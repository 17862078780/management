package com.sunniwell.servece.impl;

import com.sunniwell.common.entity.PageResult;
import com.sunniwell.common.entity.Result;
import com.sunniwell.common.entity.StatusCode;
import com.sunniwell.common.entity.pojo.User;
import com.sunniwell.dao.UserDao;
import com.sunniwell.servece.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    @Override
    public PageResult search(User user, int i, int size) {
        PageResult pageResult = new PageResult();
//        排序条件
        Sort sort = Sort.by(Sort.Direction.DESC, "_id");
//        分页信息
        Pageable pageable = PageRequest.of(i, size, sort);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()) //采用“包含匹配”的方式查询
                .withIgnorePaths("password");  //忽略属性，不参与查询

        //创建实例
        Example<User> example = Example.of(user, matcher);
        Page<User> userPage = userDao.findAll(example, pageable);

        pageResult.setTotal(userPage.getTotalElements());
        pageResult.setRows(userPage.getContent());
        return pageResult;

    }


}
