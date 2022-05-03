package com.cy.travels.dao;

import com.cy.travels.model.dto.CommentDTO;
import com.cy.travels.model.dto.CommentRespDTO;
import com.cy.travels.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment>, ExampleMapper<Comment> {
    List<CommentRespDTO> selectCommentListByTravelsId(CommentDTO query);

    CommentRespDTO selectParentComment(@Param("parentId") Long parentId);
}
