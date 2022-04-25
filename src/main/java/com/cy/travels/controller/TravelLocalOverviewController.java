package com.cy.travels.controller;

import com.cy.travels.model.dto.TravelLocalOverviewDTO;
import com.cy.travels.service.TravelLocalOverviewService;
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
@Api(tags = "当地概况管理")
@RestController
@RequestMapping("/front/travel/localOverview")
public class TravelLocalOverviewController {

    /**
     * 保存
     * 删除
     * 保存图片
     * 保存图片集
     * 获取详情
     */

    @Resource
    private TravelLocalOverviewService localOverviewService;

    @ApiOperation("保存当地概况")
    @PostMapping("/saveOrUpdata")
    public ResultResponse<TravelLocalOverviewDTO> saveOrUpdata(@RequestBody TravelLocalOverviewDTO condition) {
        TravelLocalOverviewDTO localOverviewDTO = localOverviewService.saveOrUpdata(condition);
        return ResultResponse.ok(localOverviewDTO);
    }

    @ApiOperation("删除当地概况")
    @PostMapping("/delete")
    public ResultResponse<TravelLocalOverviewDTO> delete(@RequestBody TravelLocalOverviewDTO condition) {
        TravelLocalOverviewDTO localOverviewDTO = localOverviewService.delete(condition);
        return ResultResponse.ok(localOverviewDTO);
    }

    @ApiOperation("获取当地概况")
    @PostMapping("/getDetails")
    public ResultResponse<TravelLocalOverviewDTO> getDetails(@RequestBody TravelLocalOverviewDTO condition) {
        TravelLocalOverviewDTO localOverviewDTO = localOverviewService.getDetails(condition);
        return ResultResponse.ok(localOverviewDTO);
    }

    @ApiOperation("上传图片")
    @PostMapping("/uploadImg")
    public ResultResponse<String> uploadImg(@RequestParam("file")MultipartFile file) {
        if (file.isEmpty()) {
            return ResultResponse.fail("上传文件为null");
        }
        String fileName = file.getOriginalFilename();
        String filePath = "/upload/travels/localOverview/";
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
            return ResultResponse.ok(Constant.BaseUrl+"static/travels/localOverview/" + fileName);
        } catch (IOException e) {
            System.out.println(e);
            return ResultResponse.fail("上传失败");
        }
    }


}
