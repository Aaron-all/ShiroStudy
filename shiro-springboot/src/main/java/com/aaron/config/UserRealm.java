package com.aaron.config;

import com.aaron.pojo.Userguang;
import com.aaron.service.UserService;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @BelongsProject: SpringBoot-08-Shiro
 * @BelongsPackage: com.aaron.config
 * @author: Andy
 * @date: 2020.12.8 13:53
 * @Description: 自定义Realm
 * @since JDK 1.8
 */
public class UserRealm extends AuthorizingRealm {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 授权:进入被拦截的页面就会进入授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        加上权限
//        info.addStringPermission("user:add");
//        拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        Userguang principal = (Userguang)subject.getPrincipal();//拿到user对象
        if(principal.getPerms().equals("user:all")){
            info.addStringPermission("user:add");//设置当前用户的权限
            info.addStringPermission("user:update");//设置当前用户的权限
        }
        info.addStringPermission(principal.getPerms());//设置当前用户的权限
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        Userguang userguang = userService.queryUserByName(usernamePasswordToken.getUsername());
        if (userguang.getName() == null){
            return null;        // 抛出异常UnknownAccountException
        }
//        登录成功后，把用户的信息方法Session中
//            1、获取当前对象
        Subject currentSubject = SecurityUtils.getSubject();
//            2、获取当前对象的Session
        Session session = currentSubject.getSession();
//            3、设置把登录成功的信息放到Session中
        session.setAttribute("loginUser",userguang);
//        密码认证  MD5加密   MD5盐值加密
        return new SimpleAuthenticationInfo(userguang,userguang.getPwd(),"");
    }
}
