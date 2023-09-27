package com.racanaa.services.account.service;

import com.racanaa.services.account.dto.ListResponseDto;
import com.racanaa.services.account.dto.SortDto;
import com.racanaa.services.account.dto.UserPreferenceDto;
import com.racanaa.services.account.exception.ResourceAlreadyExistException;
import com.racanaa.services.account.persistance.manager.UserManager;
import com.racanaa.services.account.persistance.manager.UserPreferenceManager;
import com.racanaa.services.account.persistance.model.UserPreference;
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

import static com.racanaa.services.account.constant.ApiConstant.VALIDATION_MESSAGE_USER_PREFERENCE_EXISTS_NAME;

/**
 * User preference service
 *
 * @author Manohar
 * @since 1.0
 */
@Slf4j
@Component("UserPreferenceService")
public class UserPreferenceService {
    @Autowired
    private UserPreferenceManager userPreferenceManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ApplicationContext context;

    /**
     * List user preferences
     *
     * @param offset    page number
     * @param limit     page size
     * @param sort      the sort criteria
     * @param userId user id
     * @return ListResponseDto<UserPreference>
     */
    public ListResponseDto<UserPreferenceDto> list(int offset, int limit, SortDto sort, String userId) {
        userManager.getById(userId);

        Pageable pageable =
                PageRequest.of(offset - 1, limit, ObjectUtil.getSortObject(sort));
        Page<UserPreference> page = userPreferenceManager.list(userId, pageable);
        Type listType = new TypeToken<List<UserPreferenceDto>>() {
        }.getType();
        List<UserPreferenceDto> aprDtoList = modelMapper.map(page.getContent(), listType);
        sort = (sort == null) ? ObjectUtil.getDefaultSortDto() : sort;
        return new ListResponseDto<>(aprDtoList, page.getTotalElements(), offset, limit, sort);
    }

    /**
     * Creates an user preference
     *
     * @param userId                   user id
     * @param userPreferenceDto user preference
     * @return UserPreference newly created user preference
     */
    public UserPreferenceDto create(String userId, UserPreferenceDto userPreferenceDto) {
        userManager.getById(userId);
        userPreferenceDto.setUserId(userId);
        String tenantId = ObjectUtil.getTenantId(context);
        this.validate(userPreferenceDto, tenantId);
        UserPreference userPreference = modelMapper.map(userPreferenceDto, UserPreference.class);
        userPreference.setTenantId(tenantId);
        userPreference = userPreferenceManager.create(userPreference);
        return modelMapper.map(userPreference, UserPreferenceDto.class);
    }

    /**
     * Get user preference by id
     *
     * @param id user preference id
     * @return Account
     */
    public UserPreferenceDto getById(String userId, String id) {
        userManager.getById(userId);
        return modelMapper.map(
                userPreferenceManager.getById(id, userId), UserPreferenceDto.class);
    }

    /**
     * Updates user preference
     *
     * @param id user preference id
     * @return UserPreference
     */
    public UserPreferenceDto updateById(String userId, String id, UserPreferenceDto userPreferenceDto) {
        userManager.getById(userId);
        String tenantId = ObjectUtil.getTenantId(context);
        this.validate(userPreferenceDto, tenantId);
        UserPreference userPreference = modelMapper.map(userPreferenceDto, UserPreference.class);
        userPreference = userPreferenceManager.updateById(id, userId, userPreference);
        return modelMapper.map(userPreference, UserPreferenceDto.class);
    }

    /**
     * Deletes an user preference
     *
     * @param id user preference id
     * @return UserPreference deleted user preference
     */
    public UserPreferenceDto deleteById(String userId, String id) {
        userManager.getById(userId);
        return modelMapper.map(userPreferenceManager.deleteById(id, userId), UserPreferenceDto.class);
    }

    /**
     * Validate the accountDto to check duplicates in DB
     *
     * @param userPreferenceDto   user preference
     * @param tenantId tenant id
     */
    private void validate(UserPreferenceDto userPreferenceDto, String tenantId) {
        // check name duplicate
        if (userPreferenceDto != null
                    && userPreferenceDto.getName() != null
                    && userPreferenceDto.getUserId() != null) {
            UserPreference nameCheck = userPreferenceManager.findByNameAndUserIdAndTenantId(
                    userPreferenceDto.getName(), userPreferenceDto.getUserId(), tenantId);
            if (nameCheck != null) {
                throw new ResourceAlreadyExistException(
                        VALIDATION_MESSAGE_USER_PREFERENCE_EXISTS_NAME + userPreferenceDto.getName());
            }
        }
    }
}