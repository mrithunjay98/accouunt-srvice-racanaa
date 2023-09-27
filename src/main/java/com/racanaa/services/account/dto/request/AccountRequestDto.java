package com.racanaa.services.account.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.dto.validator.ContactNumberConstraint;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Account Request DTO
 *
 * @author Manohar
 * @since 1.0
 */
@Data
public class AccountRequestDto {

    @NotBlank(message = ApiConstant.VALIDATION_MESSAGE_NAME_MANDATORY)
    @Size(min = 2, message = ApiConstant.VALIDATION_MESSAGE_NAME_LENGTH)
    private String name;

    @JsonIgnore
    private String nameRegistered;

    private String type; // ROOT, CLIENT, AGENCY

    @JsonIgnore
    private String code;

    @NotBlank(message = ApiConstant.VALIDATION_MESSAGE_EMAIL_MANDATORY)
    @Email(message = ApiConstant.VALIDATION_MESSAGE_EMAIL_VALID)
    private String email;

    @NotNull(message = "Country code is mandatory")
    @Digits(message = "Length of country code must be less than 3", integer = 3, fraction = 0)
    private String countryCode;

    @NotNull(message = "Contact number is required")
    @ContactNumberConstraint(message = "Contact number must be 8 to 10 character only")
    private String contact;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String district;
    private String state;
    private String country;

    @NotNull(message = "Pin code is required")
    @Digits(message = "Length of pin code must be less than 6", integer = 6, fraction = 0)
    private String pinCode;

    @Pattern(regexp = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$", message = "Domain must be a valid domain name")
    private String domain;
}

