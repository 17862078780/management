package com.sunniwell;

import com.sunniwell.common.entity.pojo.User;
import com.sunniwell.dao.UserDao;
import com.sunniwell.servece.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.PortUnreachableException;
import java.util.Optional;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/29 09:17
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestQuery {
@Autowired
private UserDao userDao;
    @Test
    public void test1(){
        Optional<User> by_id = userDao.findBy_id("5cd519e19dee78621067db17");
        System.out.println(by_id.get());
    }
}
