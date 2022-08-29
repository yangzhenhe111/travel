package com.cy.travels.model.dto;

import com.cy.travels.model.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description TravelsHistory
 * @Author yzh
 * @Date 2022-02-20
 */
@Data
@ApiModel
public class TravelsHistoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户ID
     */
    @NonNull
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 游记ID
     */
    @NonNull
    @ApiModelProperty(value = "游记ID")
    private Long travelsId;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private Date publishDate;

    /**
     * 景点封面
     */
    @ApiModelProperty(value = "景点封面")
    private String cover;

    /**
     * 景点名称
     */
    @ApiModelProperty(value = "景点名称")
    private String name;

    /**
     * 所在地
     */
    @ApiModelProperty(value = "所在地")
    private String address;

    /**
     * 开放时间
     */
    @ApiModelProperty(value = "开放时间")
    private String opentime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    /**
     * 作者姓名
     */
    @ApiModelProperty(value = "作者姓名")
    private String username;

    /**
     * 作者头像
     */
    @ApiModelProperty(value = "作者头像")
    private String headImg;

    public TravelsHistoryDTO() {

    }
}
