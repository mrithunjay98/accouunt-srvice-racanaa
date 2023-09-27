package com.racanaa.services.account.util;

import com.racanaa.services.account.exception.ErrorResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Date;

import static com.racanaa.services.account.constant.ApiConstant.*;

/**
 * A utility class for security & filter
 *
 * @author Manohar
 * @since 23/Sep/2023
 */
@Slf4j
public class SecurityUtil {

    /**
     * Private constructor to avoid new()
     */
    private SecurityUtil() {
        throw new IllegalStateException(UTILITY_CLASS_NO_CONSTRUCTOR);
    }

    /**
     * Sets the json string to response object
     *
     * <b>Note!<b/>
     * <p>
     * This is required to be added from filters as the Global exception handler i.e controller advice doesn't work.
     * This handles the issue of conversion of ErrorResponse at the filter level.
     * </p>
     *
     * @param status
     * @param response
     * @param ex
     */
    public static void setFilterErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex) {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorResponse er = new ErrorResponse(status, ex.getMessage());
        try {
            String json = er.convertToJson();
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Validates the JWT token using the secret
     *
     * @param jwtToken  JWT token
     * @param jwtSecret JWT secret
     * @return
     */
    public static Claims validateToken(String jwtToken, String jwtSecret) {
        String token = jwtToken.replaceAll(AUTH_TOKEN_BEARER_TEXT, EMPTY_STRING);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * Generates a JWT token
     *
     * @param userId            user id
     * @param jwtSecret         JWT secret
     * @param expirationSeconds token expiry time in seconds
     * @return
     */
    public static String generateToken(String userId, String jwtSecret, int expirationSeconds) {
        String jwtToken = Jwts.builder().setSubject(userId)
                                  .claim("roles", "user")
                                  .setIssuedAt(new Date())
                                  .signWith(SignatureAlgorithm.HS256, jwtSecret)
                                  .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                                  .compact();
        return jwtToken;
    }

    /**
     * Checks if a JWT claim has expired
     *
     * @param claims JWT claim object which
     * @return
     */
    public static Boolean isTokenExpired(Claims claims) {
        Date expirationDate = claims.getExpiration();
        Date currentDate = new Date();
        return expirationDate.before(currentDate);
    }
}
