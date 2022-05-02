package cn.Travels_App.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.Travels_App.model.entity.Comment;
import retrofit2.http.FormUrlEncoded;

public class CommentRespDTO implements Serializable {

    private static final long serialVersionUID =  1L;

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

    private CommentRespDTO parentComment;

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
    private String createTime;

    /**
     * 评论者姓名
     */
    private String username;

    /**
     * 评论者头像
     */
    private String headImg;


    private List<Comment> childCommentList;

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

    public CommentRespDTO getParentComment() {
        return parentComment;
    }

    public void setParentComment(CommentRespDTO parentComment) {
        this.parentComment = parentComment;
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

    public List<Comment> getChildCommentList() {
        return childCommentList;
    }

    public void setChildCommentList(List<Comment> childCommentList) {
        this.childCommentList = childCommentList;
    }
}
