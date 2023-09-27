package com.racanaa.services.account.persistance.manager;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.exception.DataNotFoundException;
import com.racanaa.services.account.persistance.model.AccountPreference;
import com.racanaa.services.account.persistance.repository.AccountPreferenceRepository;
import com.racanaa.services.account.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Account preference manager to handle data access layer with transaction boundary
 *
 * @author Manohar
 * @since 1.0
 */
@Transactional
@Slf4j
@Component("AccountPreferenceManager")
public class AccountPreferenceManager {
    @Autowired
    private AccountPreferenceRepository accountPreferenceRepository;

    /**
     * Return account preferences list page
     *
     * @param pageable pageable
     * @return Page<AccountPreference>
     */
    public Page<AccountPreference> list(String accountId, Pageable pageable) {
        return accountPreferenceRepository.findByAccountId(accountId, pageable);
    }

    /**
     * Creates an account preference
     *
     * @param accountPreference account preference model
     * @return AccountPreference
     */
    public AccountPreference create(AccountPreference accountPreference) {
        return accountPreferenceRepository.save(accountPreference);
    }

    /**
     * Get account preference by id
     *
     * @param id account preference id
     * @return AccountPreference
     */
    public AccountPreference getById(String id, String accountId) {
        return accountPreferenceRepository.findByIdAndAccountId(id, accountId).orElseThrow(
                () -> new DataNotFoundException(ApiConstant.VALIDATION_MESSAGE_ACCOUNT_PREFERENCE_NOT_FOUND + id)
        );
    }

    /**
     * Updates an account preference
     *
     * @param id                the account preference id
     * @param accountPreference account preference model
     * @return AccountPreference
     */
    public AccountPreference updateById(String id, String accountId, AccountPreference accountPreference) {
        AccountPreference existingPreference = accountPreferenceRepository.findByIdAndAccountId(id, accountId).orElseThrow(
                () -> new DataNotFoundException(ApiConstant.VALIDATION_MESSAGE_ACCOUNT_PREFERENCE_NOT_FOUND + id)
        );
        ObjectUtil.copyNonNullFields(accountPreference, existingPreference);
        return accountPreferenceRepository.save(existingPreference);
    }

    /**
     * Deletes an account preference
     *
     * @param id account preference id
     * @return AccountPreference
     */
    public AccountPreference deleteById(String id, String accountId) {
        AccountPreference accountPreference = accountPreferenceRepository.findByIdAndAccountId(id, accountId).orElseThrow(
                () -> new DataNotFoundException(ApiConstant.VALIDATION_MESSAGE_ACCOUNT_PREFERENCE_NOT_FOUND + id)
        );
        accountPreferenceRepository.deleteById(id);
        return accountPreference;
    }

    /**
     * Find an account preference with name, accountId and tenantId
     *
     * @param name      account preference name
     * @param accountId account id
     * @param tenantId  tenant id
     * @return boolean
     */
    public AccountPreference findByNameAndAccountIdAndTenantId(String name, String accountId, String tenantId) {
        return accountPreferenceRepository.findByNameAndAccountIdAndTenantId(name, accountId, tenantId);
    }
}

