package com.racanaa.services.account.security.apikey;

import com.racanaa.services.account.persistance.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ApiKey object for storing in context and api key authentication
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiKey {
    private String headerName;
    private String value;
    private Account account;
}
