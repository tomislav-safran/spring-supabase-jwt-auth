package com.tsafran.springsupabasejwtauth.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtClaimsProvider {
    private final ObjectMapper objectMapper;

    public String getClaimsJson() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return null;

        Object principal = auth.getPrincipal();
        if (!(principal instanceof Jwt jwt)) return null;

        Map<String, Object> claims = new HashMap<>(jwt.getClaims());

        try {
            return objectMapper.writeValueAsString(claims);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not serialize JWT claims", e);
        }
    }
}
