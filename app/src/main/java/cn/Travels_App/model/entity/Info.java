package cn.Travels_App.model.entity;

import java.util.Date;

public class Info {
    /**
     * ID
     */
    private Long id;
    /**
     * 发布时间
     */
    private Date publishDate;
    /**
     * 封面介绍（图片）
     */
    private String cover;
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
    private String opentime;
    /**
     * 预算
     */
    private Double budget;
    /**
     * 简介
     */
    private String briefDesc;
    /**
     * 交通信息
     */
    private String trafficInfo;
    /**
     * 餐饮信息
     */
    private String resraurantInfo;
    /**
     * 酒店信息
     */
    private String hotelInfo;
    /**
     * 创建时间
     */
    private Date createtime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
        this.briefDesc = briefDesc;
    }

    public String getTrafficInfo() {
        return trafficInfo;
    }

    public void setTrafficInfo(String trafficInfo) {
        this.trafficInfo = trafficInfo;
    }

    public String getResraurantInfo() {
        return resraurantInfo;
    }

    public void setResraurantInfo(String resraurantInfo) {
        this.resraurantInfo = resraurantInfo;
    }

    public String getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(String hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
