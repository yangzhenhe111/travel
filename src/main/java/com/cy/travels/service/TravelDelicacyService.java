package com.cy.travels.service;

import com.cy.travels.model.dto.TravelDelicacyDTO;

public interface TravelDelicacyService {
    TravelDelicacyDTO saveOrUpdata(TravelDelicacyDTO condition);

    TravelDelicacyDTO delete(TravelDelicacyDTO condition);

    TravelDelicacyDTO getDetails(TravelDelicacyDTO condition);
}
