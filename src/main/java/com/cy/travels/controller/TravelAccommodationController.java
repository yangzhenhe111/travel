package com.cy.travels.controller;

import com.cy.travels.model.dto.TravelAccommodationDTO;
import com.cy.travels.model.dto.TravelAccommodationDTO;
import com.cy.travels.service.TravelAccommodationService;
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
@Api(tags = "住宿信息管理")
@RestController
@RequestMapping("/front/travel/accommodation")
public class TravelAccommodationController {

    /**
     * 保存
     * 删除
     * 保存图片
     * 保存图片集
     * 获取详情
     */

    @Resource
    private TravelAccommodationService accommodationService;

    @ApiOperation("保存住宿信息")
    @PostMapping("/saveOrUpdata")
    public ResultResponse<TravelAccommodationDTO> saveOrUpdata(@RequestBody TravelAccommodationDTO condition) {
        TravelAccommodationDTO AccommodationDTO = accommodationService.saveOrUpdata(condition);
        return ResultResponse.ok(AccommodationDTO);
    }

    @ApiOperation("删除住宿信息")
    @PostMapping("/delete")
    public ResultResponse<TravelAccommodationDTO> delete(@RequestBody TravelAccommodationDTO condition) {
        TravelAccommodationDTO AccommodationDTO = accommodationService.delete(condition);
        return ResultResponse.ok(AccommodationDTO);
    }

    @ApiOperation("获取住宿信息")
    @PostMapping("/getDetails")
    public ResultResponse<TravelAccommodationDTO> getDetails(@RequestBody TravelAccommodationDTO condition) {
        TravelAccommodationDTO AccommodationDTO = accommodationService.getDetails(condition);
        return ResultResponse.ok(AccommodationDTO);
    }

    @ApiOperation("上传图片")
    @PostMapping("/uploadImg")
    public ResultResponse<String> uploadImg(@RequestParam("file")MultipartFile file) {
        if (file.isEmpty()) {
            return ResultResponse.fail("上传文件为null");
        }
        String fileName = file.getOriginalFilename();
        String filePath = "/upload/travels/accommodation/";
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
            return ResultResponse.ok(Constant.BaseUrl+"static/travels/accommodation/" + fileName);
        } catch (IOException e) {
            System.out.println(e);
            return ResultResponse.fail("上传失败");
        }
    }


}
