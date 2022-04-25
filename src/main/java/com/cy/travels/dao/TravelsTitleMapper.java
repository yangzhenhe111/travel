package com.cy.travels.dao;

import com.cy.travels.model.entity.TravelsTitle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TravelsTitleMapper extends BaseMapper<TravelsTitle> {
    List<Long> getTravelsIdList(String info);
}
