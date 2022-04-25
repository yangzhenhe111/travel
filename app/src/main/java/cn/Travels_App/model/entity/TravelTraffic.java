package cn.Travels_App.model.entity;

import java.io.Serializable;

public class TravelTraffic implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 交通信息
     */
    private String trafficInformation;

    /**
     * 标题
     */
    private String title;

    /**
     * 交通建议
     */
    private String trafficAdvice;

    /**
     * 标题ID
     */
    private Long titleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrafficInformation() {
        return trafficInformation;
    }

    public void setTrafficInformation(String trafficInformation) {
        this.trafficInformation = trafficInformation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrafficAdvice() {
        return trafficAdvice;
    }

    public void setTrafficAdvice(String trafficAdvice) {
        this.trafficAdvice = trafficAdvice;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }
}
