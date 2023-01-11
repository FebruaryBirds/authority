package com.common.verify.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@Service
public class JwtUtil {

    private static Algorithm algorithm = null;

    private static String key = "month-clear";

    static{
        algorithm = Algorithm.HMAC256("month-clear-jwt-secret");
    }

    /**
     * 生成带有过期时间的token
     * @param json token需要包含的内容
     * @param expireTime 过期时间(单位是分钟)
     * @return 生成的token
     */
    public static String generateToken(String json, int expireTime){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE,expireTime);
        String token;
        if(expireTime==-1){
            token = JWT.create()
                    .withIssuer(key)
                    .withSubject(json)
                    .sign(algorithm);
        }else {
            token = JWT.create()
                    .withIssuer(key)
                    .withSubject(json)
                    .withExpiresAt(calendar.getTime())
                    .sign(algorithm);
        }
        return token;
    }

    /**
     * 验证token是否有效
     * @param token
     * @return 验证结果
     */
    public static boolean verifyToken(String token){
        try{
            if (token==null){
                return false;
            }else {
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer(key)
                        .build();
                verifier.verify(token);
            }

        }catch (JWTVerificationException ex){
            log.warn(ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 获取token的内容
     * @param token
     * @return 保持在token中的内容
     */
    public static String getTokenContent(String token){
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(key)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }

    /**
     * 更新token的过期时间
     * @param token
     * @param expireTime
     * @return 更新后的token
     */
    public static String refreshToken(String token, int expireTime){
        String json = getTokenContent(token);
        return generateToken(json, expireTime);
    }

    public static void main(String[] args) {
        System.out.println(RandomStringUtils.randomAlphanumeric(6));
    }
}
