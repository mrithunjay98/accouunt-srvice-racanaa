package com.racanaa.services.account.controller;

import com.racanaa.services.account.dto.ListResponseDto;
import com.racanaa.services.account.dto.SearchDto;
import com.racanaa.services.account.dto.SortDto;
import com.racanaa.services.account.dto.request.UserRequestDto;
import com.racanaa.services.account.dto.response.UserResponseDto;
import com.racanaa.services.account.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.racanaa.services.account.constant.ApiConstant.*;

/**
 * User controller
 *
 * @author Manohar
 * @since 1.0
 */

@Slf4j
@RestController
@Component("UserController")
@Tag(name = "Users", description = "Manage users")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    /**
     * Returns a list of users. Sorts data if Sort criteria is provided,
     * otherwise sorts by a default criteria that is dateUpdated desc.
     *
     * @param offset page number
     * @param limit  page size
     * @param sort   the sort criteria
     * @return ListResponseDto<UserResponseDto>
     * @see SortDto
     * @see com.racanaa.services.account.persistance.criteria.SortCriteria
     */
    @GetMapping(METHOD_UC_LIST_PATH)
    @Operation(summary = METHOD_UC_LIST_SUMMARY, description = METHOD_UC_LIST_DESC)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ListResponseDto<UserResponseDto>> list(
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
        ListResponseDto<UserResponseDto> response = userService.list(offset, limit, sort);
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(response);
    }

    /**
     * Returns a list of users based on the search criteria. Sorts data if Sort criteria is provided,
     * otherwise sorts by a default criteria that is dateUpdated desc.
     *
     * @param offset page number
     * @param limit  page size
     * @param sort   the sort criteria
     * @param search the search criteria
     * @return ListResponseDto<UserResponseDto>
     * @see SortDto
     * @see com.racanaa.services.account.persistance.criteria.SortCriteria
     * @see SearchDto
     * @see com.racanaa.services.account.persistance.criteria.SearchCriteria
     */
    @GetMapping(METHOD_UC_SEARCH_PATH)
    @Operation(summary = METHOD_UC_SEARCH_SUMMARY, description = METHOD_UC_SEARCH_DESC)
    public ResponseEntity<ListResponseDto<UserResponseDto>> search(
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
        ListResponseDto<UserResponseDto> response = userService.search(offset, limit, sort, search);
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(response);
    }

    //@RequestMapping(method = RequestMethod.GET)
    @PostMapping(METHOD_UC_CREATE_PATH)
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseDto> validatePassword(@Validated @RequestBody UserRequestDto userRequestDto, BindingResult bindingResult, Locale locale) {
        log.info("Input value from the request passed : "+ userRequestDto);
        if (bindingResult.hasFieldErrors()) {
            List<String> errorList = new ArrayList<>();
            bindingResult.getFieldErrors().stream().forEach(error -> {
                errorList.add(messageSource.getMessage(error.getCode(), error.getArguments(), locale));
            });

            log.info("errorList : "+ errorList);

            //throw new InvalidPasswordException(errorList);
        }
        return null;
        //return new ResponseEntity<ValidatePasswordResponse>(new ValidatePasswordResponse(loginInputData.getPassword()), HttpStatus.OK);
    }

//    /**
//     * Creates a user
//     *
//     * @param userRequestDto user request dto
//     * @return UserResponseDto newly created account
//     */
//    @PostMapping(METHOD_UC_CREATE_PATH)
//    @Operation(summary = METHOD_UC_CREATE_SUMMARY, description = METHOD_UC_CREATE_DESC)
//    public ResponseEntity<UserResponseDto> create(
//            @Valid @RequestBody UserRequestDto userRequestDto) {
//        return ResponseEntity
//                       .status(HttpStatus.CREATED)
//                       .body(accountService.create(accountRequestDto));
//    }
   /* *//**
     * Get user by id
     *
     * @return
     *//*
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable String id) {
        return new ResponseEntity<Optional<User>>(userService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }*/

}
