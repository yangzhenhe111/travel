package com.cy.travels.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class QueryTravelsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间-起期")
    private Date publishDateStart;
    @ApiModelProperty(value = "发布时间-止期")
    private Date publishDateEnd;

    /**
     * 景点名称
     */
    @ApiModelProperty(value = "景点名称")
    private String name;

    /**
     * 所在地
     */
    @ApiModelProperty(value = "所在地")
    private String address;

    /**
     * 开放时间
     */
    @ApiModelProperty(value = "开放时间-起期")
    private String opentimeStart;
    @ApiModelProperty(value = "开放时间-止期")
    private String opentimeEnd;

    /**
     * 预算
     */
    @ApiModelProperty(value = "预算-最少")
    private Double budgetMin;
    @ApiModelProperty(value = "预算-最多")
    private Double budgetMax;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String briefDesc;

    /**
     * 信息
     * 交通/餐饮/酒店
     */
    @ApiModelProperty(value = "搜索框输入信息(模糊搜索),景点名/地址/简介/开放时间/发布时间/创建者等，取并集,再与其他的查询条件取交集")
    private String info;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private Long creator;

    /**
     * 创建者姓名
     */
    @Column
    @ApiModelProperty(value = "创建者姓名")
    private String creatorName;

}
