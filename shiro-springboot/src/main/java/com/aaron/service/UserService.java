package com.aaron.service;

import com.aaron.pojo.Userguang;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: SpringBoot-08-Shiro
 * @BelongsPackage: com.aaron.service
 * @author: Andy
 * @date: 2020.12.8 16:07
 * @Description: 登录的业务层
 * @since JDK 1.8
 */
public interface UserService {
    /**
     * 通过用户名登录
     * @return
     */
    Userguang queryUserByName(String name);
}
