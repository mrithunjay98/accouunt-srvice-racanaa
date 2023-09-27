package com.racanaa.services.account.dto.validator;

import com.racanaa.services.account.dto.request.UserRequestDto;
import com.racanaa.services.account.dto.validator.enums.PasswordError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.racanaa.services.account.constant.ApiConstant.PASSWORD_INPUT_PARAM;


/**
 * Custom validator to validate password
 *
 * @author Manoahr
 * @since 1.0
 */
@Slf4j
public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequestDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e, PASSWORD_INPUT_PARAM, PasswordError.CONSTRAINT_EMPTY.toString());
        String password = ((UserRequestDto) target).getPassword();
        if (password.length() < 8 || password.length() > 19) {
            e.rejectValue(PASSWORD_INPUT_PARAM, PasswordError.CONSTRAINT_SIZE.toString(), new Object[] { "8", "20" }, null);
        }
        if (!password.matches(".*?[A-Z].*")) {
            e.rejectValue(PASSWORD_INPUT_PARAM, PasswordError.CONSTRAINT_UPPER_CASE.toString());
        }
        if (!password.matches(".*?[a-z].*")) {
            e.rejectValue(PASSWORD_INPUT_PARAM, PasswordError.CONSTRAINT_LOWER_CASE.toString());
        }
        if (!password.matches(".*?[0-9].*")) {
            e.rejectValue(PASSWORD_INPUT_PARAM, PasswordError.CONSTRAINT_NUMBER.toString());
        }
        if (password.matches(".*?(.{2,})\\1.*")) {
            e.rejectValue(PASSWORD_INPUT_PARAM, PasswordError.CONSTRAINT_SEQUENCE.toString());
        }
    }
}
