package com.cy.travels.controller;

import com.cy.travels.model.dto.TravelsHistoryDTO;
import com.cy.travels.service.TravelsHistoryService;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;
import com.cy.travels.utils.dto.Result;
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
@Api(tags = "游记浏览历史管理")
@RestController
@RequestMapping("/front/travelsHistory")
public class TravelsHistoryController {

    @Resource
    private TravelsHistoryService travelsHistoryService;

    @ApiOperation("查看游记历史")
    @PostMapping("/listPage")
    private ResultResponse<PageBean<TravelsHistoryDTO>> listPage(@RequestBody PageRequest<TravelsHistoryDTO> request) {
        if (Objects.isNull(request.getPageNum()) || request.getPageNum() <1) {
            request.setPageNum(1);
        }
        if (Objects.isNull(request.getPageSize()) || request.getPageSize() < 1) {
            request.setPageSize(10);
        }
        PageBean<TravelsHistoryDTO> result = travelsHistoryService.listPage(request);
        return ResultResponse.ok(result);
    }

}
