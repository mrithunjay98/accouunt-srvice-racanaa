package com.racanaa.services.account.persistance.repository;

import com.racanaa.services.account.persistance.model.Account;
import com.racanaa.services.account.persistance.model.SystemConfig;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * System config repository
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
@Repository("SystemConfigRepository")
public interface SystemConfigRepository extends CrudRepository<SystemConfig, String>,
                                                        PagingAndSortingRepository<SystemConfig, String> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "1000")})
    Optional<Account> getById(String id);

    SystemConfig findByName(String name);

    SystemConfig findByNameAndTenantId(String name, String tenantId);
}
