package com.aaron.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @BelongsProject: SpringBoot-08-Shiro
 * @BelongsPackage: com.aaron.controller
 * @author: Andy
 * @date: 2020.12.8 13:27
 * @Description: 首页控制器
 * @since JDK 1.8
 */
@Controller
public class MyController {
    @RequestMapping("/")
    public String toIndex(Model model){
        model.addAttribute("msg","Hello,Shiro");
        return "index";
    }
    @RequestMapping("user/add")
    public String add(){
        return "User/add";
    }
    @RequestMapping("user/update")
    public String update(){
        return "User/update";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(String username,String password,Model model){
//        获取当前的用户
        Subject subject = SecurityUtils.getSubject();
//        封装用户的登录数据(令牌)
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);       // 执行登录方法，如果没有异常就说明OK了
            return "index";
        } catch (UnknownAccountException uae) { // 用户名不存在
            model.addAttribute("massage","用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException ice) {  //  密码不对
            model.addAttribute("massage","密码不对");
            return "login";
        } catch (LockedAccountException lae) {      // 用户名被锁定
            model.addAttribute("massage","用户名被锁定");
            return "login";
        }
        catch (AuthenticationException ae) {        // 认证异常
            model.addAttribute("massage","认证异常");
            return "login";
        }
    }
    @ResponseBody
    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "未经授权无法访问此页面";
    }
    @RequestMapping("/logout")
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
