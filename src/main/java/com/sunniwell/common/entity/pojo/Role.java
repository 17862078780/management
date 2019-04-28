package com.sunniwell.common.entity.pojo;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 14:51
 * @Description:
 */

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "role")
public class Role {
    @Id
    private String _id;
    private String name;
    private String vName;
//    对应资源
    private String resource;

}
