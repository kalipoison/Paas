package com.gohb.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * JWT 生成，校验，过期
 */
public class JWTUtils {

    // 签名密钥（高度保密）
    private static final String SECRET_KEY = "qYYjXt7s1C*nEC%9RCwQGFA$YwPr$Jrj";
    // 签名算法
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    // expire 过期时间
    private static final Long EXPIRE_TIME = 1000 * 60 * 60 * 24l;
    private static Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    /**
     * 生成 token
     */
    public static String generateToken(String username, String password) {
        Date now = new Date(System.currentTimeMillis());
        String jwt = Jwts.builder()
                .setSubject(username) // 主题
                .claim("username", username)
                .claim("password", password)
                .setIssuedAt(now) // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME)) // 过期时间
                .signWith(signatureAlgorithm, SECRET_KEY).compact(); // 加密

        return jwt;
    }

    /**
     * 从 Token 中获取 username
     */
    public static String getUsername(String jwt){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
        return claims.get("username").toString();
    }

    /**
     * 从 Token中获取 password
     */
    public static String getPassword(String jwt){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
        return claims.get("password").toString();
    }


    /**
     * 校验 Token 是否过期
     */
    public static boolean isExpiration(String jwt){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
        return claims.getExpiration().before(new Date());
    }

    public static void main(String[] args) {
        String token = generateToken("wangwu","12345");
        logger.info("token : " + token);
        String username = getUsername(token);
        String password = getPassword(token);
        logger.info("username: " + username);
        logger.info("password: " + password);
    }

}
