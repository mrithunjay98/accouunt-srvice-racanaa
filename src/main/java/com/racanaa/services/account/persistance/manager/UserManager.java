package com.racanaa.services.account.persistance.manager;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.exception.DataNotFoundException;
import com.racanaa.services.account.persistance.criteria.SearchSpecificationBuilder;
import com.racanaa.services.account.persistance.model.User;
import com.racanaa.services.account.persistance.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * User manager to handle data access layer with transaction boundary
 *
 * @author Manohar
 * @since 1.0
 */
@Transactional
@Slf4j
@Component("UserManager")
public class UserManager {

    @Autowired
    private UserRepository userRepository;

    /**
     * Return account list page
     *
     * @param pageable pageable
     * @return Page<Account>
     */
    public Page<User> list(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Returns a list of users. Sorts data if Sort criteria is provided,
     * otherwise sorts by a default criteria that is dateUpdated desc.
     *
     * @param builder  Search specification builder
     * @param pageable pageable
     * @return Page<User>
     * @see Pageable
     * @see SearchSpecificationBuilder
     */
    public Page<User> search(SearchSpecificationBuilder<User> builder, Pageable pageable) {
        return userRepository.findAll(builder.build(), pageable);
    }

    /**
     * Get user by id
     *
     * @param id user id
     * @return User
     */
    public User getById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(ApiConstant.VALIDATION_MESSAGE_USER_PREFERENCE_NOT_FOUND + id)
        );
    }


}
