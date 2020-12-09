package com.aaron;

import com.aaron.pojo.Userguang;
import com.aaron.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    void contextLoads() {
        Userguang aaron = userService.queryUserByName("Aaron");
        System.out.println(aaron);
    }

}
