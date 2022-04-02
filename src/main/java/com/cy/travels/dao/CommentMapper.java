package com.cy.travels.dao;

import com.cy.travels.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment>, ExampleMapper<Comment> {
}
