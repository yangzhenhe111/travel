package com.cy.travels.config;

import com.cy.travels.enums.ResultEnum;
import com.cy.travels.exception.BusinessException;
import com.cy.travels.utils.ErrorUtil;
import com.cy.travels.utils.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * RestControllerAdvice，统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerConfig {


    /**
     * 业务异常处理
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result exceptionHandler(BusinessException e) {
        log.error(ErrorUtil.errorInfoToString(e));
        return Result.fail(e.getCode(), e.getErrorMsg());
    }

    /**
     * 未知异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        // 把错误信息输入到日志中
        log.error(ErrorUtil.errorInfoToString(e));
        return Result.fail(ResultEnum.UNKNOWN.getCode(),
                ResultEnum.UNKNOWN.getMsg());
    }

//    /**
//     * 错误页面异常
//     */
//    @ExceptionHandler(value = ErrorPageException.class)
//    @ResponseBody
//    public ApiResponse exceptionHandler(ErrorPageException e) {
//        log.error(ErrorUtil.errorInfoToString(e));
//        return ApiResponse.error(e.getCode(), e.getErrorMsg());
//    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(NullPointerException e) {
        log.error(ErrorUtil.errorInfoToString(e));
        return Result.fail(ResultEnum.INTERNAL_SERVER_ERROR.getCode(),
                ResultEnum.INTERNAL_SERVER_ERROR.getMsg());
    }
}
