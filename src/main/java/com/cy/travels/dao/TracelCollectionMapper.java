package com.cy.travels.dao;

import com.cy.travels.model.dto.TravelCollectionDTO;
import com.cy.travels.model.dto.TravelsHistoryDTO;
import com.cy.travels.model.dto.TravelsTitleDTO;
import com.cy.travels.model.entity.TravelCollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TracelCollectionMapper extends BaseMapper<TravelCollection> {
//    List<TravelsTitleDTO> getAllCollectionList(TravelCollectionDTO query);
    List<TravelCollectionDTO> getAllCollectionList(TravelCollectionDTO query);
}
