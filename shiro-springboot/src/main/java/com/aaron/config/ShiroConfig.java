package com.aaron.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.security.pkcs11.wrapper.Constants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @BelongsProject: SpringBoot-08-Shiro
 * @BelongsPackage: com.aaron.config
 * @author: Andy
 * @date: 2020.12.8 13:51
 * @Description: Shiro的配置类
 * @since JDK 1.8
 */
@Configuration
public class ShiroConfig{
    // ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean =  new ShiroFilterFactoryBean();
//        设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //        添加Shiro的内置过滤器
        /**
         * anon:   无须认证就可以访问
         * authc:   必须认证了才能用
         * user:    必须拥有记住我功能才能用
         * perms:   拥有对某个资源的权限才能访问
         * role:    拥有某个权限才能访问
         */
//        拦截
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        授权:正常都会跳转到未授权页面（定义权限）
        filterChainDefinitionMap.put("/user/add","perms[user:add]");
        filterChainDefinitionMap.put("/user/update","perms[user:update]");

        filterChainDefinitionMap.put("/user/*","authc");
        filterChainDefinitionMap.put("/logout","logout");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        设置登录请求
        bean.setLoginUrl("/toLogin");
//        设置未授权的请求
        bean.setUnauthorizedUrl("/unauthorized");
        return bean;
    }
    // DafaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    // 创建realm对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

//    整合Thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
