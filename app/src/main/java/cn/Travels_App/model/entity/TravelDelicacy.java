package cn.Travels_App.model.entity;

import java.io.Serializable;

public class TravelDelicacy implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 餐馆名称
     */
    private String restaurantName;

    /**
     * 标题
     */
    private String title;
    /**
     * 推荐菜品
     */
    private String recommendDishes;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecommendDishes() {
        return recommendDishes;
    }

    public void setRecommendDishes(String recommendDishes) {
        this.recommendDishes = recommendDishes;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }
}
