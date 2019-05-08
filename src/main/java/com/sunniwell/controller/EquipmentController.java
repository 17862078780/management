package com.sunniwell.controller;

import com.alibaba.fastjson.JSON;
import com.sunniwell.common.entity.PageResult;
import com.sunniwell.common.entity.Result;
import com.sunniwell.common.entity.StatusCode;
import com.sunniwell.common.entity.pojo.Equipment;
import com.sunniwell.common.entity.pojo.EquipmentReqVO;
import com.sunniwell.servece.EquipmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        if (equipment!=null){
            if (equipment.getActiveTime()==null){
                equipment.setActiveTime(new Date());
            }
            if (equipment.getRegistrationTime()==null){
                equipment.setRegistrationTime(new Date());
            }
        equipmentService.add(equipment);
        return new Result(true, StatusCode.OK,"保存成功");
        }
        return new Result(true, StatusCode.ERROR,"新增失败");
    }

    /**
     * 修改
     * @return
     */
    @PutMapping("/{id}")
    public Object update(@RequestBody Equipment equipment,@PathVariable String id ) {

        if(!StringUtils.isEmpty(id)){
            equipment.set_id(id);
        equipmentService.update(equipment);
        return new Result(true, StatusCode.OK,"修改成功");
        }
        return new Result(true, StatusCode.ERROR,"数据提交错误");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable String id ) {
        if (!StringUtils.isEmpty(id)){

        equipmentService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
        }
        return new Result(true, StatusCode.ERROR,"删除成功");

    }

    /**
     * 动态条件分页查询
     * @param page
     * @param size
     * @param searchMap
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result comment(@PathVariable int page,@PathVariable int size,@RequestBody Map<String,String> searchMap){
        EquipmentReqVO equipmentReqVO = JSON.parseObject(JSON.toJSONString(searchMap), EquipmentReqVO.class);
        equipmentReqVO.setPage(page-1);
        equipmentReqVO.setSize(size);
        PageResult pageResult = equipmentService.search(equipmentReqVO);
        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Object finOne(@PathVariable String id ) {

        if (!StringUtils.isEmpty(id)){

       Equipment equipment =  equipmentService.findOne(id);

        return new Result(true, StatusCode.OK,"查询成功",equipment);
        }

        return new Result(true, StatusCode.ERROR,"系统错误");

    }

}
