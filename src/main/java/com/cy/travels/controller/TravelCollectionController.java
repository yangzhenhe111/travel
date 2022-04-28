package com.cy.travels.controller;

import com.cy.travels.model.dto.TravelCollectionDTO;
import com.cy.travels.model.dto.TravelsHistoryDTO;
import com.cy.travels.model.dto.TravelsTitleDTO;
import com.cy.travels.service.TravelCollectionService;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;
import com.cy.travels.utils.dto.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Api(tags = "收藏游记管理")
@RestController
@RequestMapping("/front/travel/collection")
public class TravelCollectionController {

    @Resource
    private TravelCollectionService collectionService;

//    @ApiOperation("查看收藏历史，严格来讲，不需要参数，只需要确定分页即可")
//    @PostMapping("/listPage")
//    private ResultResponse<PageBean<TravelsTitleDTO>> listPage(@RequestBody PageRequest<TravelCollectionDTO> request) {
//        if (Objects.isNull(request.getPageNum()) || request.getPageNum() <1) {
//            request.setPageNum(1);
//        }
//        if (Objects.isNull(request.getPageSize()) || request.getPageSize() < 1) {
//            request.setPageSize(10);
//        }
//        PageBean<TravelsTitleDTO> result = collectionService.listPage(request);
//        return ResultResponse.ok(result);
//    }

    @ApiOperation("查看收藏历史")
    @PostMapping("/listPage")
    private ResultResponse<PageBean<TravelCollectionDTO>> listPage(@RequestBody PageRequest<TravelCollectionDTO> request) {
        if (Objects.isNull(request.getPageNum()) || request.getPageNum() <1) {
            request.setPageNum(1);
        }
        if (Objects.isNull(request.getPageSize()) || request.getPageSize() < 1) {
            request.setPageSize(10);
        }
        PageBean<TravelCollectionDTO> result = collectionService.listPage(request);
        return ResultResponse.ok(result);
    }

    @ApiOperation("收藏游记，传来ID即可")
    @PostMapping("/collect")
    public ResultResponse<TravelCollectionDTO> collect(@RequestBody TravelCollectionDTO condition) {
        TravelCollectionDTO result = collectionService.save(condition);
        return ResultResponse.ok("收藏成功",result);
    }
}
