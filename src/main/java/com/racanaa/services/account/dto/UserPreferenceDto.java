package com.racanaa.services.account.dto;

import com.racanaa.services.account.constant.ApiConstant;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

/**
 * User preference DTO
 *
 * @author Manohar
 * @since 1.0
 */
@Data
public class UserPreferenceDto {

    private String userId;
    @NotBlank(message = ApiConstant.VALIDATION_MESSAGE_NAME_MANDATORY)
    private String name;

    @NotBlank(message = ApiConstant.VALIDATION_MESSAGE_VALUE_MANDATORY)
    private String value;

    private String id;

    private String tenantId;
    private String createdBy;
    private String updatedBy;
    private Date dateCreated;
    private Date dateUpdated;
}
