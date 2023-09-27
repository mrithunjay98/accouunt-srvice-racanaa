package com.racanaa.services.account.security.apikey;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Class for API Key based authentication
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
public class ApiKeyAuthentication extends AbstractAuthenticationToken {
    private final ApiKey apiKey;

    public ApiKeyAuthentication(ApiKey apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKey = apiKey;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}
