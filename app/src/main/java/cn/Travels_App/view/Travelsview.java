package cn.Travels_App.view;


import java.util.List;
import java.util.Map;

import cn.Travels_App.base.BaseView;
import cn.Travels_App.model.entity.Travels;

public interface Travelsview extends BaseView {
    void onSuccessData(Map resultMap);
    void loadData(List<Travels> spotsList);
}
