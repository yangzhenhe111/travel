package cn.Travels_App.model.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: City城市类
 * @author: soulcoder 灵魂码仔
 * @email: 2579692606@qq.com
 * @date: created by 2021/10/29
 * @copyright: itxfq 项目分享圈
 */

public class City implements Serializable {
    private Long id;
    private String name;
    private Long parentid;

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

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public City(){}

    public City(Long id,String name,Long parentid){
        this.id = id;
        this.name = name;
        this.parentid = parentid;
    }

    //获取城市数据
    public static List<City> getCityDatas(){
        List<City> cityList = new ArrayList();
        City city1 = new City(1L,"成都市",0L);
        City city2 = new City(2L,"自贡市",0L);
        City city3 = new City(3L,"攀枝花",0L);
        City city4 = new City(4L,"泸州市",0L);
        City city5 = new City(5L,"德阳市",0L);
        City city6 = new City(6L,"绵阳市",0L);
        City city7 = new City(7L,"广元市",0L);
        City city8 = new City(8L,"遂宁市",0L);
        City city9 = new City(9L,"内江市",0L);
        City city10 = new City(10L,"乐山市",0L);
        City city11 = new City(11L,"南充市",0L);
        City city12 = new City(12L,"宜宾市",0L);
        City city13 = new City(13L,"广安市",0L);
        City city14 = new City(14L,"达州市",0L);
        City city15 = new City(15L,"眉山市",0L);
        City city16 = new City(16L,"雅安市",0L);
        City city17 = new City(17L,"巴中市",0L);
        City city18 = new City(18L,"资阳市",0L);
        City city19 = new City(19L,"阿坝州",0L);
        City city20 = new City(20L,"甘孜州",0L);
        City city21 = new City(21L,"凉山州",0L);

        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);
        cityList.add(city4);
        cityList.add(city5);
        cityList.add(city6);
        cityList.add(city7);
        cityList.add(city8);
        cityList.add(city9);
        cityList.add(city10);
        cityList.add(city11);
        cityList.add(city12);
        cityList.add(city13);
        cityList.add(city14);
        cityList.add(city15);
        cityList.add(city16);
        cityList.add(city17);
        cityList.add(city18);
        cityList.add(city19);
        cityList.add(city20);
        cityList.add(city21);

        return cityList;
    }

    //通过内容获取id
    public static Long getCityIdByName(String name){
        List<City> cityList = getCityDatas();
        for (int i = 0; i < cityList.size() ; i++) {
            City city = cityList.get(i);
            if(city.getName().equals(name)){
                return city.id;
            }
        }
        return 0L;
    }
}
