package com.racanaa.services.account.security.jwt;

import com.racanaa.services.account.persistance.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Token object for storing in context and JWT token authentication
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String headerName;
    private int expirationSeconds;
    private String secret;
    private User user;
}