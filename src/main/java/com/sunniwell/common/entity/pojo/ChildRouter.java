package com.sunniwell.common.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: 孟德坤
 * @Date: 2019/5/10 13:22
 * @Description: 孩子级路由菜单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildRouter implements Serializable {
    @Id
    private String cid;
    private String path;
    private String component;
    private Object meta;
    private Boolean hidden;
}
