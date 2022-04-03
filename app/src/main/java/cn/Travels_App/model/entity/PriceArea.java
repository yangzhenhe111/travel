package cn.Travels_App.model.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: PriceArea
 * @author: soulcoder 灵魂码仔
 * @email: 2579692606@qq.com
 * @date: created by 2021/10/30 15:13
 * @copyright: itxfq 项目分享圈
 */

public class PriceArea implements Serializable {

    private Long id;
    private String pricefw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPricefw() {
        return pricefw;
    }

    public void setPricefw(String pricefw) {
        this.pricefw = pricefw;
    }

    public PriceArea(){}

    public PriceArea(Long id,String pricefw){
        this.id = id;
        this.pricefw = pricefw;
    }

    public List<PriceArea> getPriceAreaDatas(){
        List<PriceArea> priceArea = new ArrayList<>();
        priceArea.add(new PriceArea(1L,"0-99"));
        priceArea.add(new PriceArea(2L,"100-199"));
        priceArea.add(new PriceArea(3L,"200-299"));
        priceArea.add(new PriceArea(4L,"300-399"));
        priceArea.add(new PriceArea(5L,"400-499"));
        priceArea.add(new PriceArea(6L,"500以上"));
        return priceArea;
    }

    public Long getPricefwidByName(String pricefw){
        List<PriceArea> priceAreaDatas = getPriceAreaDatas();
        for (int i = 0; i < priceAreaDatas.size(); i++) {
            PriceArea priceArea = priceAreaDatas.get(i);
            if(priceArea.getPricefw().equals(pricefw)){
                return priceArea.getId();
            }
        }
        return 0L;
    }
}
