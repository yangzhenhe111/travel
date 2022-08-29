package com.cy.travels.controller;

import com.cy.travels.model.dto.TravelDelicacyDTO;
import com.cy.travels.service.TravelDelicacyService;
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
@Api(tags = "美食信息管理")
@RestController
@RequestMapping("/front/travel/delicacy")
public class TravelDelicacyController {

    /**
     * 保存
     * 删除
     * 保存图片
     * 保存图片集
     * 获取详情
     */

    @Resource
    private TravelDelicacyService delicacyService;

    @ApiOperation("保存美食信息")
    @PostMapping("/saveOrUpdata")
    public ResultResponse<TravelDelicacyDTO> saveOrUpdata(@RequestBody TravelDelicacyDTO condition) {
        TravelDelicacyDTO DelicacyDTO = delicacyService.saveOrUpdata(condition);
        return ResultResponse.ok(DelicacyDTO);
    }

    @ApiOperation("删除美食信息")
    @PostMapping("/delete")
    public ResultResponse<TravelDelicacyDTO> delete(@RequestBody TravelDelicacyDTO condition) {
        TravelDelicacyDTO DelicacyDTO = delicacyService.delete(condition);
        return ResultResponse.ok(DelicacyDTO);
    }

    @ApiOperation("获取美食信息")
    @PostMapping("/getDetails")
    public ResultResponse<TravelDelicacyDTO> getDetails(@RequestBody TravelDelicacyDTO condition) {
        TravelDelicacyDTO DelicacyDTO = delicacyService.getDetails(condition);
        return ResultResponse.ok(DelicacyDTO);
    }

    @ApiOperation("上传图片")
    @PostMapping("/uploadImg")
    public ResultResponse<String> uploadImg(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResultResponse.fail("上传文件为null");
        }
        String fileName = file.getOriginalFilename();
        String filePath = "/upload/travels/delicacy/";
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
            return ResultResponse.ok(Constant.BaseUrl + "static/travels/delicacy/" + fileName);
        } catch (IOException e) {
            System.out.println(e);
            return ResultResponse.fail("上传失败");
        }
    }

}
