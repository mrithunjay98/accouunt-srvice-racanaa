package com.racanaa.services.account.service;

import com.racanaa.services.account.dto.AccountPreferenceDto;
import com.racanaa.services.account.dto.ListResponseDto;
import com.racanaa.services.account.dto.SortDto;
import com.racanaa.services.account.exception.ResourceAlreadyExistException;
import com.racanaa.services.account.persistance.manager.AccountManager;
import com.racanaa.services.account.persistance.manager.AccountPreferenceManager;
import com.racanaa.services.account.persistance.model.AccountPreference;
import com.racanaa.services.account.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

import static com.racanaa.services.account.constant.ApiConstant.*;

/**
 * Account preference service
 *
 * @author Manohar
 * @since 1.0
 */
@Slf4j
@Component("AccountPreferenceService")
public class AccountPreferenceService {
    @Autowired
    private AccountPreferenceManager accountPreferenceManager;

    @Autowired
    private AccountManager accountManager;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ApplicationContext context;

    /**
     * List account preferences
     *
     * @param offset    page number
     * @param limit     page size
     * @param sort      the sort criteria
     * @param accountId account id
     * @return ListResponseDto<AccountPreference>
     */
    public ListResponseDto<AccountPreferenceDto> list(int offset, int limit, SortDto sort, String accountId) {
        accountManager.getById(accountId);

        Pageable pageable =
                PageRequest.of(offset - 1, limit, ObjectUtil.getSortObject(sort));
        Page<AccountPreference> page = accountPreferenceManager.list(accountId, pageable);
        Type listType = new TypeToken<List<AccountPreferenceDto>>() {
        }.getType();
        List<AccountPreferenceDto> aprDtoList = modelMapper.map(page.getContent(), listType);
        sort = (sort == null) ? ObjectUtil.getDefaultSortDto() : sort;
        return new ListResponseDto<>(aprDtoList, page.getTotalElements(), offset, limit, sort);
    }

    /**
     * Creates an account preference
     *
     * @param accountId                   account id
     * @param accountPreferenceDto account preference
     * @return AccountPreference newly created account preference
     */
    public AccountPreferenceDto create(String accountId, AccountPreferenceDto accountPreferenceDto) {
        accountManager.getById(accountId);
        accountPreferenceDto.setAccountId(accountId);
        String tenantId = ObjectUtil.getTenantId(context);
        this.validate(accountPreferenceDto, tenantId);
        AccountPreference accountPreference = modelMapper.map(accountPreferenceDto, AccountPreference.class);
        accountPreference.setTenantId(tenantId);
        accountPreference = accountPreferenceManager.create(accountPreference);
        return modelMapper.map(accountPreference, AccountPreferenceDto.class);
    }

    /**
     * Get account preference by id
     *
     * @param id account preference id
     * @return Account
     */
    public AccountPreferenceDto getById(String accountId, String id) {
        accountManager.getById(accountId);
        return modelMapper.map(
                accountPreferenceManager.getById(id, accountId), AccountPreferenceDto.class);
    }

    /**
     * Updates an account preference
     *
     * @param id account preference id
     * @return AccountPreference
     */
    public AccountPreferenceDto updateById(String accountId, String id, AccountPreferenceDto accountPreferenceDto) {
        accountManager.getById(accountId);
        String tenantId = ObjectUtil.getTenantId(context);
        this.validate(accountPreferenceDto, tenantId);
        AccountPreference accountPreference = modelMapper.map(accountPreferenceDto, AccountPreference.class);
        accountPreference = accountPreferenceManager.updateById(id, accountId, accountPreference);
        return modelMapper.map(accountPreference, AccountPreferenceDto.class);
    }

    /**
     * Deletes an account preference
     *
     * @param id account preference id
     * @return AccountPreference deleted account preference
     */
    public AccountPreferenceDto deleteById(String accountId, String id) {
        accountManager.getById(accountId);
        return modelMapper.map(accountPreferenceManager.deleteById(id, accountId), AccountPreferenceDto.class);
    }

    /**
     * Validate the accountDto to check duplicates in DB
     *
     * @param aprDto   account preference
     * @param tenantId tenant id
     */
    private void validate(AccountPreferenceDto aprDto, String tenantId) {
        // check name duplicate
        if (aprDto != null
                    && aprDto.getName() != null
                    && aprDto.getAccountId() != null) {
            AccountPreference nameCheck = accountPreferenceManager.findByNameAndAccountIdAndTenantId(
                    aprDto.getName(), aprDto.getAccountId(), tenantId);
            if (nameCheck != null) {
                throw new ResourceAlreadyExistException(
                        VALIDATION_MESSAGE_ACCOUNT_PREFERENCE_EXISTS_NAME + aprDto.getName());
            }
        }
    }
}