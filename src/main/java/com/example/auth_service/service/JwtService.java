package com.example.auth_service.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.auth_service.dto.TokenDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;


    private static final String ISSUER = "AUTH-SERVICE";

    public String generateToken(TokenDetail tokenDetail) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(tokenDetail.getAuthRequest().getUserName())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 300000)) //
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Token generation failed", exception);
        }
    }
}
