package com.aaron.service.impl;

import com.aaron.mapper.UserMapper;
import com.aaron.pojo.Userguang;
import com.aaron.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: SpringBoot-08-Shiro
 * @BelongsPackage: com.aaron.service.impl
 * @author: Andy
 * @date: 2020.12.8 16:08
 * @Description: 实现业务层
 * @since JDK 1.8
 */
@Service
public class UserServiceimpl implements UserService {
    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Userguang queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
