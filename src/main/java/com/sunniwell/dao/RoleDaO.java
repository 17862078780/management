package com.sunniwell.dao;

import com.sunniwell.common.entity.pojo.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Auther: 孟德坤
 * @Date: 2019/5/10 17:11
 * @Description:
 */
public interface RoleDaO extends MongoRepository<Role,String> {

}
