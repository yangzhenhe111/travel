package cn.Travels_App.persenter;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.view.BriefDescView;
import cn.Travels_App.view.LodgingView;

public class LodgingPersenter extends BasePresenter<LodgingView> {
    LodgingView lodgingView;
    public LodgingPersenter(App app) {
        super(app);
    }

    public LodgingPersenter(App app, LodgingView view){
        super(app);
        this.lodgingView =view;
    }

}
