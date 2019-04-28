package com.sunniwell.servece.impl;

import com.sunniwell.common.entity.pojo.Role;
import com.sunniwell.servece.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 16:23
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public Page<Role> findAll(int page, int size) {
        return null;
    }
}
