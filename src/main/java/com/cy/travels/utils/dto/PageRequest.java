package com.cy.travels.utils.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class PageRequest<T> {

    @ApiModelProperty("每页数量")
    private int pageNum;

    @ApiModelProperty("页码")
    private int pageSize;

    @ApiModelProperty("参数")
    private T data;

}
