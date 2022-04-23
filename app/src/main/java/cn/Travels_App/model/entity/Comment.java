package cn.Travels_App.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 内容
     */
    private String content;

    /**
     * 父评论ID
     */
    private Long parentId;

    /**
     * 所属游记id
     */
    private Long travelsId;

    /**
     * 评论用户
     */
    private Long userId;

    /**
     * 点赞数
     */
    private Long likeNum;


    /**
     * 评论时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getTravelsId() {
        return travelsId;
    }

    public void setTravelsId(Long travelsId) {
        this.travelsId = travelsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
