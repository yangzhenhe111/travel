package cn.Travels_App.model.entity;

import java.io.Serializable;

/**
 * @description: CasesEntity
 * @author:marker
 * @copyright:www.itfxq.cn
 * @email:2579692606@qq.com
 * @createTime 2021/9/30 11:16
 */
public class CasesEntity implements Serializable {
    private Long id;
    //案例名称
    private String casename;
    //患者姓名
    private String sickname;
    //案例内容
    private String content;
    //主治医生
    private String doctor;
    //就诊时间
    private String jztime;
    //患者年龄
    private String sickage;
    //患者性别
    private String sicksex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename;
    }

    public String getSickname() {
        return sickname;
    }

    public void setSickname(String sickname) {
        this.sickname = sickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getJztime() {
        return jztime;
    }

    public void setJztime(String jztime) {
        this.jztime = jztime;
    }

    public String getSickage() {
        return sickage;
    }

    public void setSickage(String sickage) {
        this.sickage = sickage;
    }

    public String getSicksex() {
        return sicksex;
    }

    public void setSicksex(String sicksex) {
        this.sicksex = sicksex;
    }
}
