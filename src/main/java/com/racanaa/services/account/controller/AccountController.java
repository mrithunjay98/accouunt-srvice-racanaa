package com.racanaa.services.account.controller;

import com.racanaa.services.account.dto.ListResponseDto;
import com.racanaa.services.account.dto.SearchDto;
import com.racanaa.services.account.dto.SortDto;
import com.racanaa.services.account.dto.request.AccountRequestDto;
import com.racanaa.services.account.dto.request.AccountUpdateDto;
import com.racanaa.services.account.dto.response.AccountResponseDto;
import com.racanaa.services.account.exception.DataValidationException;
import com.racanaa.services.account.service.AccountService;
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
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.racanaa.services.account.constant.ApiConstant.*;

/**
 * Account controller
 *
 * @author Manohar
 * @since 1.0
 */
@Slf4j
@RestController
@Component("AccountController")
@Tag(name = "Accounts", description = "Manage accounts")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SmartValidator validator;

    /**
     * Returns a list of account. Sorts data if Sort criteria is provided,
     * otherwise sorts by a default criteria that is dateUpdated desc.
     *
     * @param offset page number
     * @param limit  page size
     * @param sort   the sort criteria
     * @return ListResponseDto<Account>
     * @see SortDto
     * @see com.racanaa.services.account.persistance.criteria.SortCriteria
     */
    @GetMapping(METHOD_AC_LIST_PATH)
    @Operation(summary = METHOD_AC_LIST_SUMMARY, description = METHOD_AC_LIST_DESC)
    public ResponseEntity<ListResponseDto<AccountResponseDto>> list(
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
            @RequestParam(value = PARAM_SORT_NAME, required = false)
            @Parameter(name = PARAM_SORT_NAME, description = PARAM_SORT_DESC,
                    example = PARAM_SORT_EXAMPLE) SortDto sort) {
        ListResponseDto<AccountResponseDto> response = accountService.list(offset, limit, sort);
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(response);
    }

    /**
     * Returns a list of accounts based on the search criteria. Sorts data if Sort criteria is provided,
     * otherwise sorts by a default criteria that is dateUpdated desc.
     *
     * @param offset page number
     * @param limit  page size
     * @param sort   the sort criteria
     * @param search the search criteria
     * @return ListResponseDto<Account>
     * @see SortDto
     * @see com.racanaa.services.account.persistance.criteria.SortCriteria
     * @see SearchDto
     * @see com.racanaa.services.account.persistance.criteria.SearchCriteria
     */
    @GetMapping(METHOD_AC_SEARCH_PATH)
    @Operation(summary = METHOD_AC_SEARCH_SUMMARY, description = METHOD_AC_SEARCH_DESC)
    public ResponseEntity<ListResponseDto<AccountResponseDto>> search(
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
            @RequestParam(value = PARAM_SORT_NAME, required = false)
            @Parameter(name = PARAM_SORT_NAME, description = PARAM_SORT_DESC,
                    example = PARAM_SORT_EXAMPLE) SortDto sort,
            @RequestParam(value = PARAM_SEARCH_NAME, required = false)
            @Parameter(name = PARAM_SEARCH_NAME, description = PARAM_SEARCH_DESC,
                    example = PARAM_SEARCH_EXAMPLE) SearchDto search) {
        ListResponseDto<AccountResponseDto> response = accountService.search(offset, limit, sort, search);
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(response);
    }

    /**
     * Creates an account
     *
     * @param accountRequestDto account dto
     * @return AccountResponseDto newly created account
     */
    @PostMapping(METHOD_AC_CREATE_PATH)
    @Operation(summary = METHOD_AC_CREATE_SUMMARY, description = METHOD_AC_CREATE_DESC)
    public ResponseEntity<AccountResponseDto> create(
            @Valid @RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity
                       .status(HttpStatus.CREATED)
                       .body(accountService.create(accountRequestDto));
    }

    /**
     * Get account by id
     *
     * @param id account id
     * @return AccountResponseDto
     */
    @GetMapping(METHOD_AC_PATH_ID)
    @Operation(summary = METHOD_AC_GET_SUMMARY, description = METHOD_AC_GET_DESC)
    public ResponseEntity<AccountResponseDto> getById(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ID_NAME, description = PARAM_ACCOUNT_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String id) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(accountService.getById(id));
    }

    /**
     * Update account by id
     *
     * @param id account id
     * @return AccountResponseDto
     */
    @PutMapping(METHOD_AC_PATH_ID)
    @Operation(summary = METHOD_AC_UPDATE_SUMMARY, description = METHOD_AC_UPDATE_DESC)
    public ResponseEntity<AccountResponseDto> updateById(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ID_NAME, description = PARAM_ACCOUNT_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String id,
            @Validated @Required @RequestBody AccountUpdateDto accountUpdateDto,
            BindingResult bindingResult, Locale locale) {

        Map<String, String> errors = this.validateDto(accountUpdateDto, bindingResult);
        log.info("errors---- "+ errors);
        if(!errors.isEmpty()){
            HttpStatus status = HttpStatus.BAD_REQUEST;
            throw new DataValidationException(status, "Input validation failed", errors);
        }
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(accountService.updateById(id, accountUpdateDto));
    }

    /**
     * Delete account by id
     *
     * @param id account id
     * @return AccountResponseDto
     */
    @DeleteMapping(METHOD_AC_PATH_ID)
    @Operation(summary = METHOD_AC_DELETE_SUMMARY, description = METHOD_AC_DELETE_DESC)
    public ResponseEntity<AccountResponseDto> deleteById(
            @Required
            @PathVariable()
            @Parameter(name = PARAM_ID_NAME, description = PARAM_ACCOUNT_ID_DESC,
                    example = PARAM_ID_EXAMPLE) String id) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(accountService.deleteById(id));
    }

    /**
     * Manually validate the dto
     *
     * @param dtoObject DTO to be validated
     * @param bindingResult binding request
     * @return AccountResponseDto
     */
    public Map<String, String> validateDto(Object dtoObject, BindingResult bindingResult) {
        //TODO: Make this a generic method to validate
        //TODO: Learn to get the binder and do the following:
        //TODO: binder.setValidator(new PasswordValidator());
        //TODO: binder.getValidator().validate(urdto, bindingResult);
        Map<String, String> errors = new HashMap<>();
        validator.validate(dtoObject, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            bindingResult.getFieldErrors().stream().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                log.info("fieldName "+ fieldName);
                log.info("errorMessage "+ errorMessage);
                errors.put(fieldName, errorMessage);
            });
        }
        return errors;
    }
}
