package cn.Travels_App.model.entity;



import java.io.Serializable;

/**
 * @description: Custorm 顾客
 * @author: soulcoder 灵魂码仔
 * @email: 2579692606@qq.com
 * @date: created by 2021/10/29
 * @copyright: itxfq 项目分享圈
 */

public class Custorm implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String headImg;
    private String tel;
    private String createtime;
    private Long totalpoints;
    private String captchaVal;
    private String captchaToken;
    private String account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Long getTotalpoints() {
        return totalpoints;
    }

    public void setTotalpoints(Long totalpoints) {
        this.totalpoints = totalpoints;
    }

    public String getCaptchaVal() {
        return captchaVal;
    }

    public void setCaptchaVal(String captchaVal) {
        this.captchaVal = captchaVal;
    }

    public String getCaptchaToken() {
        return captchaToken;
    }

    public void setCaptchaToken(String captchaToken) {
        this.captchaToken = captchaToken;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
