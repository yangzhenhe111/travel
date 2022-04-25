package cn.Travels_App.model.entity;

import java.io.Serializable;

public class TravelLocalOverview implements Serializable {

    /**
     * id
     */
    private Long id;

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
    private String openTime;

    /**
     * 简介
     */
    private String briefDesc;

//	/**
//	 * 创建时间
//	 */
//	@Column
//	@ApiModelProperty( value="创建时间")
//	private Date createtime;
//
//	/**
//	 * 修改时间
//	 */
//	@Column
//	@ApiModelProperty( value="修改时间")
//	private Date modifytime;

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

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
        this.briefDesc = briefDesc;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }
}
