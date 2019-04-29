package com.sunniwell.servece;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/29 13:38
 * @Description:
 */
public interface AsyncService {

    /**
     *
     * 功能描述: 批量插入模拟数据
     *
     * @param:
     * @return:
     * @auther: 孟德坤
     * @date: 2019/4/29 13:42
     */
    void saveAll(CountDownLatch countDownLatch);
}
