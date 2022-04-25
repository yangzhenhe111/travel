package cn.Travels_App.model.entity;

import java.io.Serializable;
import java.util.Date;

public class TravelsTitle implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题内容
     */
    private String titleContent;

    /**
     * 游记封面
     */
    private String travelCover;

    /**
     * 发布时间
     */
    private Date publishDate;


    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 创建者
     */
    private Long creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(String titleContent) {
        this.titleContent = titleContent;
    }

    public String getTravelCover() {
        return travelCover;
    }

    public void setTravelCover(String travelCover) {
        this.travelCover = travelCover;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }
}
