package com.cy.travels.service;

import com.cy.travels.model.dto.QueryTravelsDTO;
import com.cy.travels.model.dto.TravelsDTO;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;

import java.util.List;

public interface TravelsService {
    TravelsDTO submit(TravelsDTO travelsDTO);

    TravelsDTO save(TravelsDTO travelsDTO);

    TravelsDTO updata(TravelsDTO travelsDTO);

    List<TravelsDTO> getAllTravelList();

    PageBean<TravelsDTO> listPage(PageRequest<QueryTravelsDTO> request);

    TravelsDTO getDetails(TravelsDTO travelsDTO);

    TravelsDTO saveOrUpdata(TravelsDTO travelsDTO);
}
