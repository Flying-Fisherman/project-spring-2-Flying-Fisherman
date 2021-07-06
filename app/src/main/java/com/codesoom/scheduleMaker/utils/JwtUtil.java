package com.codesoom.scheduleMaker.utils;

import com.codesoom.scheduleMaker.errors.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * 토큰의 인코딩, 디코딩을 담당합니다.
 */
@Component
public class JwtUtil {
    private final Key key;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * 토큰을 인코딩합니다.
     *
     * @param userUid 토큰에 포함 할 회원의 UID
     * @return 회원의 접속 토큰
     */
    public String encode(Long userUid) {
        return Jwts.builder()
                .claim("userUid", userUid)
                .signWith(key)
                .compact();
    }

    /**
     * 토큰을 디코딩합니다.
     *
     * @param token 디코딩 할 토큰
     * @return 디코딩 된 정보의 Body
     * @throws InvalidTokenException 올바르지 않은 토큰 일 경우
     * @exception SignatureException
     */
    public Claims decode(String token) {
        if (token == null || token.isBlank()) {
            throw new InvalidTokenException(token);
        }

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new InvalidTokenException(token);
        }
    }
}
