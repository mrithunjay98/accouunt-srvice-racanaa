package com.racanaa.services.account.dto.response;

import com.racanaa.services.account.persistance.model.Privilege;
import com.racanaa.services.account.persistance.model.User;

import java.util.Collection;
import java.util.Date;

public class RoleResponseDto {

    private String id;
    private String name;
    private String tenantId;
    private String createdBy;
    private String updatedBy;
    private Date dateCreated;
    private Date dateUpdated;
    private Collection<User> users;
    private Collection<Privilege> privileges;
}
