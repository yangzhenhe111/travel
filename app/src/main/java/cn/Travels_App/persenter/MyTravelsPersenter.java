package cn.Travels_App.persenter;

import java.util.List;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.view.DelicaciesView;
import cn.Travels_App.view.MyTravelsView;

public class MyTravelsPersenter extends BasePresenter<MyTravelsView> {
    MyTravelsView myTravelsView;
    public MyTravelsPersenter(App app) {
        super(app);
    }
    public MyTravelsPersenter(App app, MyTravelsView view){
        super(app);
        this.myTravelsView =view;
    }

    public void findMyTracels() {
        getAppComponent().getAPIService().findTjTravels()
                .subscribe(new BaseObserver<HttpResult<List<Travels>>>() {
                    @Override
                    public void onSuccess(HttpResult<List<Travels>> resp) {
                        if (resp.isSuccess()) {
                            //查询成功
                            List<Travels> myTravelList = resp.getData();
                            myTravelsView.loadData(myTravelList);
                            myTravelsView.onCompleted();
                        } else {
                            //查询失败
                            myTravelsView.onFailed(resp.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }
}
