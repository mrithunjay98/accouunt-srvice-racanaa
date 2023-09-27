package com.racanaa.services.account.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Class for JWT token based authentication
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
public class TokenAuthentication extends AbstractAuthenticationToken {
    private final Token token;

    public TokenAuthentication(Token token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}

