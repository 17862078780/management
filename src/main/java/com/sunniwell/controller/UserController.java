package com.sunniwell.controller;


import com.sunniwell.common.entity.Result;
import com.sunniwell.common.entity.StatusCode;
import com.sunniwell.common.entity.UserInfo;
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
    /**
     *
     * 功能描述:用户登录
     *
     * @auther: 孟德坤
     * @date: 2019/4/28 10:48
     */
    @PostMapping("/login")
    public Object login(@RequestBody Map loginMap){
        User user = userService.logoIn(loginMap.get("username") + "", loginMap.get("password") + "");
        if(user!=null){
            //生成令牌
            String token = jwtUtil.createJWT(user.get_id()+"", user.getUsername(), user.getRole());
            System.err.println(token);
            Map map = new HashMap();
            map.put("token",token);
            map.put("name",user.getUsername());//登陆名

            return new Result(true, StatusCode.OK,"登录成功",map);
        }else{
            return new Result(false,StatusCode.LOGINERROR,"用户名或密码错误");
        }
    }
    /**
     *
     * 功能描述: 注册
     * @auther: 孟德坤
     * @date: 2019/4/28 14:37
     */
    @PostMapping("/register")
    public Object register(@RequestBody Map<String,String> registerUser){
        if (!registerUser.isEmpty()&& !StringUtils.isEmpty(registerUser.get("username"))&&!StringUtils.isEmpty(registerUser.get("password"))){
            User user = new User();
            user.setUsername(registerUser.get("username"));
            user.setPassword(encoder.encode(registerUser.get("password")));
            user.setRole("ROLE_USER");
//            给默认头像地址
            user.setAvatar("http://cdn.duitang.com/uploads/item/201409/18/20140918141220_N4Tic.thumb.700_0.jpeg");
             return userService.register(user);
        }
        return new Result(false, StatusCode.ERROR, "用户名或者密码不能为空！");

    }
    /**
     *
     * 功能描述: 用户修改资料
     *
     * @date: 2019/5/5 9:30
     */
    @PostMapping("/update/{id}")
    public Object update(@RequestBody User user,@PathVariable String id){
        user.set_id(id);
        userService.update(user);
        return new Result(true, StatusCode.ERROR, "id不能为空！");

    }
    /**
     *
     * 功能描述:用于数据 回显
     *
     * @auther: 孟德坤
     * @date: 2019/5/5 9:36
     */
    @GetMapping("/{id}")
    public Object update(@PathVariable String id){
        User user =  userService.findOne(id);
        return new Result(true, StatusCode.ERROR, "id不能为空！",user);

    }
    /**
     *
     * 功能描述: 删除用户byid
     *
     * @auther: 孟德坤
     * @date: 2019/5/5 9:45
     */
    @DeleteMapping("/{id}")
    public Object deleteById(@PathVariable String id){
         userService.deleteById(id);
        return new Result(true, StatusCode.ERROR, "id不能为空！");
    }

    /**
     *
     * 功能描述: 过去用户详情
     *
     * @param:
     * @return:
     * @auther: 孟德坤
     * @date: 2019/5/5 14:55
     */
    @GetMapping("/info")
    public Object getInfo(@RequestHeader("Authorization") String token){
//        解析token 返回User
//        包括用户名 角色
//        模拟数据库返回userInfo
        UserInfo userInfo = new UserInfo();
        List list = new ArrayList<String>();
        list.add("admin");
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfo.setName("admin");
        userInfo.setRole(list);
        userInfo.setRoles(list);
        return new Result(true, StatusCode.OK, "查询成功！",userInfo);
    }

    /**
     *
     * 功能描述: 退出
     *
     * @auther: 孟德坤
     * @date: 2019/5/6 18:15
     */
    @PostMapping("/logout")
    public Object logout(){

        return new Result(true, StatusCode.OK, "登出成功！");
    }

}
