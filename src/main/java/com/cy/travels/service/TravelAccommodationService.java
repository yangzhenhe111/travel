package com.cy.travels.service;

import com.cy.travels.model.dto.TravelAccommodationDTO;

public interface TravelAccommodationService {
    TravelAccommodationDTO saveOrUpdata(TravelAccommodationDTO condition);

    TravelAccommodationDTO delete(TravelAccommodationDTO condition);

    TravelAccommodationDTO getDetails(TravelAccommodationDTO condition);
}
