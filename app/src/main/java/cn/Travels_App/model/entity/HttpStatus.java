package cn.Travels_App.model.entity;


import cn.Travels_App.common.Constants;
import com.google.gson.annotations.SerializedName;

/**
 * itfxq
 * @since 2021/2/23
 */
public class HttpStatus {

    @SerializedName("code")
    private String code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("isSuccess")
    private boolean isSuccess;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    /**
     * API是否请求失败
     *
     * @return 失败返回true, 成功返回false
     */
    public boolean codeIsInvalid() {
        return !code.equals(Constants.REQUEST_CODE);
    }

    public boolean statusIsFailure() {
        return !isSuccess;

    }
}
