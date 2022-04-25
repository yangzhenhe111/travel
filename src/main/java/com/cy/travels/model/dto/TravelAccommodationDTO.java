package com.cy.travels.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description  TravelAccommodation 
 * @Author  yzh
 * @Date 2022-02-09 
 */
@Data
@ApiModel
public class TravelAccommodationDTO implements Serializable {

	private static final long serialVersionUID =  1L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("ID")
	private Long id;

	/**
	 * 住宿信息
	 */
	@ApiModelProperty("住宿信息")
	private String accommodationInformation;

	/**
	 * 标题
	 */
	@ApiModelProperty("标题")
	private String title;

	/**
	 * 住宿建议
	 */
	@ApiModelProperty("住宿建议")
	private String accommodationAdvice;

	/**
	 * 标题ID
	 */
	@ApiModelProperty("标题ID")
	private Long titleId;


}
