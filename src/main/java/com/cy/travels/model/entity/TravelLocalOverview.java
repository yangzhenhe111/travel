package com.cy.travels.model.entity;

import com.cy.travels.model.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  TravelLocalOverview 
 * @Author  yzh
 * @Date 2022-02-09 
 */
@Data
@Entity
@Table ( name ="travel_local_overview" )
@ApiModel
public class TravelLocalOverview extends BaseDO implements Serializable {

	private static final long serialVersionUID =  1L;

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("id")
	private Long id;

	/**
	 * 景点名称
	 */
	@Column
	@ApiModelProperty( value="景点名称")
	private String name;

	/**
	 * 所在地
	 */
	@Column
	@ApiModelProperty( value="所在地")
	private String address;

	/**
	 * 开放时间
	 */
	@Column
	@ApiModelProperty( value="开放时间")
	private String openTime;

	/**
	 * 简介
	 */
	@Column
	@ApiModelProperty( value="简介")
	private String briefDesc;

//	/**
//	 * 创建时间
//	 */
//	@Column
//	@ApiModelProperty( value="创建时间")
//	private Date createtime;
//
//	/**
//	 * 修改时间
//	 */
//	@Column
//	@ApiModelProperty( value="修改时间")
//	private Date modifytime;

	/**
	 * 标题ID
	 */
	@Column
	@ApiModelProperty("标题ID")
	private Long titleId;


}
