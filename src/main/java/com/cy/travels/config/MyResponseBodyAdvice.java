package com.cy.travels.config;

import com.cy.travels.enums.ResultEnum;
import com.cy.travels.model.entity.User;
import com.cy.travels.utils.dto.Result;
import com.cy.travels.utils.dto.ResultResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
//声明该类要处理的包路径
@ControllerAdvice("com.cy.travels.controller")
@Slf4j
public class MyResponseBodyAdvice extends AbstractController implements ResponseBodyAdvice {

    /*
     * 选择哪些类，或哪些方法需要走beforeBodyWrite
     * 从returnType中可以获取方法名和类名
     * returnType.getMethod().getName()为获取方法名
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
//        if ("login".equals(returnType.getMethod().getName())) {
//            return true;
//        }else {
//            return false;
//        }
        return false;
    }

    /*
     * 对response处理的具体方法
     * body为返回的报文体，body为org.json.jsonObject
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        log.info(body.toString());
        WebApplicationContext context = getWebApplicationContext();
        Map<String, User> map = (Map)context.getServletContext().getAttribute("usermap");
        if (map == null) {
            map = new HashMap<>();
        }
        Gson gson = new Gson();
        Result result = (Result)body;
        //判断是否登陆成功
        if (ResultEnum.OK.getCode().equals(result.getStatus())) {
            //登录成功
            log.info("login:"+ResultEnum.OK.getMsg());
            User user = (User)result.getData();
            //检验是否是首次登录
            if (map.get(user.getId()+"")== null) {
                //首次登录，保存
                map.put("" + user.getId(), user);
                context.getServletContext().setAttribute("usermap",map);
            }else {
                //非首次登录，不必管
            }
            ResultResponse resultResponse = ResultResponse.ok(user);
            body =gson.toJson(resultResponse);
        }else {
            //登录失败，返回的异常信息不变
        }

        return body;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return null;
    }
}
