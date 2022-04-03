package cn.Travels_App.persenter;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.view.TrafficView;

public class TrafficPersenter extends BasePresenter<TrafficView> {
    TrafficView trafficView;
    public TrafficPersenter(App app) {
        super(app);
    }

    public TrafficPersenter(App app, TrafficView view){
        super(app);
        this.trafficView =view;
    }
}
