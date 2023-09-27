package com.racanaa.services.account.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.persistance.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Collection;

public class PrevilegeRequestDto {

    @NotBlank(message = ApiConstant.VALIDATION_MESSAGE_NAME_MANDATORY)
    @Size(min = 2, message = ApiConstant.VALIDATION_MESSAGE_NAME_LENGTH)
    private String name;

    @JsonIgnore
    private Collection<Role> roles;
}
