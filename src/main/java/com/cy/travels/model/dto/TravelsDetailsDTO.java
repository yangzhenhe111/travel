package com.cy.travels.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class TravelsDetailsDTO {

    @ApiModelProperty("标题及其他")
    private TravelsTitleDTO title;

    @ApiModelProperty("交通")
    private TravelTrafficDTO traffic;

//    @ApiModelProperty("景点")
//    private TravelPlaceDTO place;

    @ApiModelProperty("当地概况")
    private TravelLocalOverviewDTO localOverview;

    @ApiModelProperty("美食")
    private TravelDelicacyDTO delicacy;

    @ApiModelProperty("住宿")
    private TravelAccommodationDTO accommodation;

}
