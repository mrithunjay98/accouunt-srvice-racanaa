package com.racanaa.services.account.persistance.repository;

import com.racanaa.services.account.persistance.model.User;
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
 * User repository
 *
 * @author Manohar
 * @since 1.0
 */
@Repository("UserRepository")
public interface UserRepository extends CrudRepository<User, String>,
                                                PagingAndSortingRepository<User, String>,
                                                JpaSpecificationExecutor<User> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "1000")})
    Optional<User> getById(String id);

    User findByEmail(String email);

    @Override
    void delete(User user);
}
