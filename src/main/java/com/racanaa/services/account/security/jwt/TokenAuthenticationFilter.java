package com.racanaa.services.account.security.jwt;

import com.racanaa.services.account.security.AuthenticationService;
import com.racanaa.services.account.util.SecurityUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * Filter to authenticate JWT token
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
@Slf4j
public class TokenAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            ApplicationContext ctx = WebApplicationContextUtils
                                             .getRequiredWebApplicationContext(request.getServletContext());
            Authentication authentication = AuthenticationService.authenticateToken((HttpServletRequest) request, ctx);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            SecurityUtil.setFilterErrorResponse(HttpStatus.UNAUTHORIZED, httpResponse, e);
        }
    }
}

