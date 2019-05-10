package com.sunniwell.controller;


import com.sunniwell.common.entity.*;
import com.sunniwell.common.entity.pojo.EquipmentReqVO;
import com.sunniwell.common.entity.pojo.Role;
import com.sunniwell.common.utils.JwtUtil;
import com.sunniwell.servece.UserService;
import com.sunniwell.common.entity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 10:38
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder encoder;
    private Role role;

    /**
     * 功能描述:用户登录
     *
     * @auther: 孟德坤
     * @date: 2019/4/28 10:48
     */
    @PostMapping("/login")
    public Object login(@RequestBody Map loginMap) {
        User user = userService.logoIn(loginMap.get("username") + "", loginMap.get("password") + "");
        if (user != null) {
            //生成令牌
            String token = jwtUtil.createJWT(user.get_id() + "", user.getUsername(), user.getRole().getName());
            System.err.println(token);
            Map map = new HashMap();
            map.put("token", token);
            map.put("name", user.getUsername());//登陆名

            return new Result(true, StatusCode.OK, "登录成功", map);
        } else {
            return new Result(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }
    }

    /**
     * 功能描述: 注册
     *
     * @auther: 孟德坤
     * @date: 2019/4/28 14:37
     */
    @PostMapping("/register")
    public Object register(@RequestBody User user) {
        if (user != null) {
//           设置默认权限
            Role role = new Role();
            role.setName("user");
            role.setDesc("普通管理员");
//           留白先
//            role.setResource("");
            user.setRole(role);
            String password = user.getPassword();
            user.setPassword(encoder.encode(password));
            return userService.register(user);
        }
        return new Result(false, StatusCode.ERROR, StatusMessage.PARAMSERROR);

    }

    /**
     * 功能描述: 用户修改资料
     *
     * @date: 2019/5/5 9:30
     */
    @PostMapping("/update/{id}")
    public Object update(@RequestBody User user, @PathVariable String id) {
        user.set_id(id);
        userService.update(user);
        return new Result(true, StatusCode.ERROR, "id不能为空！");

    }
    /**
     *
     * 功能描述:添加用户
     *
     * @param:
     * @return:
     * @auther: 孟德坤
     * @date: 2019/5/10 16:47
     */

    @PostMapping("/save")
    public Object update(@RequestBody User user) {

        userService.update(user);
        return new Result(true, StatusCode.ERROR, "id不能为空！");

    }

    /**
     * 功能描述:用于数据 回显
     *
     * @auther: 孟德坤
     * @date: 2019/5/5 9:36
     */
    @GetMapping("/{id}")
    public Object update(@PathVariable String id) {
        if (!StringUtils.isEmpty(id)){

        User user = userService.findOne(id);
        return new Result(true,StatusCode.OK,StatusMessage.OK ,user);
        }
        return new Result(false, StatusCode.ERROR, StatusMessage.PARAMSERROR);

    }

    /**
     * 功能描述: 删除用户byid
     *
     * @auther: 孟德坤
     * @date: 2019/5/5 9:45
     */
    @DeleteMapping("/{id}")
    public Object deleteById(@PathVariable String id) {
        userService.deleteById(id);
        return new Result(true, StatusCode.ERROR, "id不能为空！");
    }

    /**
     * 功能描述: 获取用户详情
     *
     * @param:
     * @return:
     * @auther: 孟德坤
     * @date: 2019/5/5 14:55
     */
    @GetMapping("/info")
    public Object getInfo(@RequestHeader("Authorization") String token) {
//        解析token 返回User
//        包括用户名 角色
//        模拟数据库返回userInfo
        UserInfo userInfo = new UserInfo();
        List list = new ArrayList<String>();
        list.add("admin");
        userInfo.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557465946818&di=75be329ee87006669b19e156bc680ad3&imgtype=0&src=http%3A%2F%2Fdownhdlogo.yy.com%2Fhdlogo%2F640640%2F640%2F640%2F52%2F0503528495%2Fu503528495iQoPk9pE.jpeg");
        userInfo.setName("admin");
        userInfo.setRole(list);
        userInfo.setRoles(list);
        return new Result(true, StatusCode.OK, "查询成功！", userInfo);
    }

    /**
     * 功能描述: 退出
     *
     * @auther: 孟德坤
     * @date: 2019/5/6 18:15
     */
    @PostMapping("/logout")
    public Object logout() {

        return new Result(true, StatusCode.OK, "登出成功！");
    }
    /**
     *
     * 功能描述: 动态分页查询
     * @auther: 孟德坤
     * @date: 2019/5/10 15:33
     */
    /**
     * 动态条件分页查询
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result comment(@PathVariable int page,@PathVariable int size,@RequestBody User user){

        PageResult pageResult = userService.search(user,page-1,size);
        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }

}
