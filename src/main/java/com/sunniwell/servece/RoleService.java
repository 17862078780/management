package com.sunniwell.servece;

import com.sunniwell.common.entity.pojo.Role;
import org.springframework.data.domain.Page;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 16:22
 * @Description:
 */
public interface RoleService {
    Page<Role> findAll(int page, int size);
}
