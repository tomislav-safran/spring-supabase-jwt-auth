# Spring Supabase JWT + RLS Demo

This project demonstrates how to implement **Supabase JWT authentication** in a **Spring Boot** application.

It uses `spring-boot-starter-oauth2-resource-server` for JWT verification. This starter also includes `spring-boot-starter-security` used for authorization.

## How It Works

### 1. **JWT Authentication**

   Supabase issues a JWT.  
   Spring Security validates the token using the Resource Server setup ([`SecurityConfig`](src/main/java/com/tsafran/springsupabasejwtauth/security/SecurityConfig.java)).  
   
   The resource server requires the following properties to validate the JWT:

  ```properties
  spring.security.oauth2.resourceserver.jwt.jwk-set-uri=https://[supabase-id].supabase.co/auth/v1/.well-known/jwks.json
  spring.security.oauth2.resourceserver.jwt.jws-algorithms=ES256
```

- **`jwk-set-uri`** — Specifies the location of the public keys used to verify tokens.  
- **`jws-algorithms`** — Defines the signing algorithm used. Supabase uses `ES256` by default.

---

### 2. RLS Context Propagation

To ensure that database requests from the application comply with the **Postgres RLS policies** configured in Supabase, session variables must be set on the JDBC connection.

- The [`RlsAspect`](src/main/java/com/tsafran/springsupabasejwtauth/context/RlsAspect.java) runs before each `@Transactional` method and extracts the user’s claims from the JWT.  
- The [`RlsContextSetter.applyClaimsJson(String claimsJson)`](src/main/java/com/tsafran/springsupabasejwtauth/context/RlsContextSetter.java) method applies these extracted claims to the JDBC session.

This ensures that each authenticated transaction automatically carries the user’s Supabase claims, allowing Postgres RLS policies to be enforced at the database level.
