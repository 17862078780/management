package com.sunniwell.common.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: 孟德坤
 * @Date: 2019/5/6 11:11
 * @Description:
 */
@Data
public class UserInfo implements Serializable {

    /**
     * roles : ["admin"]
     * role : ["admin"]
     * name : admin
     * avatar : https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif
     */

    private String name;
    private String avatar;
    private List<String> roles;
    private List<String> role;


}
