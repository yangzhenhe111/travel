package com.cy.travels.model.entity;

import com.cy.travels.model.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description TravelDelicacy
 * @Author yzh
 * @Date 2022-02-09
 */
@Data
@Entity
@Table(name = "travel_delicacy")
@ApiModel
public class TravelDelicacy extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Long id;

    /**
     * 餐馆名称
     */
    @Column
    @ApiModelProperty("餐馆名称")
    private String restaurantName;

    /**
     * 标题
     */
    @Column
    @ApiModelProperty("标题")
    private String title;

    /**
     * 推荐菜品
     */
    @Column
    @ApiModelProperty("推荐菜品")
    private String recommendDishes;

//	/**
//	 * 人均消费
//	 */
//	@Column
//	@ApiModelProperty("人均消费")
//	private String averageConsumption;

    /**
     * 标题ID
     */
    @Column
    @ApiModelProperty("标题ID")
    private Long titleId;


}
