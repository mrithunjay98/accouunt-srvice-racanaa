package com.racanaa.services.account.security;

import com.racanaa.services.account.persistance.manager.AccountManager;
import com.racanaa.services.account.persistance.manager.UserManager;
import com.racanaa.services.account.persistance.model.Account;
import com.racanaa.services.account.persistance.model.User;
import com.racanaa.services.account.security.apikey.ApiKey;
import com.racanaa.services.account.security.apikey.ApiKeyAuthentication;
import com.racanaa.services.account.security.jwt.Token;
import com.racanaa.services.account.security.jwt.TokenAuthentication;
import com.racanaa.services.account.util.SecurityUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import static com.racanaa.services.account.constant.ApiConstant.*;

/**
 * Authentication service to validate api key and JWT token
 *
 * @author Manohar
 * @since 26/Sep/2023
 */
@Slf4j
public class AuthenticationService {

    /**
     * Authenticates an api key and sets the account detail in context and the request attributes
     *
     * @param request HttpServletRequest
     * @param context ApplicationContext
     * @return Authentication
     */
    public static Authentication authenticateApiKey(HttpServletRequest request, ApplicationContext context) {
        ApiKey contextApiKeyObject = context.getBean(ApiKey.class);
        String dbApiKeyName = contextApiKeyObject.getHeaderName();
        String apiKey = request.getHeader(dbApiKeyName);
        if (apiKey == null) {
            throw new BadCredentialsException(AUTH_API_KEY_REQUIRED);
        }
        AccountManager accountManager = context.getBean(AccountManager.class);
        Account account = accountManager.getByApiKey(apiKey);
        if (account == null) {
            throw new BadCredentialsException(AUTH_API_KEY_INVALID);
        } else {
            request.setAttribute(REQUEST_ATTRIBUTE_ACCOUNT_KEY, account);
            contextApiKeyObject.setAccount(account);
        }
        return new ApiKeyAuthentication(contextApiKeyObject, AuthorityUtils.NO_AUTHORITIES);
    }

    /**
     * Authenticates a JWT token and sets the user detail in context and the request attributes
     *
     * @param request HttpServletRequest
     * @param context ApplicationContext
     * @return Authentication
     */
    public static Authentication authenticateToken(HttpServletRequest request, ApplicationContext context) {
        Token contextTokenObject = context.getBean(Token.class);
        String dbTokenHeaderName = contextTokenObject.getHeaderName();
        String dbJwtSecret = contextTokenObject.getSecret();
        int dbJwtExpiry = contextTokenObject.getExpirationSeconds();
        String jwtToken = request.getHeader(dbTokenHeaderName);
        //TODO: Remove it once auth service is implemented
        String token = SecurityUtil.generateToken("402881888ac3ce6b018ac3ce6ffd000b", dbJwtSecret, dbJwtExpiry);
        log.info(AUTH_TOKEN_BEARER_TEXT + token);

        if (jwtToken == null) {
            throw new BadCredentialsException(AUTH_TOKEN_REQUIRED);
        }
        try {
            Claims claims = SecurityUtil.validateToken(jwtToken, dbJwtSecret);
            if (claims == null) {
                throw new BadCredentialsException(AUTH_TOKEN_INVALID);
            }
            if (SecurityUtil.isTokenExpired(claims)) {
                throw new BadCredentialsException(AUTH_TOKEN_EXPIRED);
            }
            String userId = claims.getSubject();
            UserManager userManager = context.getBean(UserManager.class);
            User user = userManager.getById(userId);
            Account account = (Account) request.getAttribute(REQUEST_ATTRIBUTE_ACCOUNT_KEY);
            if (user != null && user.getTenantId().equalsIgnoreCase(account.getTenantId())) {
                request.setAttribute(REQUEST_ATTRIBUTE_USER_KEY, user);
                contextTokenObject.setUser(user);
            } else {
                throw new BadCredentialsException(AUTH_INVALID_TENANT);
            }
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
        return new TokenAuthentication(contextTokenObject, AuthorityUtils.NO_AUTHORITIES);
    }
}