package com.sunniwell.dao;

import com.sunniwell.common.entity.pojo.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 16:51
 * @Description:
 */
public interface EquipmentDao extends MongoRepository<Equipment,String> {

}
