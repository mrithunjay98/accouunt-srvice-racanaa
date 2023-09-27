package com.racanaa.services.account.dto.request;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.persistance.model.Privilege;
import com.racanaa.services.account.persistance.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Collection;

public class RoleRequestDto {

    @NotBlank(message = ApiConstant.VALIDATION_MESSAGE_NAME_MANDATORY)
    @Size(min = 2, message = ApiConstant.VALIDATION_MESSAGE_NAME_LENGTH)
    private String name;
    private Collection<User> users;
    private Collection<Privilege> privileges;

}
