package com.cy.travels.service;

import com.cy.travels.model.dto.QueryTravelsDTO;
import com.cy.travels.model.dto.TravelsDTO;
import com.cy.travels.model.dto.TravelsDetailsDTO;
import com.cy.travels.model.dto.TravelsTitleDTO;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;

import java.util.List;

public interface TravelService {
    TravelsTitleDTO submit(TravelsTitleDTO travelsTitleDTO);

    TravelsTitleDTO save(TravelsTitleDTO travelsTitleDTO);

    TravelsTitleDTO updata(TravelsTitleDTO travelsTitleDTO);

    List<TravelsTitleDTO> getAllTravelList();

    PageBean<TravelsTitleDTO> listPage(PageRequest<QueryTravelsDTO> request);

    TravelsDetailsDTO getDetails(TravelsTitleDTO travelsTitleDTO);

    TravelsTitleDTO saveOrUpdata(TravelsTitleDTO travelsTitleDTO);
}
