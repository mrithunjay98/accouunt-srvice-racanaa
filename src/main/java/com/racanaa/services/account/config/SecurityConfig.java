package com.racanaa.services.account.config;

import com.racanaa.services.account.persistance.manager.SystemConfigManager;
import com.racanaa.services.account.security.apikey.ApiKey;
import com.racanaa.services.account.security.apikey.ApiKeyAuthenticationFilter;
import com.racanaa.services.account.security.jwt.Token;
import com.racanaa.services.account.security.jwt.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.racanaa.services.account.constant.ApiConstant.*;

/**
 * SecurityConfig for securing api endpoints
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private SystemConfigManager systemConfigManager;

    /**
     * Bean to initialize security filter chain. It adds API Key and JWT based authentication mechanism to filter chain.
     *
     * @return
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                       .csrf(csrf -> csrf.disable())
                       .securityMatcher("/api/**")
                       .httpBasic(Customizer.withDefaults())
                       .addFilterBefore(new ApiKeyAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                       .addFilterBefore(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                       .build();
    }

    /**
     * Bean to initialize BCryptPasswordEncoder
     *
     * @return
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean to initialize ApiKey from DB configurations. It reads the API key name used from the System Config table and
     * uses it for reading request headers value.
     *
     * @return
     */
    @Bean
    public ApiKey setApiKeys() {
        ApiKey aKey = new ApiKey();
        aKey.setHeaderName(systemConfigManager.getString(DB_API_KEY_HEADER_NAME));
        aKey.setValue(systemConfigManager.getString(DB_API_KEY_VALUE));
        return aKey;
    }

    /**
     * Bean to initialize Token from DB configurations. It reads the JWT configs from the System Config table and
     * uses it for generating and validating JWT tokens.
     *
     * @return
     */
    @Bean
    public Token setToken() {
        Token token = new Token();
        token.setHeaderName(systemConfigManager.getString(DB_JWT_TOKEN_HEADER_NAME));
        token.setSecret(systemConfigManager.getString(DB_JWT_TOKEN_SECRET));
        token.setExpirationSeconds(systemConfigManager.getIntValue(DB_JWT_TOKEN_EXPIRY_SECONDS, DEFAULT_TOKEN_EXPIRY_SECONDS_DAY));
        return token;
    }
}