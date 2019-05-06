package com.sunniwell.servece.impl;

import com.sunniwell.common.entity.Result;
import com.sunniwell.common.entity.StatusCode;
import com.sunniwell.common.entity.pojo.User;
import com.sunniwell.dao.UserDao;
import com.sunniwell.servece.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 10:47
 * @Description:
 */
@Service
public class userServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserDao userDao;

    @Override
    public User logoIn(String username, String password) {
        User user = userDao.findByUsername(username).get();
        if(user!=null&&encoder.matches(password,user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public Object register(User user) {
        userDao.save(user);
        return new Result(true, StatusCode.OK, "注册成功");
    }

    @Override
    public User findOne(String id) {
        return userDao.findBy_id(id).get();
    }


    @Override
    public void update(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteById(String id) {
        userDao.deleteBy_id(id);
    }


}
