package com.cy.travels.dao;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

@RegisterMapper
public interface BaseMapper<T> extends Mapper<T> {
}
