package com.cy.travels.enums;

public enum TravelStatusEnum {
    SAVE("1", "保存"),
    PUBLISH("2", "发布");
    private String code;
    private String desc;

    TravelStatusEnum(String code, String desc) {
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
