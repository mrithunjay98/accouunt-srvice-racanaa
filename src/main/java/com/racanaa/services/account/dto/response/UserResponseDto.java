package com.racanaa.services.account.dto.response;

import com.racanaa.services.account.persistance.model.Role;
import jakarta.persistence.Column;

import java.util.Collection;
import java.util.Date;

public class UserResponseDto {
    private String id;
    @Column(name = "name")
    private String name;
    private String emailMasked;
    private String countryCode;
    private String contact;
    private String contactMasked;
    private String tenantId;
    private String createdBy;
    private String updatedBy;
    private Date dateCreated;
    private Date dateUpdated;
    private Collection<Role> roles;
}
