package com.cy.travels.dao;

import com.cy.travels.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;

@Mapper
public interface UserMapper extends BaseMapper<User>, ExampleMapper<User> {
}
