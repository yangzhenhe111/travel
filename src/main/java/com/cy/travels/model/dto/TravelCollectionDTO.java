package com.cy.travels.model.dto;

import com.cy.travels.model.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class TravelCollectionDTO extends BaseDO implements Serializable {

    private static final long serialVersionUID =  1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Long id;

    /**
     * 用户ID
     */
    @Column
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 游记ID
     */
    @Column
    @ApiModelProperty("游记ID")
    private Long travelId;

    /**
     * 创建时间
     */
    @Column
    @ApiModelProperty("创建时间")
    private Date creatDate;

    /**
     * 作者姓名
     */
    @ApiModelProperty( value="作者姓名")
    private String username;

    /**
     * 作者头像
     */
    @ApiModelProperty( value="作者头像")
    private String headImg;

    /**
     * 发布时间
     */
    @ApiModelProperty( value="发布时间")
    private Date publishDate;

    /**
     * 景点封面
     */
    @ApiModelProperty( value="景点封面")
    private String cover;

    /**
     * 景点名称
     */
    @ApiModelProperty( value="景点名称")
    private String name;

    /**
     * 所在地
     */
    @ApiModelProperty( value="所在地")
    private String address;

    /**
     * 开放时间
     */
    @ApiModelProperty( value="开放时间")
    private String opentime;

}
