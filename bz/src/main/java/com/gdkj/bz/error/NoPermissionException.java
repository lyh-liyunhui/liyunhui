package com.gdkj.bz.error;

import com.gdkj.bz.response.CommonReturnType;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by DELL on 2020/2/25.
 */
@ControllerAdvice
public class NoPermissionException {
    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception ex) {
        return CommonReturnType.success("404");
    }
    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(Exception ex) {
        return CommonReturnType.success("404");
    }
}
