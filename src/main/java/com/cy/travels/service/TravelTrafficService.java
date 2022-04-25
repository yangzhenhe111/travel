package com.cy.travels.service;

import com.cy.travels.model.dto.TravelTrafficDTO;

public interface TravelTrafficService {
    TravelTrafficDTO getDetails(TravelTrafficDTO condition);

    TravelTrafficDTO delete(TravelTrafficDTO condition);

    TravelTrafficDTO saveOrUpdata(TravelTrafficDTO condition);
}
