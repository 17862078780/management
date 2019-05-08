package com.sunniwell.servece;

import com.sunniwell.common.entity.PageResult;
import com.sunniwell.common.entity.pojo.Equipment;
import com.sunniwell.common.entity.pojo.EquipmentReqVO;

import java.util.Map;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 16:25
 * @Description:
 */
public interface EquipmentService {
    PageResult<Equipment> search(EquipmentReqVO equipmentReqVO);

    void add(Equipment equipment);

    Equipment findOne(String id);

    void deleteById(String id);

    void update(Equipment equipment);
}
