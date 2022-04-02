package com.cy.travels.dao;


import com.cy.travels.model.entity.Travels;
import com.cy.travels.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;

import java.util.List;

@Mapper
public interface TravelsMapper extends BaseMapper<Travels>, ExampleMapper<Travels> {
    List<Long> getTravelsIdList(@Param("info") String info);
}
