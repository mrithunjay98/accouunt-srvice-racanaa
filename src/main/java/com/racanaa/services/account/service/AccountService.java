package com.racanaa.services.account.service;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.dto.ListResponseDto;
import com.racanaa.services.account.dto.SearchDto;
import com.racanaa.services.account.dto.SortDto;
import com.racanaa.services.account.dto.request.AccountRequestDto;
import com.racanaa.services.account.dto.request.AccountUpdateDto;
import com.racanaa.services.account.dto.response.AccountResponseDto;
import com.racanaa.services.account.exception.ResourceAlreadyExistException;
import com.racanaa.services.account.persistance.criteria.SearchCriteria;
import com.racanaa.services.account.persistance.criteria.SearchSpecificationBuilder;
import com.racanaa.services.account.persistance.manager.AccountManager;
import com.racanaa.services.account.persistance.model.Account;
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


/**
 * Account service
 *
 * @author Manohar
 * @since 1.0
 */
@Slf4j
@Component("AccountService")
public class AccountService {
    @Autowired
    private AccountManager accountManager;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ApplicationContext context;

    /**
     * Returns a list of accounts based on the search criteria. Sorts data if Sort criteria is provided,
     * otherwise sorts by a default criteria that is dateUpdated desc.
     *
     * @param offset page number
     * @param limit  page size
     * @param sort   the sort criteria
     * @return ListResponseDto<AccountResponseDto> list response dto of AccountResponseDto's
     */
    public ListResponseDto<AccountResponseDto> list(int offset, int limit, SortDto sort) {
        Pageable pageable =
                PageRequest.of(offset - 1, limit, ObjectUtil.getSortObject(sort));
        Page<Account> page = accountManager.list(pageable);
        Type listType = new TypeToken<List<AccountResponseDto>>() {}.getType();
        List<AccountResponseDto> accountRequestDtoList = modelMapper.map(page.getContent(), listType);
        sort = (sort == null) ? ObjectUtil.getDefaultSortDto() : sort;
        return new ListResponseDto<>(accountRequestDtoList, page.getTotalElements(), offset, limit, sort);
    }

    /**
     * Returns a list of accounts based on the search criteria. Sorts data if Sort criteria is provided,
     * otherwise sorts by a default criteria that is dateUpdated desc.
     *
     * @param offset page number
     * @param limit  page size
     * @param sort   the sort criteria
     * @param search the search criteria
     * @return ListResponseDto<AccountResponseDto> list response dto of AccountResponseDto's
     * @see SortDto
     * @see com.racanaa.services.account.persistance.criteria.SortCriteria
     * @see SearchDto
     * @see com.racanaa.services.account.persistance.criteria.SearchCriteria
     */
    public ListResponseDto<AccountResponseDto> search(int offset, int limit, SortDto sort, SearchDto search) {
        if (search == null) {
            return list(offset, limit, sort);
        }
        SearchSpecificationBuilder<Account> builder = new SearchSpecificationBuilder<Account>();
        List<SearchCriteria> criteriaList = search.getCriteria();
        if (criteriaList != null) {
            criteriaList.forEach(x ->
            {
                x.setDataOption(search.getDataOption());
                builder.with(x);
            });
        }
        Pageable pageable = PageRequest.of(offset - 1, limit, ObjectUtil.getSortObject(sort));
        Page<Account> page = accountManager.search(builder, pageable);
        Type listType = new TypeToken<List<AccountResponseDto>>() {
        }.getType();
        List<AccountResponseDto> accountRequestDtoList = modelMapper.map(page.getContent(), listType);
        sort = (sort == null) ? ObjectUtil.getDefaultSortDto() : sort;
        return new ListResponseDto<>(accountRequestDtoList, page.getTotalElements(), offset, limit, sort, search);
    }

    /**
     * Creates an account
     *
     * @param accountRequestDto account dto
     * @return AccountResponseDto newly created response dto
     */
    public AccountResponseDto create(AccountRequestDto accountRequestDto) {
        String tenantId = ObjectUtil.getTenantId(context);
        this.validate(accountRequestDto, tenantId);
        Account account = modelMapper.map(accountRequestDto, Account.class);
        account.setTenantId(tenantId);
        account = accountManager.create(account);
        return modelMapper.map(account, AccountResponseDto.class);
    }

    /**
     * Get account by id
     *
     * @param id account id
     * @return AccountResponseDto account response dto
     */
    public AccountResponseDto getById(String id) {
        return modelMapper.map(
                accountManager.getById(id), AccountResponseDto.class);
    }

    /**
     * Updates an account
     *
     * @param id                account id
     * @param accountRequestDto the account request dto
     * @return AccountResponseDto
     */
    public AccountResponseDto updateById(String id, AccountUpdateDto accountUpdateDto) {
        String tenantId = ObjectUtil.getTenantId(context);
        this.validate(accountUpdateDto, tenantId);
        Account account = modelMapper.map(accountUpdateDto, Account.class);
        account = accountManager.updateById(id, account);
        return modelMapper.map(account, AccountResponseDto.class);
    }

    /**
     * Delete an account
     *
     * @param id account id
     * @return AccountResponseDto deleted account dto
     */
    public AccountResponseDto deleteById(String id) {
        return modelMapper.map(accountManager.deleteById(id),
                AccountResponseDto.class);
    }

    /**
     * Validate the accountRequestDto to check duplicates in DB
     *
     * @param accountRequestDto account dto
     * @param tenantId          tenant id
     */
    private void validate(AccountRequestDto accountRequestDto, String tenantId) {
        // check name duplicate
        if (accountRequestDto != null && accountRequestDto.getName() != null) {
            Account nameCheck = accountManager.getByNameAndTenantId(accountRequestDto.getName(), tenantId);
            if (nameCheck != null) {
                throw new ResourceAlreadyExistException(
                        ApiConstant.VALIDATION_MESSAGE_ACCOUNT_EXISTS_NAME + accountRequestDto.getName());
            }
        }

        // check account duplicate
        if (accountRequestDto != null && accountRequestDto.getDomain() != null) {
            Account domainCheck = accountManager.getByDomainAndTenantId(accountRequestDto.getDomain(), tenantId);
            if (domainCheck != null) {
                throw new ResourceAlreadyExistException(
                        ApiConstant.VALIDATION_MESSAGE_ACCOUNT_EXISTS_DOMAIN + accountRequestDto.getDomain());
            }
        }
    }

    private void validate(AccountUpdateDto accountUpdateDto, String tenantId) {
        // check name duplicate
        if (accountUpdateDto != null && accountUpdateDto.getName() != null) {
            Account nameCheck = accountManager.getByNameAndTenantId(accountUpdateDto.getName(), tenantId);
            if (nameCheck != null) {
                throw new ResourceAlreadyExistException(
                        ApiConstant.VALIDATION_MESSAGE_ACCOUNT_EXISTS_NAME + accountUpdateDto.getName());
            }
        }

        // check account duplicate
        if (accountUpdateDto != null && accountUpdateDto.getDomain() != null) {
            Account domainCheck = accountManager.getByDomainAndTenantId(accountUpdateDto.getDomain(), tenantId);
            if (domainCheck != null) {
                throw new ResourceAlreadyExistException(
                        ApiConstant.VALIDATION_MESSAGE_ACCOUNT_EXISTS_DOMAIN + accountUpdateDto.getDomain());
            }
        }
    }
}
