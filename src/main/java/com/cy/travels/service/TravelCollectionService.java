package com.cy.travels.service;

import com.cy.travels.model.dto.TravelCollectionDTO;
import com.cy.travels.model.dto.TravelsTitleDTO;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;

public interface TravelCollectionService {

    TravelCollectionDTO save(TravelCollectionDTO condition);

//    PageBean<TravelsTitleDTO> listPage(PageRequest<TravelCollectionDTO> request);


    PageBean<TravelCollectionDTO> listPage(PageRequest<TravelCollectionDTO> request);
}
