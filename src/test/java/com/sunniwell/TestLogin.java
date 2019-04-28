package com.sunniwell;

import com.sunniwell.common.entity.pojo.User;
import com.sunniwell.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 11:54
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLogin {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserDao userDao;
//    模拟注册一个用户
    @Test
    public void test01(){
        User u = new User();
        u.setUsername("admin");
        u.setPassword(encoder.encode("admin"));
        u.setAddresss("地址");
        u.setAvatar("头像地址");
        u.setBirthday(new Date());
        u.setInterest("这是一个爱好");
        u.setMobile("1223333");
        u.setRole("ROLE_ADMIN");
        u.setSex("男");
        userDao.save(u);
        System.err.println("保存完成");


    }
}
