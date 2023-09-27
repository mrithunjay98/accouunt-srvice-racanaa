package com.racanaa.services.account.controller;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.dto.ListResponseDto;
import com.racanaa.services.account.dto.SortDto;
import com.racanaa.services.account.dto.UserPreferenceDto;
import com.racanaa.services.account.persistance.model.User;
import com.racanaa.services.account.service.UserPreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.racanaa.services.account.constant.ApiConstant.*;

/**
 * User preference controller
 *
 * @author Manohar
 * @since 1.0
 */
@Slf4j
@RestController("UserPreferenceController")
@Tag(name = "Preferences (User)", description = "Manage user preferences")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserPreferenceController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    /**
     * Returns a list of user preferences for the user. Sorts data if Sort criteria is provided,
     * otherwise sorts by a default criteria that is dateUpdated desc.
     *
     * @param userId user id
     * @param offset page number
     * @param limit  page size
     * @param sort   the sort criteria
     * @return ListResponseDto<UserPreferenceDto>
     * @see SortDto
     * @see com.racanaa.services.account.persistance.criteria.SortCriteria
     */
    @GetMapping(METHOD_UPC_LIST_PATH)
    @Operation(summary = METHOD_UPC_LIST_SUMMARY, description = METHOD_UPC_LIST_DESC)
    public ResponseEntity<ListResponseDto<UserPreferenceDto>> list(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_USER_ID_NAME, description = PARAM_USER_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String userId,
            @RequestParam(value = PARAM_OFFSET_NAME,
                    defaultValue = PARAM_OFFSET_DEFAULT,
                    required = false)
            @Parameter(name = PARAM_OFFSET_NAME, description = PARAM_OFFSET_DESC,
                    example = PARAM_OFFSET_EXAMPLE) int offset,
            @RequestParam(value = PARAM_LIMIT_NAME,
                    defaultValue = PARAM_LIMIT_DEFAULT,
                    required = false)
            @Parameter(name = PARAM_LIMIT_NAME, description = PARAM_LIMIT_DESC,
                    example = PARAM_LIMIT_EXAMPLE) int limit,
            @RequestParam(value = ApiConstant.DEFAULT_SORT_FIELD_NAME,
                    required = false)
            @Parameter(name = PARAM_SORT_NAME, description = PARAM_SORT_DESC,
                    example = PARAM_SORT_EXAMPLE) SortDto sort) {
        ListResponseDto<UserPreferenceDto> response = userPreferenceService.list(offset, limit, sort, userId);
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(response);
    }

    /**
     * Creates user preference
     *
     * @param userId user id
     * @return UserPreferenceDto
     */
    @PostMapping(METHOD_UPC_CREATE_PATH)
    @Operation(summary = METHOD_UPC_CREATE_SUMMARY, description = METHOD_UPC_CREATE_DESC)
    public ResponseEntity<UserPreferenceDto> create(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_USER_ID_NAME, description = PARAM_USER_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String userId,
            @Valid @RequestBody UserPreferenceDto userPreferenceDto,
            @RequestAttribute User user) {
        if (user != null) {
            userPreferenceDto.setCreatedBy(user.getId());
            userPreferenceDto.setUpdatedBy(user.getId());
        }
        return ResponseEntity
                       .status(HttpStatus.CREATED)
                       .body(userPreferenceService.create(userId, userPreferenceDto));

    }

    /**
     * Get user preference by id
     *
     * @param userId user id
     * @param id     user preference id
     * @return UserPreferenceDto
     */
    @GetMapping(METHOD_UPC_PATH_ID)
    @Operation(summary = METHOD_UPC_GET_SUMMARY, description = METHOD_UPC_GET_DESC)
    public ResponseEntity<UserPreferenceDto> getById(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_USER_ID_NAME, description = PARAM_USER_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String userId,
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ID_NAME, description = PARAM_UP_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String id) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(userPreferenceService.getById(userId, id));
    }

    /**
     * Update account preference
     *
     * @param userId            user id
     * @param id                user preference id
     * @param userPreferenceDto
     * @return UserPreferenceDto
     */
    @PutMapping(METHOD_UPC_PATH_ID)
    @Operation(summary = METHOD_UPC_UPDATE_SUMMARY, description = METHOD_UPC_UPDATE_DESC)
    public ResponseEntity<UserPreferenceDto> updateById(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_USER_ID_NAME, description = PARAM_USER_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String userId,
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ID_NAME, description = PARAM_UP_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String id,
            @RequestBody UserPreferenceDto userPreferenceDto) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(userPreferenceService.updateById(userId, id, userPreferenceDto));
    }

    /**
     * Delete account preference
     *
     * @param userId user id
     * @param id     user preference id
     * @return UserPreferenceDto
     */
    @DeleteMapping(METHOD_UPC_PATH_ID)
    @Operation(summary = METHOD_UPC_DELETE_SUMMARY, description = METHOD_UPC_DELETE_DESC)
    public ResponseEntity<UserPreferenceDto> deleteById(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_USER_ID_NAME, description = PARAM_USER_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String userId,
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ID_NAME, description = PARAM_UP_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String id) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(userPreferenceService.deleteById(userId, id));
    }


}
