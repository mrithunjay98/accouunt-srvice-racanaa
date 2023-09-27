package com.racanaa.services.account.persistance.manager;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.exception.DataNotFoundException;
import com.racanaa.services.account.persistance.criteria.SearchSpecificationBuilder;
import com.racanaa.services.account.persistance.model.Account;
import com.racanaa.services.account.persistance.repository.AccountRepository;
import com.racanaa.services.account.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Account manager to handle data access layer with transaction boundary
 *
 * @author Manohar
 * @since 1.0
 */
@Transactional
@Slf4j
@Component("AccountManager")
public class AccountManager {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Return account list page
     *
     * @param pageable pageable
     * @return Page<Account>
     */
    public Page<Account> list(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    /**
     * Returns a list of accounts. Sorts data if Sort criteria is provided,
     * otherwise sorts by a default criteria that is dateUpdated desc.
     *
     * @param builder  Search specification builder
     * @param pageable pageable
     * @return Page<Account>
     * @see Pageable
     * @see SearchSpecificationBuilder
     */
    public Page<Account> search(SearchSpecificationBuilder<Account> builder, Pageable pageable) {
        return accountRepository.findAll(builder.build(), pageable);
    }

    /**
     * Creates an account
     *
     * @param account account model
     * @return Account
     */
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Get account by id
     *
     * @param id account id
     * @return Account
     */
    public Account getById(String id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(ApiConstant.VALIDATION_MESSAGE_ACCOUNT_NOT_FOUND + id)
        );
    }

    /**
     * Updates an account
     *
     * @param id      account id
     * @param account account model
     * @return Account
     */
    public Account updateById(String id, Account account) {
        Account existingAccount = accountRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(ApiConstant.VALIDATION_MESSAGE_ACCOUNT_NOT_FOUND + id)
        );
        ObjectUtil.copyNonNullFields(account, existingAccount);
        return accountRepository.save(existingAccount);
    }

    /**
     * Deletes an account
     *
     * @param id account id
     * @return Account
     */
    public Account deleteById(String id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(ApiConstant.VALIDATION_MESSAGE_ACCOUNT_NOT_FOUND + id)
        );
        accountRepository.deleteById(account.getId());
        return account;
    }

    /**
     * Find an account with name and tenantId
     *
     * @param name     account name
     * @param tenantId tenant id
     * @return boolean
     */
    public Account getByNameAndTenantId(String name, String tenantId) {
        return accountRepository.findByNameAndTenantId(name, tenantId);
    }

    /**
     * find an account with domain and tenantId
     *
     * @param domain   domain name
     * @param tenantId tenant id
     * @return Account with domain and tenantId match
     */
    public Account getByDomainAndTenantId(String domain, String tenantId) {
        return accountRepository.findByDomainAndTenantId(domain, tenantId);
    }

    /**
     * Get an account by API key
     *
     * @param apiKey api key
     * @return Account with matching api key
     */
    public Account getByApiKey(String apiKey) {
        return accountRepository.findByApiKey(apiKey);
    }
}
