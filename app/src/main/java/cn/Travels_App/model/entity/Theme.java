package cn.Travels_App.model.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Theme 主题
 * @author: soulcoder 灵魂码仔
 * @email: 2579692606@qq.com
 * @date: created by 2021/10/29
 * @copyright: itxfq 项目分享圈
 */

public class Theme implements Serializable {

    private Long id;
    private String themename;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThemename() {
        return themename;
    }

    public void setThemename(String themename) {
        this.themename = themename;
    }

    public Theme(){}
    public Theme(Long id, String themename){
        this.id = id;
        this.themename =  themename;
    }
    public List<Theme> getThemeDatas(){
        List<Theme> themes = new ArrayList<>();
        themes.add(new Theme(1L,"温泉游"));
        themes.add(new Theme(2L,"爬山游"));
        themes.add(new Theme(3L,"自然风光"));
        themes.add(new Theme(4L,"拜佛游"));
        themes.add(new Theme(5L,"玩水嬉戏"));
        themes.add(new Theme(6L,"滑雪游"));
        return themes;
    }

    public Long getThemeidByName(String themename){
        List<Theme> themeDatas = getThemeDatas();
        for (int i = 0; i < themeDatas.size(); i++) {
            Theme theme = themeDatas.get(i);
            if(theme.getThemename().equals(themename)){
                return theme.getId();
            }
        }
        return 0L;
    }
}
