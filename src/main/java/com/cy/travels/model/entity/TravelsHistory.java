package com.cy.travels.model.entity;

import com.cy.travels.model.BaseDO;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;

import java.util.Date;

/**
 * @Description TravelsHistory
 * @Author yzh
 * @Date 2022-02-21
 */
@Data
@Entity
@Table(name = "travels_history")
@ApiModel
public class TravelsHistory extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 用户ID
     */
    @Column
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 游记ID
     */
    @Column
    @ApiModelProperty(value = "游记ID")
    private Long travelsId;

    /**
     * 创建时间
     */
    @Column
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
