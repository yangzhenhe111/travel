package com.cy.travels.model.entity;

import com.cy.travels.model.BaseDO;
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
@Entity
@Table ( name ="travel_place" )
@ApiModel
public class TravelPlace extends BaseDO implements Serializable {

	private static final long serialVersionUID =  1L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("ID")
	private Long id;

	/**
	 * 景点介绍
	 */
	@Column
	@ApiModelProperty("景点介绍")
	private String placeInformation;

	/**
	 * 标题
	 */
	@Column
	@ApiModelProperty("标题")
	private String title;

	/**
	 * 游玩建议
	 */
	@Column
	@ApiModelProperty("游玩建议")
	private String travelAdvice;

	/**
	 * 标题ID
	 */
	@Column
	@ApiModelProperty("标题ID")
	private Long titleId;


}
