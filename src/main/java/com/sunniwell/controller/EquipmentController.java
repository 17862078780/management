package com.sunniwell.controller;

import com.sunniwell.common.entity.PageResult;
import com.sunniwell.common.entity.Result;
import com.sunniwell.common.entity.StatusCode;
import com.sunniwell.common.entity.pojo.Equipment;
import com.sunniwell.common.entity.pojo.Role;
import com.sunniwell.servece.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 16:14
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    /**
     * 功能描述: 新增
     *
     * @auther: 孟德坤
     */
    @PostMapping
    public Object add(@RequestBody Equipment equipment) {
        equipmentService.add(equipment);
        return new Result(true, StatusCode.OK,"保存成功");
    }

    /**
     * 修改
     * @return
     */
    @PutMapping
    public Object update(@RequestBody Equipment equipment) {
        return new Result(true, StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable String id) {
        return new Result(true, StatusCode.OK,"删除成功");
    }
    @PostMapping(value = "/search/{page}/{size}")
    public Result comment(@PathVariable int page,@PathVariable int size,@RequestBody Map<String,String> searchMap){
        PageResult pageResult = equipmentService.search( searchMap, page, size);;
        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }

}
