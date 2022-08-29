package com.cy.travels.controller;

import com.cy.travels.model.dto.TravelTrafficDTO;
import com.cy.travels.service.TravelTrafficService;
import com.cy.travels.utils.Constant;
import com.cy.travels.utils.dto.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

@Slf4j
@Api(tags = "交通信息管理")
@RestController
@RequestMapping("/front/travel/traffic")
public class TravelTrafficController {

    /**
     * 保存
     * 删除
     * 保存图片
     * 保存图片集
     * 获取详情
     */

    @Resource
    private TravelTrafficService trafficService;

    @ApiOperation("保存交通信息")
    @PostMapping("/saveOrUpdata")
    public ResultResponse<TravelTrafficDTO> saveOrUpdata(@RequestBody TravelTrafficDTO condition) {
        TravelTrafficDTO TrafficDTO = trafficService.saveOrUpdata(condition);
        return ResultResponse.ok(TrafficDTO);
    }

    @ApiOperation("删除交通信息")
    @PostMapping("/delete")
    public ResultResponse<TravelTrafficDTO> delete(@RequestBody TravelTrafficDTO condition) {
        TravelTrafficDTO TrafficDTO = trafficService.delete(condition);
        return ResultResponse.ok(TrafficDTO);
    }

    @ApiOperation("获取交通信息")
    @PostMapping("/getDetails")
    public ResultResponse<TravelTrafficDTO> getDetails(@RequestBody TravelTrafficDTO condition) {
        TravelTrafficDTO TrafficDTO = trafficService.getDetails(condition);
        return ResultResponse.ok(TrafficDTO);
    }

    @ApiOperation("上传图片")
    @PostMapping("/uploadImg")
    public ResultResponse<String> uploadImg(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResultResponse.fail("上传文件为null");
        }
        String fileName = file.getOriginalFilename();
        String filePath = "/upload/travels/traffic/";
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
            return ResultResponse.ok(Constant.BaseUrl + "static/travels/traffic/" + fileName);
        } catch (IOException e) {
            System.out.println(e);
            return ResultResponse.fail("上传失败");
        }
    }


}
