package com.aaron.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: SpringBoot-08-Shiro
 * @BelongsPackage: com.aaron.pojo
 * @author: Andy
 * @date: 2020.12.8 16:01
 * @Description: 实体类
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userguang {
    private int id;
    private String name;
    private String pwd;
    private String perms;
}
