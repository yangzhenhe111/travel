package com.cy.travels.controller;

import com.cy.travels.enums.ResultEnum;
import com.cy.travels.exception.BusinessException;
import com.cy.travels.model.dto.UserDTO;
import com.cy.travels.model.entity.User;
import com.cy.travels.service.UserService;
import com.cy.travels.utils.Constant;
import com.cy.travels.utils.RedisUtil;
import com.cy.travels.utils.RequestContextUtil;
import com.cy.travels.utils.dto.ResResult;
import com.cy.travels.utils.dto.Result;
import com.cy.travels.utils.dto.ResultResponse;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HttpServletBean;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Api(tags = "用户接口")
@RestController
@RequestMapping("/front/user")
@Component
public class UserController {

    @Resource
    private UserService userService;

    private Gson gson = new Gson();

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation("登录")
    public ResultResponse<UserDTO> login(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpServletRequest request){

        ServletContext context = request.getSession().getServletContext();

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        UserDTO loginUser = userService.login(userDTO);
        if (null != loginUser) {
//            Map<String, String> map = (Map)context.getAttribute("usermap");
//            if (map == null) {
//                map = new HashMap<>();
//            }
//            //检验是否是首次登录
//            if (Objects.isNull(map.get(loginUser.getId()+""))) {
//                //首次登录，保存
//                map.put("" + loginUser.getId(), gson.toJson(loginUser));
//                context.setAttribute("usermap",map);
//            }
            if (Objects.isNull(redisUtil.get("user-"+loginUser.getId()))){
                redisUtil.set("user-"+loginUser.getId(),loginUser);
            }
            return ResultResponse.ok(loginUser);
        }else {
            return ResultResponse.fail("用户名或者密码不正确");
        }

    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ApiOperation("注册用户")
    public ResultResponse<UserDTO> register(@RequestBody UserDTO userDTO){

        try {
            int num = userService.register(userDTO);
            if (num > 0) {
                return ResultResponse.ok(userService.findUser(userDTO));
            }else {
                return ResultResponse.fail("信息已被注册");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.fail("操作失败"+e.getMessage());
        }

    }


    //编辑保存用户信息
    @ApiOperation("编辑保存用户信息")
    @PostMapping("/editSaveUserInfo")
    public ResultResponse<UserDTO> editSaveUserInfo(@RequestBody UserDTO userDTO){
        try {
            //注册顾客
            UserDTO userDTO1 = userService.editSaveUserInfo(userDTO);
            return ResultResponse.ok(userDTO1);
        }catch (Exception e) {
            return ResultResponse.fail(e.getMessage());
        }

    }

    //编辑保存用户信息
    @ApiOperation("更新密码")
    @PostMapping("/updatePwd")
    public ResultResponse<UserDTO> updatePwd(@RequestBody UserDTO userDTO){
        try {
            UserDTO userDTO1 = userService.updatePwd(userDTO);
            return ResultResponse.ok(userDTO1);
        }catch (Exception e) {
            return ResultResponse.fail(e.getMessage());
        }

    }

    //更新用户头像
    @Deprecated
    @ApiOperation("更新头像名称")
    @PostMapping("/updateHeadImg")
    public ResultResponse<UserDTO> updateHeadImg(@RequestBody UserDTO userDTO){
        try {
            UserDTO userDTO1 = userService.updateHeadImg(userDTO);
            return ResultResponse.ok(userDTO1);
        }catch (Exception e) {
            return ResultResponse.fail(e.getMessage());
        }

    }

    //更新用户头像
    @ApiOperation("更新头像(现用)")
    @PostMapping("/uploadHeadImg")
    public ResultResponse<UserDTO> uploadHeadImg(@RequestParam("file") MultipartFile file){

        if (file.isEmpty()) {
            return ResultResponse.fail("上传文件为null");
        }
        String fileName = file.getOriginalFilename();
        String filePath = "/upload/user/";
        File path = new File(filePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        File dest = new File(filePath + fileName);
        OutputStream out = null;
        try {
            if (!dest.exists()) {
                dest.createNewFile();
            }
            out = new BufferedOutputStream(new FileOutputStream(dest));
            out.write(file.getBytes());
            out.close();
//            return Result.ok("上传成功");
            String headImg = Constant.BaseUrl+ "static/user/" + fileName;

            UserDTO userDTO = new UserDTO();
            userDTO.setHeadImg(headImg);
            UserDTO userDTO1 = userService.updateHeadImg(userDTO);
            return ResultResponse.ok(headImg,userDTO1);
        } catch (IOException e) {
            System.out.println(e);
            return ResultResponse.fail("上传失败");
        }

    }

    
    
    

    @RequestMapping(value = "/info",method = RequestMethod.POST)
    @ApiOperation("获取用户信息")
    public ResultResponse<UserDTO> info(@RequestBody UserDTO userDTO){
        UserDTO result = userService.findUser(userDTO);
        return ResultResponse.ok(result);
    }

    public List<User> getLoginUser() {
        return userService.getLoginUserList();
    }
}
