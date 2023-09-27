package com.racanaa.services.account.persistance.repository;

import com.racanaa.services.account.persistance.model.UserPreference;
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
 * User preference repository
 *
 * @author Manohar
 * @since 1.0
 */
@Repository("UserPreferenceRepository")
public interface UserPreferenceRepository extends CrudRepository<UserPreference, String>,
                                                             PagingAndSortingRepository<UserPreference, String> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "1000")})
    Optional<UserPreference> getById(String id);

    UserPreference findByNameAndUserIdAndTenantId(String name, String userId, String tenantId);

    Page<UserPreference> findByUserId(String userId, Pageable pageable);

    Optional<UserPreference> findByIdAndUserId(String id, String userId);

}

