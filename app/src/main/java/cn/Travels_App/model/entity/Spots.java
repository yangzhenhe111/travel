package cn.Travels_App.model.entity;

import java.io.Serializable;

public class Spots implements Serializable {

    private Long id;
    private String name;
    private String address;
    private String opentime;
    private Double price;
    private String tel;
    private String spotsDesc;
    private String info;
    private String purchaseinfo;
    private String features;
    private Long spotsid;
    private City city;
    private Long levelid;
    private Level level;
    private Long themeid;
    private Theme theme;
    private String createtime;
    private Long salenum;
    private Long creatorid;
    private String fmUrl;

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

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSpotsDesc() {
        return spotsDesc;
    }

    public void setSpotsDesc(String spotsDesc) {
        this.spotsDesc = spotsDesc;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPurchaseinfo() {
        return purchaseinfo;
    }

    public void setPurchaseinfo(String purchaseinfo) {
        this.purchaseinfo = purchaseinfo;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public Long getSpotsid() {
        return spotsid;
    }

    public void setSpotsid(Long spotsid) {
        this.spotsid = spotsid;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getLevelid() {
        return levelid;
    }

    public void setLevelid(Long levelid) {
        this.levelid = levelid;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Long getThemeid() {
        return themeid;
    }

    public void setThemeid(Long themeid) {
        this.themeid = themeid;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Long getSalenum() {
        return salenum;
    }

    public void setSalenum(Long salenum) {
        this.salenum = salenum;
    }

    public Long getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(Long creatorid) {
        this.creatorid = creatorid;
    }

    public String getFmUrl() {
        return fmUrl;
    }

    public void setFmUrl(String fmUrl) {
        this.fmUrl = fmUrl;
    }
}
