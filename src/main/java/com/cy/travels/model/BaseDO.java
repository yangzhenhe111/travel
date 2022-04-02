package com.cy.travels.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@ApiModel
public class BaseDO implements Serializable {

    @Column
    @ApiModelProperty(value = "已删除（Y/N)")
    private String isDeleted;
}
