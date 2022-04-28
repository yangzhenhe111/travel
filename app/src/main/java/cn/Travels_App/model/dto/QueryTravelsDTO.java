package cn.Travels_App.model.dto;


import java.io.Serializable;
import java.util.Date;

public class QueryTravelsDTO implements Serializable {

    private static final long serialVersionUID =  1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 发布时间
     */
    private Date publishDateStart;
    private Date publishDateEnd;

    /**
     * 景点名称
     */
    private String name;

    /**
     * 所在地
     */
    private String address;

    /**
     * 开放时间
     */
    private String opentimeStart;
    private String opentimeEnd;

    /**
     * 预算
     */
    private Double budgetMin;
    private Double budgetMax;

    /**
     * 简介
     */
    private String briefDesc;

    /**
     * 信息
     * 交通/餐饮/酒店
     * 搜索框输入信息(模糊搜索),景点名/地址/简介/开放时间/发布时间/创建者等，取并集,再与其他的查询条件取交集
     */
    private String info;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 创建者姓名
     */
    private String creatorName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPublishDateStart() {
        return publishDateStart;
    }

    public void setPublishDateStart(Date publishDateStart) {
        this.publishDateStart = publishDateStart;
    }

    public Date getPublishDateEnd() {
        return publishDateEnd;
    }

    public void setPublishDateEnd(Date publishDateEnd) {
        this.publishDateEnd = publishDateEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpentimeStart() {
        return opentimeStart;
    }

    public void setOpentimeStart(String opentimeStart) {
        this.opentimeStart = opentimeStart;
    }

    public String getOpentimeEnd() {
        return opentimeEnd;
    }

    public void setOpentimeEnd(String opentimeEnd) {
        this.opentimeEnd = opentimeEnd;
    }

    public Double getBudgetMin() {
        return budgetMin;
    }

    public void setBudgetMin(Double budgetMin) {
        this.budgetMin = budgetMin;
    }

    public Double getBudgetMax() {
        return budgetMax;
    }

    public void setBudgetMax(Double budgetMax) {
        this.budgetMax = budgetMax;
    }

    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
        this.briefDesc = briefDesc;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
