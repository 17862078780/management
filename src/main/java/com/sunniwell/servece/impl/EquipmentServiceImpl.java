package com.sunniwell.servece.impl;

import com.sunniwell.common.entity.PageResult;
import com.sunniwell.common.entity.pojo.Equipment;
import com.sunniwell.dao.EquipmentDao;
import com.sunniwell.servece.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 16:25
 * @Description:
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public PageResult<Equipment> search(Map<String, String> searchMap, int page, int size) {
        //排序
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        //分页信息
        Pageable pageable = PageRequest.of(page, size, sort);
        Query query = new Query();
        Criteria criteria = new Criteria();
        // 多字段查询
        // 在线状态
        if (!StringUtils.isEmpty(searchMap.get("status")) ){
            criteria.and("status").is(searchMap.get("status"));
        }
        // 厂商
        if (!StringUtils.isEmpty(searchMap.get("vendor"))) {
            criteria.and("vendor").is(searchMap.get("vendor"));
        }
        // SN
        if (!StringUtils.isEmpty(searchMap.get("sn"))) {
            criteria.and("sn").is(searchMap.get("sn"));
        }
        // MAC
        if (!StringUtils.isEmpty(searchMap.get("mac"))) {
            criteria.and("mac").is(searchMap.get("mac"));
        }
        // 型号
        if (!StringUtils.isEmpty(searchMap.get("model"))) {
            criteria.and("model").is(searchMap.get("model"));
        }
        Criteria gt = new Criteria();
        Criteria lt = new Criteria();
        // 活跃时间段
        if (!StringUtils.isEmpty(searchMap.get("activeTime"))) {
            gt.where("activeTime").gt(searchMap.get("activeTime"));
        }
        if (!StringUtils.isEmpty(searchMap.get("registrationTime"))) {
            lt.where("registrationTime").lt(searchMap.get("registrationTime"));
        }
        if (gt != null && lt != null) {
            criteria.andOperator(gt, lt);
        } else if (gt != null) {
            criteria.andOperator(gt);
        } else if (lt != null) {
            criteria.andOperator(lt);
        }

        query.addCriteria(criteria);
        Long count = mongoTemplate.count(query, Equipment.class);
        // 查询
        List<Equipment> list = mongoTemplate.find(query, Equipment.class);

        // 将集合与分页结果封装
        PageResult pagelist = new PageResult();
        pagelist.setRows(list);
        pagelist.setTotal(count);
        return pagelist;
    }

    @Override
    public void add(Equipment equipment) {
      equipmentDao.save(equipment);
    }

    @Override
    public Equipment findOne(String id) {

        return equipmentDao.findById(id).get();
    }

    @Override
    public void deleteById(String id) {
        equipmentDao.deleteById(id);
    }
}
