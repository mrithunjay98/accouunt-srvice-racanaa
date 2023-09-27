package com.racanaa.services.account.persistance.repository;

import com.racanaa.services.account.persistance.model.AccountPreference;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Account preference repository
 *
 * @author Manohar
 * @since 1.0
 */
@Repository("AccountPreferenceRepository")
public interface AccountPreferenceRepository extends CrudRepository<AccountPreference, String>,
                                                             PagingAndSortingRepository<AccountPreference, String> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "1000")})
    Optional<AccountPreference> getById(String id);

    AccountPreference findByNameAndAccountIdAndTenantId(String name, String accountId, String tenantId);

    Page<AccountPreference> findByAccountId(String accountId, Pageable pageable);

    Optional<AccountPreference> findByIdAndAccountId(String id, String accountId);

}

