package com.cy.travels.model.dto;

import com.cy.travels.model.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  Comment 
 * @Author  yzh
 * @Date 2022-02-16 
 */
@Data
@ApiModel
public class CommentDTO implements Serializable {

	private static final long serialVersionUID =  1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 内容
	 */
	@ApiModelProperty( value="内容")
	private String content;

	/**
	 * 父评论ID
	 */
	@ApiModelProperty( value="父评论ID")
	private Long parentId;

	/**
	 * 所属游记id
	 */
	@ApiModelProperty( value="所属游记id")
	private Long travelsId;

	/**
	 * 评论用户
	 */
	@ApiModelProperty( value="评论用户")
	private Long userId;

	/**
	 * 点赞数
	 */
	@ApiModelProperty( value="点赞数")
	private Long likeNum;


	/**
	 * 评论时间
	 */
	@ApiModelProperty( value="评论时间")
	private Date createTime;

	/**
	 * 评论者姓名
	 */
	@Column
	@ApiModelProperty( value="评论者姓名")
	private String username;

	/**
	 * 评论者头像
	 */
	@Column
	@ApiModelProperty( value="评论者头像")
	private String headImg;

}
