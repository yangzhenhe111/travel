package cn.Travels_App.persenter;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.view.DelicaciesView;
import cn.Travels_App.view.LodgingView;

public class DelicaciesPersenter extends BasePresenter<DelicaciesView> {
    DelicaciesView delicaciesView;
    public DelicaciesPersenter(App app) {
        super(app);
    }

    public DelicaciesPersenter(App app, DelicaciesView view){
        super(app);
        this.delicaciesView =view;
    }
}
