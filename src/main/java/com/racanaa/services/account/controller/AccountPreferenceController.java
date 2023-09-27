package com.racanaa.services.account.controller;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.dto.AccountPreferenceDto;
import com.racanaa.services.account.dto.ListResponseDto;
import com.racanaa.services.account.dto.SortDto;
import com.racanaa.services.account.service.AccountPreferenceService;
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
 * Account preference controller
 *
 * @author Manohar
 * @since 1.0
 */
@Slf4j
@RestController("AccountPreferenceController")
@Tag(name = "Preferences (Account)", description = "Manage account preferences")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountPreferenceController {

    @Autowired
    private AccountPreferenceService accountPreferenceService;

    /**
     * Returns a list of account preferences for the account. Sorts data if Sort criteria is provided,
     * otherwise sorts by a default criteria that is dateUpdated desc.
     *
     * @param accountId account id
     * @param offset    page number
     * @param limit     page size
     * @param sort      the sort criteria
     * @return ListResponseDto<AccountPreferenceDto>
     * @see SortDto
     * @see com.racanaa.services.account.persistance.criteria.SortCriteria
     */
    @GetMapping(METHOD_APC_LIST_PATH)
    @Operation(summary = METHOD_APC_LIST_SUMMARY, description = METHOD_APC_LIST_DESC)
    public ResponseEntity<ListResponseDto<AccountPreferenceDto>> list(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ACCOUNT_ID_NAME, description = PARAM_ACCOUNT_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String accountId,
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
        ListResponseDto<AccountPreferenceDto> response = accountPreferenceService.list(offset, limit, sort, accountId);
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(response);
    }

    /**
     * Creates an account preference
     *
     * @param accountId account id
     * @return AccountPreferenceDto
     */
    @PostMapping(METHOD_APC_CREATE_PATH)
    @Operation(summary = METHOD_APC_CREATE_SUMMARY, description = METHOD_APC_CREATE_DESC)
    public ResponseEntity<AccountPreferenceDto> create(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ACCOUNT_ID_NAME, description = PARAM_ACCOUNT_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String accountId,
            @Valid @RequestBody AccountPreferenceDto accountPreferenceDto) {
        return ResponseEntity
                       .status(HttpStatus.CREATED)
                       .body(accountPreferenceService.create(accountId, accountPreferenceDto));
    }

    /**
     * Get account preference by id
     *
     * @param accountId account id
     * @param id        account preference id
     * @return AccountPreferenceDto
     */
    @GetMapping(METHOD_APC_PATH_ID)
    @Operation(summary = METHOD_APC_GET_SUMMARY, description = METHOD_APC_GET_DESC)
    public ResponseEntity<AccountPreferenceDto> getById(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ACCOUNT_ID_NAME, description = PARAM_ACCOUNT_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String accountId,
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ID_NAME, description = PARAM_AP_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String id) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(accountPreferenceService.getById(accountId, id));
    }

    /**
     * Update account preference
     *
     * @param accountId                   account id
     * @param id                          account preference id
     * @param accountPreferenceDto
     * @return AccountPreferenceDto
     */
    @PutMapping(METHOD_APC_PATH_ID)
    @Operation(summary = METHOD_APC_UPDATE_SUMMARY, description = METHOD_APC_UPDATE_DESC)
    public ResponseEntity<AccountPreferenceDto> updateById(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ACCOUNT_ID_NAME, description = PARAM_ACCOUNT_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String accountId,
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ID_NAME, description = PARAM_AP_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String id,
            @RequestBody AccountPreferenceDto accountPreferenceDto) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(accountPreferenceService.updateById(accountId, id, accountPreferenceDto));
    }

    /**
     * Delete account preference
     *
     * @param accountId account id
     * @param id        account preference id
     * @return AccountPreferenceDto
     */
    @DeleteMapping(METHOD_APC_PATH_ID)
    @Operation(summary = METHOD_APC_DELETE_SUMMARY, description = METHOD_APC_DELETE_DESC)
    public ResponseEntity<AccountPreferenceDto> deleteById(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ACCOUNT_ID_NAME, description = PARAM_ACCOUNT_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String accountId,
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ID_NAME, description = PARAM_AP_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String id) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(accountPreferenceService.deleteById(accountId, id));
    }

}