package com.tsafran.springsupabasejwtauth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.ExpressionJwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll()
                        .anyRequest().hasRole("authenticated")
                ).oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))

                .build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        var scopes = new JwtGrantedAuthoritiesConverter(); // default: claim "scope"/"scp", prefix "SCOPE_"

        var roles = new JwtGrantedAuthoritiesConverter();
        roles.setAuthoritiesClaimName("role");
        roles.setAuthorityPrefix("ROLE_");

        // Optional expression to map custom user roles created with Supabase Auth Hooks, to Spring Security Roles
        SpelExpression expression = new SpelExpressionParser().parseRaw("[app_metadata][roles]");
        var customRoles = new ExpressionJwtGrantedAuthoritiesConverter(expression);

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var out = new ArrayList<GrantedAuthority>();
            out.addAll(scopes.convert(jwt));
            out.addAll(roles.convert(jwt));
            out.addAll(customRoles.convert(jwt));
            return out;
        });
        return converter;
    }

}
