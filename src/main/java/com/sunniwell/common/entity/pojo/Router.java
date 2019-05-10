package com.sunniwell.common.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * @Auther: 孟德坤
 * @Date: 2019/5/10 13:40
 * @Description: 一级路由
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Router {
    @Id
    private String rid;
    private String path;
    private String component;
    private String redirect;
    private String name;
    private Object meta;
    private Boolean hidden;
    @DBRef
    private List<ChildRouter> children;
}
