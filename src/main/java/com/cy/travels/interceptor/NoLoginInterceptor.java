package com.cy.travels.interceptor;

import com.cy.travels.controller.UserController;
import com.cy.travels.enums.ResultEnum;
import com.cy.travels.model.entity.User;
import com.cy.travels.service.UserService;
import com.cy.travels.utils.RedisUtil;
import com.cy.travels.utils.dto.Result;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 登录检查
 * 拦截器
 */
@Slf4j
@Component
public class NoLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 目标方法执行之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Gson gson = new Gson();

        log.info("[content-type]:"+ request.getHeader("content-type"));
        log.info("[method]:" + request.getMethod());
        log.info("[path]:" + request.getPathInfo());
        log.info("[URL]:" + request.getRequestURL());
        log.info("[Uri]:" + request.getRequestURI());
        log.info("[header-user]:" + request.getHeader("header-user"));
        String header = request.getHeader("header-user");
        //检查请求header的信息是否存在
        if (Objects.nonNull(header)) {
            //存在
            User headerUser = gson.fromJson(header,User.class);
//            Map<String,String> usermap = (Map) request.getSession().getServletContext().getAttribute("usermap");
//            //根据请求头获取session保存的usermap,检查登录信息是否存在
//            /**
//             *此处尚需修改，若是服务器重启，客户端仍旧传来请求头，则会报错空指针
//             * 改之后，若是服务器重启，当用户再次登陆时，若是服务端没有相应数据，则自动从数据库获取已登录用户信息【但是没用，controller为null】
//             */
//            if (null == usermap) {
////                List<User> list = userService.getLoginUserList();
////                usermap = new HashMap<>();
////                for (User u : list) {
////                    usermap.put(u.getId()+"",gson.toJson(u));
////                }
////                request.getSession().getServletContext().setAttribute("usermap",usermap);
//            }
//            String userStr = usermap.get(headerUser.getId()+"");
//            User sessionUser = gson.fromJson(userStr,User.class);
            LinkedHashMap<String,Object> redisUser = (LinkedHashMap<String, Object>) redisUtil.get("user-" + headerUser.getId());
            log.info("[redis]:"+redisUser.toString());

            String redisUserId = String.valueOf(redisUser.get("id"));
            String headerUserId = String.valueOf(headerUser.getId());
            if (redisUserId.equals(headerUserId)) {
                return true;
            }else {
                //不存在
            }
//            //获取保存在session的信息，判断是否存在
//            if (Objects.nonNull(sessionUser)) {
//                //存在
//                return true;
//            }else {
//                //不存在
//            }
        }else {
            //不存在，基本不可能
        }
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().print(Result.fail(ResultEnum.UNAUTHORIZED.getCode(),"用户还未登陆，请登录"));
        return false;
    }

    /**
     * 目标方法执行之后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info(request.getHeader("header-user"));
        log.info("responseInfo:" + response.getOutputStream().toString());
    }

}
