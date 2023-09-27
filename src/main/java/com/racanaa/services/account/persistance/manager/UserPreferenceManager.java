package com.racanaa.services.account.persistance.manager;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.exception.DataNotFoundException;
import com.racanaa.services.account.persistance.model.UserPreference;
import com.racanaa.services.account.persistance.repository.UserPreferenceRepository;
import com.racanaa.services.account.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * User preference manager to handle data access layer with transaction boundary
 *
 * @author Manohar
 * @since 1.0
 */
@Transactional
@Slf4j
@Component("UserPreferenceManager")
public class UserPreferenceManager {
    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    /**
     * Return user preferences list page
     *
     * @param pageable pageable
     * @return Page<UserPreference>
     */
    public Page<UserPreference> list(String userId, Pageable pageable) {
        return userPreferenceRepository.findByUserId(userId, pageable);
    }

    /**
     * Creates an user preference
     *
     * @param userPreference user preference model
     * @return UserPreference
     */
    public UserPreference create(UserPreference userPreference) {
        return userPreferenceRepository.save(userPreference);
    }

    /**
     * Get user preference by id
     *
     * @param id user preference id
     * @return UserPreference
     */
    public UserPreference getById(String id, String userId) {
        return userPreferenceRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new DataNotFoundException(ApiConstant.VALIDATION_MESSAGE_ACCOUNT_PREFERENCE_NOT_FOUND + id)
        );
    }

    /**
     * Updates an user preference
     *
     * @param id             the user preference id
     * @param userPreference user preference model
     * @return UserPreference
     */
    public UserPreference updateById(String id, String userId, UserPreference userPreference) {
        UserPreference existingPreference = userPreferenceRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new DataNotFoundException(ApiConstant.VALIDATION_MESSAGE_ACCOUNT_PREFERENCE_NOT_FOUND + id)
        );
        ObjectUtil.copyNonNullFields(userPreference, existingPreference);
        return userPreferenceRepository.save(existingPreference);
    }

    /**
     * Deletes an user preference
     *
     * @param id user preference id
     * @return UserPreference
     */
    public UserPreference deleteById(String id, String userId) {
        UserPreference userPreference = userPreferenceRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new DataNotFoundException(ApiConstant.VALIDATION_MESSAGE_ACCOUNT_PREFERENCE_NOT_FOUND + id)
        );
        userPreferenceRepository.deleteById(id);
        return userPreference;
    }

    /**
     * Find an user preference with name, userId and tenantId
     *
     * @param name     user preference name
     * @param userId   user id
     * @param tenantId tenant id
     * @return boolean
     */
    public UserPreference findByNameAndUserIdAndTenantId(String name, String userId, String tenantId) {
        return userPreferenceRepository.findByNameAndUserIdAndTenantId(name, userId, tenantId);
    }
}

