package cn.Travels_App.model.entity;

import java.io.Serializable;
import java.util.List;

public class CommentResp implements Serializable {

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
    //父级评论内容
    private Comment parentComment;

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
    //子级评论
    private List<Comment> childCommentList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
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

    public CommentResp(String nickName,  String content, String createDate) {
        this.username = nickName;
        this.content = content;
        this.createTime = createDate;
    }
}
