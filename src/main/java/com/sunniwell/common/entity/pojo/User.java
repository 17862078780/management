package com.sunniwell.common.entity.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 10:57
 * @Description:
 */
@Data
@Document(collection = "user")
public class User implements Serializable {
    @Id
    private String _id;
//    用户名/密码/性别/头像/联系方式/出生日期/爱好/住址/...
    private String username;
    private String password;
    private String sex;
    private String avatar;//头像
    private String mobile;//手机号码
    private Date birthday;//出生日期
    private String interest;//兴趣
    private String addresss;
//    角色
    private String role;
}
