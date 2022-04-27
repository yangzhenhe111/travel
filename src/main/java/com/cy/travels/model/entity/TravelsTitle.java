package com.cy.travels.model.entity;

import com.cy.travels.model.BaseDO;
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
@Entity
@Table ( name ="travel_title" )
@ApiModel
public class TravelsTitle extends BaseDO implements Serializable {

	private static final long serialVersionUID =  1L;

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("id")
	private Long id;

	/**
	 * 标题内容
	 */
	@Column
	@ApiModelProperty("标题内容")
	private String titleContent;

	/**
	 * 游记封面
	 */
	@Column
	@ApiModelProperty("游记封面")
	private String travelCover;

	/**
	 * 发布时间
	 */
	@Column
	@ApiModelProperty("发布时间")
	private Date publishDate;


	/**
	 * 创建时间
	 */
	@Column
	@ApiModelProperty( value="创建时间")
	private Date createtime;

	/**
	 * 状态（1.保存2.发布）
	 */
	@Column
	@ApiModelProperty("状态（1.保存2.发布）")
	private String status;


	/**
	 * 创建者
	 */
	@Column
	@ApiModelProperty( value="创建者")
	private Long creator;

}
