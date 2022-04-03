package cn.Travels_App.model.entity;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Order 订单
 * @author: soulcoder 灵魂码仔
 * @email: 2579692606@qq.com
 * @date: created by 2021/10/29
 * @copyright: itxfq 项目分享圈
 */

public class Order implements Serializable {
    private Long id;
    private Long spotsid;
    private String spotsname;
    private String spotstel;


    private String traveltime;
    private Long adultsnum;
    private Long childnum;
    private double hotelprice;
    private double totalprice;

    private List<OrderDetail> travels = new ArrayList();
    private String ordernum;
    private String linkname;
    private String linktel;
    private String linkemail;
    private Long hotelid;
    private Long hotelnum;
    private String hotellinktel;
    private Hotel hotel;

    private Long orderstatus;

    private String createtime;
    private Long creatorid;
    private Custorm custorm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpotsid() {
        return spotsid;
    }

    public void setSpotsid(Long spotsid) {
        this.spotsid = spotsid;
    }

    public String getSpotsname() {
        return spotsname;
    }

    public void setSpotsname(String spotsname) {
        this.spotsname = spotsname;
    }

    public String getSpotstel() {
        return spotstel;
    }

    public void setSpotstel(String spotstel) {
        this.spotstel = spotstel;
    }

    public String getTraveltime() {
        return traveltime;
    }

    public void setTraveltime(String traveltime) {
        this.traveltime = traveltime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Long getAdultsnum() {
        return adultsnum;
    }

    public void setAdultsnum(Long adultsnum) {
        this.adultsnum = adultsnum;
    }

    public Long getChildnum() {
        return childnum;
    }

    public void setChildnum(Long childnum) {
        this.childnum = childnum;
    }

    public double getHotelprice() {
        return hotelprice;
    }

    public void setHotelprice(double hotelprice) {
        this.hotelprice = hotelprice;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public List<OrderDetail> getTravels() {
        return travels;
    }

    public void setTravels(List<OrderDetail> travels) {
        this.travels = travels;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }

    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linktel) {
        this.linktel = linktel;
    }

    public String getLinkemail() {
        return linkemail;
    }

    public void setLinkemail(String linkemail) {
        this.linkemail = linkemail;
    }

    public Long getHotelid() {
        return hotelid;
    }

    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }

    public Long getHotelnum() {
        return hotelnum;
    }

    public void setHotelnum(Long hotelnum) {
        this.hotelnum = hotelnum;
    }

    public String getHotellinktel() {
        return hotellinktel;
    }

    public void setHotellinktel(String hotellinktel) {
        this.hotellinktel = hotellinktel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Long getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Long orderstatus) {
        this.orderstatus = orderstatus;
    }



    public Long getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(Long creatorid) {
        this.creatorid = creatorid;
    }

    public Custorm getCustorm() {
        return custorm;
    }

    public void setCustorm(Custorm custorm) {
        this.custorm = custorm;
    }
}
