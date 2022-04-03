package cn.Travels_App.persenter;


import java.util.List;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.model.entity.Hotel;
import cn.Travels_App.model.entity.Order;
import cn.Travels_App.model.entity.Spots;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.view.YdView;

public class YdPerenter extends BasePresenter<YdView> {
    private Travels travels;

    YdView mYyView;

    public YdPerenter(App app) {
        super(app);
    }

    public YdPerenter(App app, YdView view){
        super(app);
        this.mYyView = view;
    }

}
