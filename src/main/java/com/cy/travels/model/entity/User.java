package com.cy.travels.model.entity;

import com.cy.travels.model.BaseDO;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;

import java.util.Date;

/**
 * @Description User
 * @Author yzh
 * @Date 2022-02-20
 */
@Data
@Entity
@Table(name = "user")
@ApiModel
public class User extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 用户名
     */
    @Column
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 邮箱
     */
    @Column
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @Column
    @ApiModelProperty(value = "手机号")
    private String tel;

    /**
     * 总点数
     */
    @Column
    @ApiModelProperty(value = "总点数")
    private Long totalPoints;

    /**
     * 密码
     */
    @Column
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 头像名
     */
    @Column
    @ApiModelProperty(value = "头像名")
    private String headImg;

    /**
     * 创建时间
     */
    @Column
    @ApiModelProperty(value = "创建时间")
    private Date createtime;


    /**
     * 登录状态
     */
    @Column
    @ApiModelProperty(value = "登录状态")
    private String loginStatus;

    /**
     * 性别
     */
    @Column
    private String sex;

    /**
     * 签名
     */
    @Column
    private String signature;

}
