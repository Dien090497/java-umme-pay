package vn.unicloud.umeepay.common;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.RoleType;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.ContextService;

import java.lang.reflect.Method;
import java.util.Set;

@Configuration
@Aspect
@RequiredArgsConstructor
public class AuthorizationAspect {

    @Qualifier("cmsAuthorization")
    private final IAuthorization cmsAuthorization;

    @Qualifier("portalAuthorization")
    private final IAuthorization portalAuthorization;

    private final ContextService contextService;

    @Before("@annotation(vn.unicloud.umeepay.common.Authorized)")
    public void authorizeRequest(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Authorized authorizedAnnotation = method.getAnnotation(Authorized.class);
        String[] actions = authorizedAnnotation.actions();

        Set<String> roles = contextService.getLoggedInUserRoles();

        boolean isAuthorized = false;
        if (roles.contains(RoleType.ADMIN.toString())) {
            isAuthorized = cmsAuthorization.authorize(contextService.getLoggedInUserId().orElse(null), actions);
        } else if (roles.contains(RoleType.MERCHANT.toString())) {
            isAuthorized = portalAuthorization.authorize(contextService.getLoggedInUserId().orElse(null), actions);
        }

        if (!isAuthorized) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
