package com.cy.travels.utils.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class PageBean<E> {

    @ApiModelProperty("存放实体类集合")
    private List<E> data ;            // 存放实体类集合

    @ApiModelProperty("当前页")
    private int currentPage ;    // 当前页

    @ApiModelProperty("每页显示的条数")
    private int pageSize ;        // 每页显示的条数

    @ApiModelProperty("总页数")
    private int totalPage ;        // 总页数

    @ApiModelProperty("总条数")
    private int totalCount ;    // 总条数


}