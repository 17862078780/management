package com.sunniwell.controller;

import com.sunniwell.common.entity.PageResult;
import com.sunniwell.common.entity.Result;
import com.sunniwell.common.entity.StatusCode;
import com.sunniwell.common.entity.pojo.Equipment;
import com.sunniwell.servece.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
        if (equipment!=null){
        equipmentService.add(equipment);
        return new Result(true, StatusCode.OK,"保存成功");
        }
        return new Result(true, StatusCode.ERROR,"新增失败");
    }

    /**
     * 修改
     * @return
     */
    @PutMapping
    public Object update(@RequestBody Equipment equipment) {

        if(!StringUtils.isEmpty(equipment.get_id())){

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
        PageResult pageResult = equipmentService.search( searchMap, page, size);
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
