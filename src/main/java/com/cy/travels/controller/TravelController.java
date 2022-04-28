package com.cy.travels.controller;

import com.cy.travels.model.dto.*;
import com.cy.travels.model.entity.User;
import com.cy.travels.service.TravelService;
import com.cy.travels.service.TravelsHistoryService;
import com.cy.travels.utils.Constant;
import com.cy.travels.utils.RequestContextUtil;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;
import com.cy.travels.utils.dto.ResultResponse;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Objects;

@Slf4j
@Api(tags = "游记管理(新)")
@RestController
@RequestMapping("/front/travel")
public class TravelController {

    @Resource
    private TravelService travelService;

    @Resource
    private TravelsHistoryService historyService;


    @ApiOperation("分页获取游记列表")
    @PostMapping("/listPage")
    public ResultResponse<PageBean<TravelsTitleDTO>> listPage(@RequestBody PageRequest<QueryTravelsDTO> request) {
        if (Objects.isNull(request.getPageNum()) || request.getPageNum() <1) {
            request.setPageNum(1);
        }
        if (Objects.isNull(request.getPageSize()) || request.getPageNum() < 1) {
            request.setPageSize(10);
        }
        PageBean<TravelsTitleDTO> result = travelService.listPage(request);
        return ResultResponse.ok(result);
    }

    @ApiOperation("获取全部游记列表")
    @PostMapping("/getAllTravelsList")
    public ResultResponse<List<TravelsTitleDTO>> getAllTravelsList() {
        List<TravelsTitleDTO> result = travelService.getAllTravelList();
        return ResultResponse.ok(result);
    }

    @ApiOperation("暂存游记")
    @PostMapping("/saveOrUpdata")
    public ResultResponse<TravelsDTO> saveOrUpdata(@RequestBody TravelsTitleDTO condition) {

        //保存用户游记信息
        String userStr = RequestContextUtil.getRequestHeader("header-user");
        User user = new Gson().fromJson(userStr,User.class);
        condition.setCreator(user.getId());
        TravelsTitleDTO result = travelService.saveOrUpdata(condition);
        return ResultResponse.ok(result);
    }

    @ApiOperation("发表游记")
    @PostMapping("/submit")
    public ResultResponse<TravelsTitleDTO> submit(@RequestBody TravelsTitleDTO condition) {
        TravelsTitleDTO result = travelService.submit(condition);
        return ResultResponse.ok(result);
    }


    @ApiOperation("获取游记详情")
    @PostMapping("/getDetails")
    public ResultResponse<TravelsDetailsDTO> getDetails(@RequestBody TravelsTitleDTO condition) {
        TravelsDetailsDTO result = travelService.getDetails(condition);
        //保存浏览历史
        User user = new Gson().fromJson(RequestContextUtil.getRequestHeader("header-user"),User.class);
        if (!user.getId().equals(result.getTitle().getCreator())) {
            TravelsHistoryDTO travelsHistoryDTO = new TravelsHistoryDTO();
            BeanUtils.copyProperties(result,travelsHistoryDTO);
            travelsHistoryDTO.setId(null);
            travelsHistoryDTO.setTravelsId(result.getTitle().getId());
            travelsHistoryDTO.setUserId(user.getId());
            historyService.save(travelsHistoryDTO);
        }
        return ResultResponse.ok(result);
    }

    @ApiOperation("上传首页图片")
    @PostMapping("/uploadCover")
    public ResultResponse uploadCover(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResultResponse.fail("上传文件为null");
        }
        String fileName = file.getOriginalFilename();
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
            return ResultResponse.ok(Constant.BaseUrl + "static/travels/cover/" + fileName);
        } catch (IOException e) {
            System.out.println(e);
            return ResultResponse.fail("上传失败");
        }


    }
    @ApiOperation("收藏游记，传来ID即可")
    @PostMapping("/collect")
    public ResultResponse<TravelsTitleDTO> collect(@RequestBody TravelsTitleDTO condition) {
        TravelsTitleDTO result = travelService.collect(condition);
        return ResultResponse.ok("收藏成功",result);
    }

    /**
     * 点赞
     * 转发
     * 评论
     */


}
