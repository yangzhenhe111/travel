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

}
