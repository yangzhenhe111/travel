package com.cy.travels.controller;

import com.cy.travels.enums.ResultEnum;
import com.cy.travels.model.dto.QueryTravelsDTO;
import com.cy.travels.model.dto.TravelsDTO;
import com.cy.travels.model.dto.TravelsHistoryDTO;
import com.cy.travels.model.entity.User;
import com.cy.travels.service.TravelsHistoryService;
import com.cy.travels.service.TravelsService;
import com.cy.travels.utils.Constant;
import com.cy.travels.utils.RedisUtil;
import com.cy.travels.utils.RequestContextUtil;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;
import com.cy.travels.utils.dto.Result;
import com.cy.travels.utils.dto.ResultResponse;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Api(tags = "游记管理")
@RestController
@RequestMapping("/front/travels")
public class TravelsController {

    @Resource
    private TravelsService travelsService;

    @Resource
    private TravelsHistoryService historyService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation("分页获取游记列表")
    @PostMapping("/listPage")
    public ResultResponse<PageBean<TravelsDTO>> listPage(@RequestBody PageRequest<QueryTravelsDTO> request) {
        if (Objects.isNull(request.getPageNum()) || request.getPageNum() <1) {
            request.setPageNum(1);
        }
        if (Objects.isNull(request.getPageSize()) || request.getPageNum() < 1) {
            request.setPageSize(10);
        }
        PageBean<TravelsDTO> result = travelsService.listPage(request);
        return ResultResponse.ok(result);
    }

    @ApiOperation("获取全部游记列表")
    @PostMapping("/getAllTravelsList")
    public ResultResponse<List<TravelsDTO>> getAllTravelsList() {
        List<TravelsDTO> result = travelsService.getAllTravelList();
        return ResultResponse.ok(result);
    }
//    public Result<List<TravelsDTO>> getAllTravelsList() {
//        List<TravelsDTO> result = travelsService.getAllTravelList();
//        return Result.ok(result);
//    }

    @ApiOperation("暂存游记")
    @PostMapping("/saveOrUpdata")
    public ResultResponse<TravelsDTO> saveOrUpdata(@RequestBody TravelsDTO travelsDTO) {
        //保存用户游记信息
        String userStr = RequestContextUtil.getRequestHeader("header-user");
        User user = new Gson().fromJson(userStr,User.class);
        travelsDTO.setCreator(user.getId());
        TravelsDTO result = travelsService.saveOrUpdata(travelsDTO);
        return ResultResponse.ok(result);
    }

    @ApiOperation("发表游记")
    @PostMapping("/submit")
    public ResultResponse<TravelsDTO> submit(@RequestBody TravelsDTO travelsDTO) {
        TravelsDTO result = travelsService.submit(travelsDTO);
        return ResultResponse.ok(result);
    }


    @ApiOperation("获取游记详情")
    @PostMapping("/getDetails")
    public ResultResponse<TravelsDTO> getDetails(@RequestBody TravelsDTO travelsDTO) {
        TravelsDTO result = travelsService.getDetails(travelsDTO);
        //保存浏览历史
        User user = new Gson().fromJson(RequestContextUtil.getRequestHeader("header-user"),User.class);
        if (!user.getId().equals(result.getCreator())) {
            TravelsHistoryDTO travelsHistoryDTO = new TravelsHistoryDTO();
            BeanUtils.copyProperties(result,travelsHistoryDTO);
            travelsHistoryDTO.setId(null);
            travelsHistoryDTO.setTravelsId(result.getId());
            travelsHistoryDTO.setUserId(user.getId());
            historyService.save(travelsHistoryDTO);
        }
        return ResultResponse.ok(result);
    }

//    @Deprecated
    @ApiOperation("上传首页图片")
    @PostMapping("/uploadCover")
    public ResultResponse uploadCover(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResultResponse.fail("上传文件为null");
        }
        String fileName = file.getOriginalFilename();
        //设置文件名
        int count = 0;
        //锁住的是同一对象
        synchronized (this){
            String string = (String) redisUtil.get("File-count");
            if (Objects.nonNull(string)) {
                count = Integer.valueOf(string);
            }
            ++count;
            redisUtil.set("File-count",count,1);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileType = file.getOriginalFilename().split("\\.")[1];
        fileName = format.format(new Date())+"_"+count+"."+fileType;
        String filePath = "/upload/travels/cover/";
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
            return ResultResponse.ok(Constant.BaseUrl+"static/travels/cover/" + fileName);
        } catch (IOException e) {
            System.out.println(e);
            return ResultResponse.fail("上传失败");
        }


        /**
         * 点赞
         * 转发
         * 评论
         */

    }
}
