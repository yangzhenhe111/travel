package com.cy.travels.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description  TravelPlace 
 * @Author  yzh
 * @Date 2022-02-09 
 */
@Data
@ApiModel
public class TravelPlaceDTO  implements Serializable {

	private static final long serialVersionUID =  1L;

	/**
	 * ID
	 */
	@ApiModelProperty("ID")
	private Long id;

	/**
	 * 景点介绍
	 */
	@ApiModelProperty("景点介绍")
	private String placeInformation;

	/**
	 * 标题
	 */
	@ApiModelProperty("标题")
	private String title;

	/**
	 * 游玩建议
	 */
	@ApiModelProperty("游玩建议")
	private String travelAdvice;

	/**
	 * 标题ID
	 */
	@ApiModelProperty("标题ID")
	private Long titleId;


}
