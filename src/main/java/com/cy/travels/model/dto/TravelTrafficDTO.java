package com.cy.travels.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description TravelTraffic
 * @Author yzh
 * @Date 2022-02-09
 */
@Data
@ApiModel
public class TravelTrafficDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 交通信息
     */
    @ApiModelProperty("交通信息")
    private String trafficInformation;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 交通建议
     */
    @ApiModelProperty("交通建议")
    private String trafficAdvice;

    /**
     * 标题ID
     */
    @ApiModelProperty("标题ID")
    private Long titleId;


}
