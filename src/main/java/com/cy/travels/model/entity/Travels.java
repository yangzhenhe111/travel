package com.cy.travels.model.entity;

import com.cy.travels.model.BaseDO;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Description Travels
 * @Author yzh
 * @Date 2022-02-21
 */
@Data
@Entity
@Table(name = "travels")
@ApiModel
@Slf4j
public class Travels extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 发布时间
     */
    @Column
    @ApiModelProperty(value = "发布时间")
    private Date publishDate;

    /**
     * 封面介绍（图片）
     */
    @Column
    @ApiModelProperty(value = "封面介绍（图片）")
    private String cover;

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
     * 预算
     */
    @Column
    @ApiModelProperty(value = "预算")
    private Double budget;

    /**
     * 简介
     */
    @Column
    @ApiModelProperty(value = "简介")
    private String briefDesc;

    /**
     * 交通信息
     */
    @Column
    @ApiModelProperty(value = "交通信息")
    private String trafficInfo;

    /**
     * 餐饮信息
     */
    @Column
    @ApiModelProperty(value = "餐饮信息")
    private String resraurantInfo;

    /**
     * 酒店信息
     */
    @Column
    @ApiModelProperty(value = "酒店信息")
    private String hotelInfo;


    /**
     * 创建时间
     */
    @Column
    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    /**
     * 创建者
     */
    @Column
    @ApiModelProperty(value = "创建者")
    private Long creator;


}
