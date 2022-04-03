package cn.Travels_App.persenter;


import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.view.TravelsDetailView;


public class TravelsDetailPersenter extends BasePresenter<TravelsDetailView> {


    TravelsDetailView mTravelsDetailView;

    public TravelsDetailPersenter(App app) {
        super(app);
    }

    public TravelsDetailPersenter(App app, TravelsDetailView view) {
        super(app);
        this.mTravelsDetailView = view;
    }




}


