package com.cy.travels.model.entity;

import com.cy.travels.model.BaseDO;
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
@Entity
@Table ( name ="travel_accommodation" )
@ApiModel
public class TravelAccommodation extends BaseDO implements Serializable {

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
	@Column
	@ApiModelProperty("住宿信息")
	private String accommodationInformation;

	/**
	 * 标题
	 */
	@Column
	@ApiModelProperty("标题")
	private String title;

	/**
	 * 住宿建议
	 */
	@Column
	@ApiModelProperty("住宿建议")
	private String accommodationAdvice;

	/**
	 * 标题ID
	 */
	@Column
	@ApiModelProperty("标题ID")
	private Long titleId;


}
