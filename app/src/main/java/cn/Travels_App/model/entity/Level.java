package cn.Travels_App.model.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Level 等级
 * @author: soulcoder 灵魂码仔
 * @email: 2579692606@qq.com
 * @date: created by 2021/10/29
 * @copyright: itxfq 项目分享圈
 */

public class Level implements Serializable {

    private Long id;
    private String levelname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public Level(){}

    public Level(Long id,String levelname){
        this.id = id;
        this.levelname = levelname;
    }

    public static List<Level> getLevelDatas(){
        List<Level> levels = new ArrayList<>();

        Level level1 = new Level(1L,"A");
        Level level2 = new Level(2L,"AA");
        Level level3 = new Level(3L,"AAA");
        Level level4 = new Level(4L,"AAAA");
        Level level5 = new Level(5L,"AAAAA");

        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
        levels.add(level5);
        return levels;
    }

    public static Long getLevelIdByName(String levelname){

        List<Level> levelDatas = getLevelDatas();
        for (int i = 0; i < levelDatas.size(); i++) {
            Level level = levelDatas.get(i);
            if(level.getLevelname().equals(levelname)){
                return level.getId();
            }
        }
        return 0L;
    }
}
