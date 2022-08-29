package com.cy.travels.model.dto;

import com.cy.travels.model.BaseDO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description Travels
 * @Author yzh
 * @Date 2022-02-16
 */
@Data
@ApiModel
public class TravelsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private Date publishDate;

    /**
     * 封面介绍（图片）
     */
    @ApiModelProperty(value = "封面介绍（图片）")
    private String cover;

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
    @ApiModelProperty(value = "开放时间")
    private String opentime;

    /**
     * 预算
     */
    @ApiModelProperty(value = "预算")
    private Double budget;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String briefDesc;

    /**
     * 交通信息
     */
    @ApiModelProperty(value = "交通信息")
    private String trafficInfo;

    /**
     * 餐饮信息
     */
    @ApiModelProperty(value = "餐饮信息")
    private String resraurantInfo;

    /**
     * 酒店信息
     */
    @ApiModelProperty(value = "酒店信息")
    private String hotelInfo;


    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createtime;

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

    /**
     * 创建者头像
     */
    @Column
    @ApiModelProperty(value = "创建者头像")
    private String creatorCover;

}
