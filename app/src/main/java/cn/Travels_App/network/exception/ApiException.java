package cn.Travels_App.network.exception;

/**
 * itfxq
 * @since 2021/7/10
 */

public class ApiException extends RuntimeException {
    private String mErrorCode;

    public ApiException(String errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }


}


