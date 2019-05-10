package com.sunniwell.common.entity.pojo;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 14:51
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "role")
    public class Role implements Serializable {
    @Id
    private String rid;
    private String name;
//    对应中文描述eg超级管理员
    private String desc;
//    对应资源
    @DBRef
    private List<Router> resource;

}
