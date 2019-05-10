package com.sunniwell.dao;

import com.sunniwell.common.entity.pojo.Router;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Auther: 孟德坤
 * @Date: 2019/5/10 17:14
 * @Description:
 */
public interface RouterDao extends MongoRepository<Router,String> {
}
