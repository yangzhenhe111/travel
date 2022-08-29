package com.cy.travels.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description TravelDelicacy
 * @Author yzh
 * @Date 2022-02-09
 */
@Data
@ApiModel
public class TravelDelicacyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 餐馆名称
     */
    @ApiModelProperty("餐馆名称")
    private String restaurantName;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 推荐菜品
     */
    @ApiModelProperty("推荐菜品")
    private String recommendDishes;

//	/**
//	 * 人均消费
//	 */
//	@ApiModelProperty("人均消费")
//	private String averageConsumption;

    /**
     * 标题ID
     */
    @ApiModelProperty("标题ID")
    private Long titleId;


}
