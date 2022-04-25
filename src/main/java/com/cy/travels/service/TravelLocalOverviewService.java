package com.cy.travels.service;

import com.cy.travels.model.dto.TravelLocalOverviewDTO;

public interface TravelLocalOverviewService {
    TravelLocalOverviewDTO saveOrUpdata(TravelLocalOverviewDTO condition);

    TravelLocalOverviewDTO getDetails(TravelLocalOverviewDTO condition);

    TravelLocalOverviewDTO delete(TravelLocalOverviewDTO condition);
}
