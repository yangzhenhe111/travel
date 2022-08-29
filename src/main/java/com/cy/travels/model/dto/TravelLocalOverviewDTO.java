package com.cy.travels.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description TravelLocalOverview
 * @Author yzh
 * @Date 2022-02-09
 */
@Data
@ApiModel
public class TravelLocalOverviewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 景点名称
     */
    @Column
    @ApiModelProperty(value = "景点名称")
    private String name;

    /**
     * 所在地
     */
    @Column
    @ApiModelProperty(value = "所在地")
    private String address;

    /**
     * 开放时间
     */
    @Column
    @ApiModelProperty(value = "开放时间")
    private String opentime;

    /**
     * 简介
     */
    @Column
    @ApiModelProperty(value = "简介")
    private String briefDesc;

    /**
     * 标题ID
     */
    @ApiModelProperty("标题ID")
    private Long titleId;


}
