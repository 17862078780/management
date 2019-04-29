package com.sunniwell;

import com.sunniwell.common.utils.MyThread;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/29 11:35
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestThread {
    @Autowired
    private MyThread myThread;
    @Test
    public void test(){
        for (int i = 0; i <11 ; i++) {
            myThread.saveAll();

            System.out.println("当前线程"+i);
        }

    }
}
