package com.sunniwell.dao;

import com.sunniwell.common.entity.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 11:16
 * @Description:
 */
public interface UserDao extends MongoRepository<User,String> {

    Optional<User> findByUsername(String username);

    Optional<User> findBy_id(String id);

    void deleteBy_id(String id);
}
