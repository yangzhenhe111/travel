package com.cy.travels.controller;

import com.cy.travels.model.dto.CommentDTO;
import com.cy.travels.model.dto.CommentRespDTO;
import com.cy.travels.model.entity.Comment;
import com.cy.travels.service.CommentService;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;
import com.cy.travels.utils.dto.Result;
import com.cy.travels.utils.dto.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@Api(value = "评论管理", tags = "评论管理")
@RequestMapping("/front/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @ApiOperation("保存评论")
    @PostMapping("/save")
    public ResultResponse save(@RequestBody CommentDTO commentDTO) {
        int num = commentService.save(commentDTO);
        if (num > 0) {
            return ResultResponse.ok();
        } else {
            return ResultResponse.fail("保存失败，请重新请求。");
        }
    }

    @ApiOperation("获取评论列表")
    @PostMapping("/listPage")
    public ResultResponse<PageBean<CommentRespDTO>> listPage(@RequestBody PageRequest<CommentDTO> request) {
        if (Objects.isNull(request.getPageNum()) || request.getPageNum() < 1) {
            request.setPageNum(1);
        }
        if (Objects.isNull(request.getPageSize()) || request.getPageNum() < 1) {
            request.setPageSize(10);
        }
        PageBean<CommentRespDTO> result = commentService.listPage(request);
        return ResultResponse.ok(result);
    }

    @ApiOperation("点赞评论")
    @PostMapping("/like")
    public Result like(@RequestBody CommentDTO commentDTO) {
        int num = commentService.like(commentDTO);
        if (num > 0) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("获取全部评论列表")
    @GetMapping("/getCommentListAll")
    public ResultResponse<List<CommentRespDTO>> getCommentListAll(@RequestParam("travelId") Long travelId) {
        PageRequest<CommentDTO> request = new PageRequest<>();
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setTravelsId(travelId);
        request.setData(commentDTO);
        if (Objects.isNull(request.getPageNum()) || request.getPageNum() < 1) {
            request.setPageNum(1);
        }
        if (Objects.isNull(request.getPageSize()) || request.getPageSize() < 1) {
            request.setPageSize(999);
        }
        PageBean<CommentRespDTO> result = commentService.listPage(request);
        return ResultResponse.ok(result);
    }

}
