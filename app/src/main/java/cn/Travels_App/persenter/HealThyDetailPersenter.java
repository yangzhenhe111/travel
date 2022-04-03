package cn.Travels_App.persenter;


import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.view.HealthyDetailView;


public class HealThyDetailPersenter extends BasePresenter<HealthyDetailView> {


    HealthyDetailView mSpDetailView;

    public HealThyDetailPersenter(App app) {
        super(app);
    }

    public HealThyDetailPersenter(App app, HealthyDetailView view) {
        super(app);
        this.mSpDetailView = view;
    }




}


