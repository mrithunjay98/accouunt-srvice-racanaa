package com.racanaa.services.account.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.racanaa.services.account.constant.ApiConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Account Request DTO
 *
 * @author Manohar
 * @since 1.0
 */
@Data
public class AccountUpdateDto {

    @NotBlank(message = ApiConstant.VALIDATION_MESSAGE_NAME_MANDATORY)
    @Size(min = 2, message = ApiConstant.VALIDATION_MESSAGE_NAME_LENGTH)
    private String name;

    private String nameRegistered;

    private String type; // ROOT, CLIENT, AGENCY

    @JsonIgnore
    private String code;

    private String email;

    private String countryCode;

    private String contact;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String district;
    private String state;
    private String country;

    private String pinCode;

    private String domain;
}

