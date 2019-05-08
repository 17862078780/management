package com.sunniwell.servece.impl;

import com.sunniwell.common.entity.PageResult;
import com.sunniwell.common.entity.pojo.Equipment;
import com.sunniwell.common.entity.pojo.EquipmentReqVO;
import com.sunniwell.dao.EquipmentDao;
import com.sunniwell.servece.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
    public PageResult<Equipment> search(EquipmentReqVO equipmentReqVO) {
        PageResult<Equipment> pageResult = new PageResult<>();

        Sort sort = Sort.by(Sort.Direction.DESC, "activeTime");
        Pageable pageable = PageRequest.of(equipmentReqVO.getPage(), equipmentReqVO.getSize(), sort);

        Query query = new Query();

        // 多字段查询
        // 在线状态
        if (!StringUtils.isEmpty(equipmentReqVO.getStats())) {
            query.addCriteria(Criteria.where("stats").is(equipmentReqVO.getStats()));
        }
        // 厂商
        if (!StringUtils.isEmpty(equipmentReqVO.getVendor())) {
            query.addCriteria(Criteria.where("vendor").is(equipmentReqVO.getVendor()));
        }
        // SN
        if (!StringUtils.isEmpty(equipmentReqVO.getSn())) {
            query.addCriteria(Criteria.where("sn").is(equipmentReqVO.getSn()));
        }
        // MAC
        if (!StringUtils.isEmpty(equipmentReqVO.getMac())) {
            query.addCriteria(Criteria.where("mac").is(equipmentReqVO.getMac()));
        }
        // 型号
        if (!StringUtils.isEmpty(equipmentReqVO.getModel())) {
            query.addCriteria(Criteria.where("model").is(equipmentReqVO.getModel()));
        }

        // 活跃时间段
        if (!StringUtils.isEmpty(equipmentReqVO.getStartTime())) {
            query.addCriteria(Criteria.where("activeTime").gt(equipmentReqVO.getStartTime()));
        }
        if (!StringUtils.isEmpty(equipmentReqVO.getEndTime())) {
            query.addCriteria(Criteria.where("activeTime").lt(equipmentReqVO.getEndTime()));
        }

        //计算总数
        long total = mongoTemplate.count(query, Equipment.class);
        //查询结果集
        List<Equipment> list = mongoTemplate.find(query.with(pageable), Equipment.class);
        // 将集合与分页结果封装
        pageResult.setTotal(total);
        pageResult.setRows(list);
        return pageResult;
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

    @Override
    public void update(Equipment equipment) {
        equipmentDao.save(equipment);
    }
}
