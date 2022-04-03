package cn.Travels_App.base;


import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import cn.Travels_App.App;
import cn.Travels_App.di.componet.AppComponent;

import javax.inject.Inject;


public class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {

    private App app;

    private AppComponent mAppComponent;



    @Inject
    public BasePresenter(App app) {
        super();
        this.app = app;
        mAppComponent = app.getAppComponent();


    }



    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public App getApp() {
        return getApp();
    }

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached();
    }
}
