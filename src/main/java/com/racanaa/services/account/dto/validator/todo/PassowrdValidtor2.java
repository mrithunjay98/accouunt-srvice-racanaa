package com.racanaa.services.account.dto.validator.todo;

import com.racanaa.services.account.dto.validator.PasswordConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Custom validator to validate contact number
 *
 * @author Manoahr
 * @since 20/Sep/2023
 */
@Slf4j
public class PassowrdValidtor2 implements
        ConstraintValidator<PasswordConstraint, String> {

    @Override
    public void initialize(PasswordConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        if (contactField == null) return false;
        return contactField != null && contactField.matches("[0-9]+")
                       && (contactField.length() > 8) && (contactField.length() < 11);
    }
}
