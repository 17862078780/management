package com.sunniwell.servece;


import com.sunniwell.common.entity.pojo.User;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 10:46
 * @Description:
 */
public interface UserService {
    User logoIn(String loginname, String password);

    Object register(User user);

    User findOne(String id);

    void update(User user);

    void deleteById(String id);
}
