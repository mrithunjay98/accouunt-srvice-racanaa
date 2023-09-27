package com.racanaa.services.account.persistance.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * The SystemConfig model class
 * <p>
 * System Config is used to save all the system related configurations.
 * This is used to orchestrate the entire system based on the configurations.
 * <p>
 * There are some system configurations mandatory even to start this microservice.
 * Below is the list of these mandatory system configurations with some reference
 * values for example.
 *
 * <p>
 * api.key.name - The header name in which api key is sent. By default the value of
 * this is x-api-key.
 * <p>
 * api.key.value - The api key used to for calling the service. A value like
 * 2b312983-662e-4435-808a-4056ebc4f841 is used.
 * <p>
 * service.name - The name of the microservice. Example - Account service in this case.
 * <p>
 * beans.inspect - A boolean value to print the beans initialized by Spring boot at the
 * startup. This is helpful in debugging. By default its value is O or false.
 * </p>
 *
 * @author Manohar
 * @since 23/Sep/2023
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "system_config")
public class SystemConfig {

    @Id
    @Column(name = "name")
    String name;

    @Column(name = "value")
    String value;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @CreationTimestamp
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = false, updatable = false)
    private Date dateCreated;

    @UpdateTimestamp
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_updated", nullable = false)
    private Date dateUpdated;

    /**
     * SystemConfig constructor
     *
     * @param name  is the name of the system config
     * @param value is the value of the system config
     */
    public SystemConfig(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
