package cn.Travels_App.model.entity;


import java.io.Serializable;

/**
 * @description: Hotel 酒店类
 * @author: soulcoder 灵魂码仔
 * @email: 2579692606@qq.com
 * @date: created by 2021/10/29
 * @copyright: itxfq 项目分享圈
 */

public class Hotel implements Serializable {

    private Long id;

    private String hotelname;

    private Double hotelprice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public Double getHotelprice() {
        return hotelprice;
    }

    public void setHotelprice(Double hotelprice) {
        this.hotelprice = hotelprice;
    }
}
