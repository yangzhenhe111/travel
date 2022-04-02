package com.cy.travels.model.dto;

import com.cy.travels.model.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  User 
 * @Author  yzh
 * @Date 2022-02-16 
 */
@Data
@ApiModel
public class UserDTO implements Serializable {

	private static final long serialVersionUID =  1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 用户名
	 */
	@ApiModelProperty( value="用户名")
	private String username;

	/**
	 * 邮箱
	 */
	@ApiModelProperty( value="邮箱")
	private String email;

	/**
	 * 手机号
	 */
	@ApiModelProperty( value="手机号")
	private String tel;

	/**
	 * 总点数
	 */
	@ApiModelProperty( value="总点数")
	private Long totalPoints;

	/**
	 * 密码
	 */
	@ApiModelProperty( value="密码")
	private String password;

	/**
	 * 头像名
	 */
	@ApiModelProperty( value="头像名")
	private String headImg;

	/**
	 * 创建时间
	 */
	@ApiModelProperty( value="创建时间")
	private Date createtime;

//	public void setId(Long id) {
//		this.id = Long.parseLong(id.toString());
//	}
}
