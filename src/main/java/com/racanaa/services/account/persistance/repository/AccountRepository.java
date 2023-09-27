package com.racanaa.services.account.persistance.repository;

import com.racanaa.services.account.persistance.model.Account;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Account repository
 *
 * @author Manohar
 * @since 1.0
 */
@Repository("AccountRepository")
public interface AccountRepository extends CrudRepository<Account, String>,
                                                   PagingAndSortingRepository<Account, String>,
                                                   JpaSpecificationExecutor<Account> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "1000")})
    Optional<Account> getById(String id);

    Account findByName(String name);

    Account findByApiKey(String apiKey);

    Account findByNameAndTenantId(String name, String tenantId);

    Account findByDomainAndTenantId(String domain, String tenantId);
}
