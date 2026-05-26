package com.clouddine.orderservice.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtConfig {

    private static final String SECRET = "your-secret-key-here-change-it";

    @Bean
    public JwtDecoder jwtDecoder() {

        SecretKeySpec key = new SecretKeySpec(
                SECRET.getBytes(),
                "HmacSHA256"
        );

        return NimbusJwtDecoder.withSecretKey(key).build();
    }
}