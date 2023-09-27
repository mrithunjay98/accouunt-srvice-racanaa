package com.racanaa.services.account.dto.request;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.persistance.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Collection;

/**
 * User Request DTO
 *
 * @author Manohar
 * @since 1.0
 */
@Data
public class UserRequestDto {

    @NotBlank(message = ApiConstant.VALIDATION_MESSAGE_NAME_MANDATORY)
    @Size(min = 2, message = ApiConstant.VALIDATION_MESSAGE_NAME_LENGTH)
    private String name;

    @NotBlank(message = ApiConstant.VALIDATION_MESSAGE_EMAIL_MANDATORY)
    @Email(message = ApiConstant.VALIDATION_MESSAGE_EMAIL_VALID)
    private String email;

    private String userName;


    private String password;
    private String countryCode;
    private String contact;
    private String contactMasked;
    private Collection<Role> roles;
}
