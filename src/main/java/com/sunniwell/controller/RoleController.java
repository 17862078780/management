package com.sunniwell.controller;

import com.sunniwell.common.entity.PageResult;
import com.sunniwell.common.entity.Result;
import com.sunniwell.common.entity.StatusCode;
import com.sunniwell.common.entity.pojo.Role;
import com.sunniwell.servece.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 14:49
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 功能描述: 新增
     *
     * @auther: 孟德坤
     * @date: 2019/4/28 14:49
     */
    @PostMapping
    public Object add(@RequestBody Role rele) {
        return new Result(true, StatusCode.OK,"保存成功");
    }

    /**
     * 修改
     * @param rele
     * @return
     */
    @PutMapping
    public Object update(@RequestBody Role rele) {
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
    @GetMapping(value = "/{page}/{size}")
    public Result comment(@PathVariable int page,@PathVariable int size){
        Page<Role> page1 = roleService.findAll(page, size);
        PageResult pageResult = new PageResult(page1.getTotalElements(),page1.getContent());
        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }
}
