package com.sunniwell;

import com.sunniwell.servece.AsyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/29 11:35
 * @Description: 模拟插入100万条数据
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestThread {
//    等待一百个以后down
    private final CountDownLatch countDownLatch = new CountDownLatch(1000);
    @Autowired
    AsyncService asyncService;

    @Test
    public void test() {
        Long begin = new Date().getTime();
        try {
            for (int i = 0; i < 1000; i++) {
                asyncService.saveAll(countDownLatch);
                System.out.println("当前线程" + i);
            }
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.err.println("1000条数据插入花费时间 : " + (end - begin)  + " ms" + "  插入完成");
    }
}
