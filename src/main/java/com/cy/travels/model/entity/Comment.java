package com.cy.travels.model.entity;

import com.cy.travels.model.BaseDO;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;

import java.util.Date;

/**
 * @Description Comment
 * @Author yzh
 * @Date 2022-02-21
 */
@Data
@Entity
@Table(name = "comment")
@ApiModel
public class Comment extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 内容
     */
    @Column
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 父评论ID
     */
    @Column
    @ApiModelProperty(value = "父评论ID")
    private Long parentId;

    /**
     * 所属游记id
     */
    @Column
    @ApiModelProperty(value = "所属游记id")
    private Long travelsId;

    /**
     * 评论用户
     */
    @Column
    @ApiModelProperty(value = "评论用户")
    private Long userId;

    /**
     * 点赞数
     */
    @Column
    @ApiModelProperty(value = "点赞数")
    private Long likeNum;


    /**
     * 评论时间
     */
    @Column
    @ApiModelProperty(value = "评论时间")
    private Date createTime;


}
