package com.racanaa.services.account.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

import static com.racanaa.services.account.constant.ApiConstant.PASSWORD_SECRET;
/**
 * A utility class for password related operations
 *
 * @author Manohar
 * @since 23/Sep/2023
 *
 */
@Slf4j
@Service
public class PasswordUtil {
    private static PasswordEncoder passwordEncoder;

    public static PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    //TODO: Need to figure out a better way to handle this.
    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        passwordEncoder = encoder;
    }

    /**
     * generateSalt - Generates a password salt for the text using a password secret
     *
     * @param rawPassword raw password
     * @return String password salt
     */
    public static String generateSalt(String rawPassword) {
        if(StringUtil.isEmpty(rawPassword)) return null;
        String encodedString = Base64.getEncoder().encodeToString(rawPassword.getBytes());
        return new StringBuilder(PASSWORD_SECRET)
                .append("#")
                .append(encodedString)
                .append("#")
                .append(PASSWORD_SECRET).toString();
    }

    /**
     * encodePassword - Encode the raw password.
     *
     * @param rawPassword raw password
     * @return encoded password
     */
    public static String encodePassword(String rawPassword) {
        if(StringUtil.isEmpty(rawPassword)) return null;
        return getPasswordEncoder().encode(generateSalt(rawPassword));
    }

    /**
     * passwordMatches - Checks if the raw and encoded passwords matches
     *
     * @param encodedPassword encoded password
     * @param rawPassword raw password
     * @return encoded password
     */
    public static boolean passwordMatches(String encodedPassword, String rawPassword) {
        if(StringUtil.isEmpty(encodedPassword)  || StringUtil.isEmpty(rawPassword)) return false;
        return getPasswordEncoder().matches(encodedPassword, generateSalt(rawPassword));
    }

    /**
     * base64Encode - Encodes a text into base64
     *
     * @param text raw text
     * @return base64 encoded text
     */
    public static String base64Encode(String text) {
        if(StringUtil.isEmpty(text)) return null;
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    /**
     * base64Decode - Decodes a base64 encoded text to plain text
     *
     * @param encodedText base64 encoded text
     * @return plain text
     */
    public static String base64Decode(String encodedText) {
        if(StringUtil.isEmpty(encodedText)) return null;
        byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
        return new String(decodedBytes);
    }

}
