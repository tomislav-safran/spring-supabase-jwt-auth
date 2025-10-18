package com.tsafran.springsupabasejwtauth.context;

import com.tsafran.springsupabasejwtauth.security.JwtClaimsProvider;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class RlsAspect {

    private final JwtClaimsProvider jwtClaimsProvider;
    private final RlsContextSetter rlsContextSetter;

    @Pointcut("@within(org.springframework.transaction.annotation.Transactional) || " +
            "@annotation(org.springframework.transaction.annotation.Transactional)")
    public void transactionalPointcut() {}

    @Before("transactionalPointcut()")
    public void setPostgresContext() {
        String claimsJson = jwtClaimsProvider.getClaimsJson();
        if (claimsJson != null) {
            rlsContextSetter.applyClaimsJson(claimsJson);
        }
    }

}
