package com.cy.travels.interceptor;

import com.cy.travels.model.entity.User;
import com.cy.travels.utils.RedisUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.MimeHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        Map<String,String> map=new HashMap<>();
        User user = new User();
        user.setId(1L);
        user.setUsername("zhangsan");
        user.setPassword("123");
//        Gson gson = new Gson();
//        map.put(""+user.getId(),gson.toJson(user));
//        addHeader(request,map);
//        Map<String, User> context = (Map)request.getSession().getServletContext().getAttribute("usermap");
//        if (context == null) {
//            context = new HashMap<>();
//        }
//        //检验是否是首次登录
//        if (Objects.isNull(context.get(user.getId()+""))) {
//            //首次登录，保存
//            context.put("" + user.getId(), user);
//            request.getSession().getServletContext().setAttribute("usermap",map);
//        }
        if (Objects.isNull(redisUtil.get("1"))){
            redisUtil.set("user-1",user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        Enumeration<String> headerNames = request.getHeaderNames();
        log.info(headerNames.nextElement());
        log.info(request.getHeader("user"));
    }


    private void addHeader(HttpServletRequest request, Map<String,String> headerMap){
        if (headerMap==null||headerMap.isEmpty()){
            return;
        }

        Class<? extends HttpServletRequest> c=request.getClass();
        System.out.println(c.getName());

        try{
            Field requestField=c.getDeclaredField("request");
            requestField.setAccessible(true);

            Object o=requestField.get(request);
            Field coyoteRequest=o.getClass().getDeclaredField("coyoteRequest");
            coyoteRequest.setAccessible(true);

            Object o2=coyoteRequest.get(o);
            Field headers=o2.getClass().getDeclaredField("headers");
            headers.setAccessible(true);

            MimeHeaders mimeHeaders=(MimeHeaders) headers.get(o2);
            for (Map.Entry<String,String> entry:headerMap.entrySet()){
                mimeHeaders.removeHeader(entry.getKey());
                mimeHeaders.addValue(entry.getKey()).setString(entry.getValue());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
