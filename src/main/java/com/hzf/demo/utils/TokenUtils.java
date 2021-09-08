package com.hzf.demo.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Slf4j
public class TokenUtils {
    public final static String DEFAULT_SECRET = "StudyDemo";
    public final static String CLAIM_NAME = "user";
    private final static long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;

    public static String createToken(String user) {
        try {
            return JWT.create().withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .withClaim(CLAIM_NAME, user).sign(Algorithm.HMAC256(DEFAULT_SECRET));
        } catch (JWTCreationException jwtCreationException) {
            log.warn("Token create failed");
            return null;
        }
    }

    public static boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(DEFAULT_SECRET)).build();
            verifier.verify(token);
            return false;
        } catch (JWTVerificationException jwtVerificationException) {
            log.warn("Token verify failed!");
            return true;
        }

    }

    public static String getUserString(String token) {
        try {
            return JWT.decode(token).getClaims().get(CLAIM_NAME).asString();
        } catch (Exception exception) {
            return null;
        }
    }
}
