package com.sunniwell;

import com.sunniwell.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从请求中获取token
        String authHeader = request.getHeader("Authorization");

        /*if(authHeader==null||!authHeader.startsWith("Bearer ")){
            return new Result(false, StatusCode.ACCESSERROR,"权限不足");
        }

        String token = authHeader.substring(7);
        Claims claims = jwtUtil.parseJWT(token);

        if(claims==null){
            return new Result(false,StatusCode.ACCESSERROR,"权限不足");
        }
        if(!"admin".equals(claims.get("roles"))){
            return new Result(false,StatusCode.ACCESSERROR,"权限不足");
        }*/
//        解析token
//        可根据token拿到角色 做对应鉴权
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                if ("admin".equals(claims.get("roles"))) {
                    request.setAttribute("admin_claims", claims);
                } else {
                    request.setAttribute("user_claims", claims);
                }
            }
        }
        return true; //放行
    }
}
