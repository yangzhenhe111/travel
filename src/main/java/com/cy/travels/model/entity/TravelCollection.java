package com.cy.travels.model.entity;

import com.cy.travels.model.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table ( name ="travel_collection" )
@ApiModel
public class TravelCollection extends BaseDO implements Serializable {

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
    private Long userId;

    /**
     * 游记ID
     */
    @Column
    private Long travelId;

    /**
     * 创建时间
     */
    @Column
    private Date createdDate;

}
