package com.cy.travels.enums;

public enum LoginStatusEnum {
    LOGIN("1", "已登录"),
    LOGOUT("0", "未登录");
    private String code;
    private String desc;

    LoginStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
