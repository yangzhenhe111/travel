package com.cy.travels.utils.dto;

import com.google.gson.Gson;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * @description: ResultResponse
 */
@Data
public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = 3468352004150968551L;

    /**
     * 状态码
     */
    private Boolean isSuccess;

    /**
     * 消息
     */
    private String msg;

    /**
     * 返回对象
     */
    private T data;

    public ResultResponse() {
    }

    public ResultResponse(Boolean isSuccess, String msg) {
        this.isSuccess = isSuccess;
        this.msg = msg;
    }

    public ResultResponse(Boolean isSuccess, T data) {
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public ResultResponse(Boolean isSuccess, String msg, T data) {
        this.isSuccess = isSuccess;
        this.msg = msg;
        this.data = data;
    }

    public static ResultResponse ok() {
        return new ResultResponse(true, "请求成功");
    }

    public static ResultResponse ok(Object data) {
        return new ResultResponse(true, "请求成功", data);
    }

    public static ResultResponse ok(String msg) {
        return new ResultResponse(true, msg);
    }

    public static ResultResponse ok(String msg, Object data) {
        return new ResultResponse(true, msg, data);
    }

    public static ResultResponse fail(String msg) {
        return new ResultResponse(false, msg);
    }
    /*
    public ResultResponse(String message) {
        put("isSuccess",false);
        put("msg",message);
    }

    public ResultResponse() {
        put("isSuccess",true);
        put("msg","操作成功");
    }
    //成功
    public static ResultResponse ok(){
        return new ResultResponse();
    }
    public static ResultResponse ok(String msg){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.put("msg",msg);
        return resultResponse;
    }
    //失败
    public static ResultResponse fail(String msg){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.put("isSuccess",false);
        resultResponse.put("msg",msg);
        return resultResponse;
    }
    //成功设置对象值
    public ResultResponse put(String paramkey, Object paramValue){
        super.put(paramkey,paramValue);
        return this;
    }*/


}