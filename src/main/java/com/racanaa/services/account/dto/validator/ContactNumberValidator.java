package com.racanaa.services.account.dto.validator;

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
public class ContactNumberValidator implements
        ConstraintValidator<ContactNumberConstraint, String> {

    @Override
    public void initialize(ContactNumberConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        if (contactField == null) return false;
        return contactField != null && contactField.matches("[0-9]+")
                       && (contactField.length() > 8) && (contactField.length() < 11);
    }
}
