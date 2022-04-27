package com.cy.travels.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  TravelsTitle 
 * @Author  yzh
 * @Date 2022-02-09 
 */
@Data
@ApiModel
public class TravelsTitleDTO implements Serializable {

	private static final long serialVersionUID =  1L;

	/**
	 * id
	 */
	@ApiModelProperty("id")
	private Long id;

	/**
	 * 标题内容
	 */
	@ApiModelProperty("标题内容")
	private String titleContent;

	/**
	 * 游记封面
	 */
	@ApiModelProperty("游记封面")
	private String travelCover;

	/**
	 * 发布时间
	 */
	@ApiModelProperty("发布时间")
	private Date publishDate;


	/**
	 * 创建时间
	 */
	@ApiModelProperty( value="创建时间")
	private Date createtime;

	/**
	 * 状态（1.保存2.发布）
	 */
	@ApiModelProperty("状态（1.保存2.发布）")
	private String status;

	/**
	 * 创建者
	 */
	@ApiModelProperty( value="创建者")
	private Long creator;

	/**
	 * 创建者姓名
	 */
	@Column
	@ApiModelProperty( value="创建者姓名")
	private String creatorName;

	/**
	 * 创建者头像
	 */
	@Column
	@ApiModelProperty( value="创建者头像")
	private String creatorCover;

}
