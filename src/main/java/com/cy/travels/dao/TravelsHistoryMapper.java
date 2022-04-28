package com.cy.travels.dao;

import com.cy.travels.model.dto.TravelsHistoryDTO;
import com.cy.travels.model.dto.TravelsTitleDTO;
import com.cy.travels.model.entity.TravelsHistory;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;

import java.util.List;

@Mapper
public interface TravelsHistoryMapper extends BaseMapper<TravelsHistory>, ExampleMapper<TravelsHistory> {
    List<TravelsHistoryDTO> getAllHistoryList(TravelsHistoryDTO query);
//    List<TravelsTitleDTO> getAllHistoryList(TravelsHistoryDTO query);
}
