package com.sunniwell.dao;

import com.sunniwell.common.entity.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 11:16
 * @Description:
 */
public interface UserDao extends MongoRepository<User,Long> {


    User findByUsername(String loginname);
}
