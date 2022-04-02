package com.cy.travels.service;

import com.cy.travels.model.dto.TravelsHistoryDTO;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;

public interface TravelsHistoryService {

    TravelsHistoryDTO save(TravelsHistoryDTO travelsHistoryDTO);

    PageBean<TravelsHistoryDTO> listPage(PageRequest<TravelsHistoryDTO> request);
}
