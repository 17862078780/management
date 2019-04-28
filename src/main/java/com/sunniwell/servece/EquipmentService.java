package com.sunniwell.servece;

import com.sunniwell.common.entity.PageResult;
import com.sunniwell.common.entity.pojo.Equipment;
import com.sunniwell.common.entity.pojo.Role;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 16:25
 * @Description:
 */
public interface EquipmentService {
    PageResult<Equipment> search(Map<String, String> searchMap, int page, int size);

    void add(Equipment equipment);
}