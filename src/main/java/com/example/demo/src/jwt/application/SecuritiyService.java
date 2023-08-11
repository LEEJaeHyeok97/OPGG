package com.example.demo.src.jwt.application;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecuritiyService {
    private static String SECRET_KEY;

    public SecuritiyService(@Value("${SECRET_KEY}") String secretKey) {
        this.SECRET_KEY = secretKey;
    }

    //로그인 서비스 던질때 같이
    public String createToken(String subject, long expTime) {
        if (expTime <= 0) {
            throw new RuntimeException("만료시간이 0보다 커야합니다.");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();
    }

    //토큰 검증
    public String getSubject(String token) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
