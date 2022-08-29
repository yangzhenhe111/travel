package com.cy.travels.utils.dto;

import com.cy.travels.enums.ResultEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class Result<T> {

    private String status;

    private String message;

    private T data;

    public Result() {
    }

    public Result(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


    public Result(String status, T data) {
        this.status = status;
        this.message = "";
        this.data = data;
    }


    public Result(String status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static Result ok() {
        return new Result(ResultEnum.OK.getCode(), ResultEnum.OK.getMsg());
    }

    public static Result ok(Object data) {
        return new Result(ResultEnum.OK.getCode(), data);
    }

    public static Result ok(String message, Object data) {
        return new Result(ResultEnum.OK.getCode(), message, data);
    }

    public static Result ok(String message) {
        return new Result(ResultEnum.OK.getCode(), message);
    }

    public static Result fail(String status, String message) {
        return new Result(status, message);
    }

    public static Result fail() {
        return new Result(ResultEnum.UNKNOWN.getCode(), ResultEnum.UNKNOWN.getMsg());
    }

    public static Result build(ResultEnum eunm) {
        return new Result(eunm.getCode(), eunm.getMsg());
    }

    public static Result build(String status, String message, Object data) {
        return new Result(status, message, data);
    }
}
