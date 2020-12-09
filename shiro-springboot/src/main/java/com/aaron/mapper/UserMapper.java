package com.aaron.mapper;

import com.aaron.pojo.Userguang;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: SpringBoot-08-Shiro
 * @BelongsPackage: com.aaron.mapper
 * @author: Andy
 * @date: 2020.12.8 16:02
 * @Description: 登录数据访问层
 * @since JDK 1.8
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 通过用户名登录
     * @return
     */
    Userguang queryUserByName(String name);
}
