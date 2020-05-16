package com.gdkj.bz.shiro;

import com.gdkj.bz.controller.VO.UserVO;
import com.gdkj.bz.entity.PermissionDO;
import com.gdkj.bz.entity.RoleDO;
import com.gdkj.bz.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DELL on 2020/2/24.
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();

        UserVO userVO= (UserVO) principalCollection.getPrimaryPrincipal();
        for (RoleDO roleDO:userVO.getRole()){
            simpleAuthorizationInfo.addRole(roleDO.getRole());
        }
        for (PermissionDO per:userVO.getPermissions()){
            simpleAuthorizationInfo.addStringPermission(per.getPerName());
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken Token= (UsernamePasswordToken) authenticationToken;
        String telphone=Token.getUsername();

        String password=new String(Token.getPassword());

        UserVO userVO = userService.login(telphone,password);

        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(userVO,userVO.getPassword(), this.getName());

        return simpleAuthenticationInfo;
    }
}
