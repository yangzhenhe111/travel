package cn.Travels_App.network;

/**
 * HttpResult
 */

public class HttpResult<T> {

    // 请求状态
    private boolean isSuccess;

    private String msg;

    private T data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "isSuccess=" + isSuccess +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
