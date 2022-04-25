package cn.Travels_App.persenter;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.view.BriefDescView;

public class BriefDescPersenter extends BasePresenter<BriefDescView> {
    BriefDescView Briefview;
    public BriefDescPersenter(App app) {
        super(app);
    }

    public BriefDescPersenter(App app, BriefDescView view){
        super(app);
        this.Briefview =view;
    }
}
