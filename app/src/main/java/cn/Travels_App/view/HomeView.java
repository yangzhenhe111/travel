package cn.Travels_App.view;


import java.util.List;

import cn.Travels_App.base.BaseView;
import cn.Travels_App.model.entity.Spots;
import cn.Travels_App.model.entity.Travels;

public interface HomeView extends BaseView {
    void loadData(List<Travels> tjSpotsList);
}
