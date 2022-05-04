package cn.Travels_App.model.dto;

import java.io.Serializable;
import java.util.Date;

public class TravelsHistoryDTO implements Serializable {
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 游记ID
     */
    private Long travelsId;

    /**
     * 发布时间
     */
    private String publishDate;

    /**
     * 景点封面
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
     * 创建时间
     */
    private String createTime;


    /**
     * 作者姓名
     */
    private String username;

    /**
     * 作者头像
     */
    private String headImg;

    public TravelsHistoryDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTravelsId() {
        return travelsId;
    }

    public void setTravelsId(Long travelsId) {
        this.travelsId = travelsId;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
