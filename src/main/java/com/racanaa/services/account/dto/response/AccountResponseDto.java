package com.racanaa.services.account.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * Account DTO
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
@Data
public class AccountResponseDto {
    private String id;
    private String name;
    private String nameRegistered;
    private String type; // ROOT, CLIENT, AGENCY
    private String code;
    private String email;
    private String emailMasked;
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
    private String tenantId;
    private String createdBy;
    private String updatedBy;
    private Date dateCreated;
    private Date dateUpdated;
}


