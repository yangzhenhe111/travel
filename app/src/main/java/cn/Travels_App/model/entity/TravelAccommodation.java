package cn.Travels_App.model.entity;

import java.io.Serializable;

public class TravelAccommodation implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 住宿信息
     */
    private String accommodationInformation;

    /**
     * 标题
     */
    private String title;

    /**
     * 住宿建议
     */
    private String accommodationAdvice;

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

    public String getAccommodationInformation() {
        return accommodationInformation;
    }

    public void setAccommodationInformation(String accommodationInformation) {
        this.accommodationInformation = accommodationInformation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccommodationAdvice() {
        return accommodationAdvice;
    }

    public void setAccommodationAdvice(String accommodationAdvice) {
        this.accommodationAdvice = accommodationAdvice;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }
}
