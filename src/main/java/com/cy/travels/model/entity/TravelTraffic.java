package com.cy.travels.model.entity;

import com.cy.travels.model.BaseDO;
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
@Entity
@Table(name = "travel_traffic")
@ApiModel
public class TravelTraffic extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Long id;

    /**
     * 交通信息
     */
    @Column
    @ApiModelProperty("交通信息")
    private String trafficInformation;

    /**
     * 标题
     */
    @Column
    @ApiModelProperty("标题")
    private String title;

    /**
     * 交通建议
     */
    @Column
    @ApiModelProperty("交通建议")
    private String trafficAdvice;

    /**
     * 标题ID
     */
    @Column
    @ApiModelProperty("标题ID")
    private Long titleId;


}
