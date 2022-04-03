package cn.Travels_App.persenter;


import java.util.List;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.view.Travelsview;

public class TravelsPersenter extends BasePresenter<Travelsview> {

    Travelsview mSpotsView ;

    public TravelsPersenter(App app) {
        super(app);
    }


    public TravelsPersenter(App app, Travelsview view){
        super(app);
        this.mSpotsView = view;
    }




    //查询所有的景区
    public void queryAllTravels() {
        getAppComponent().getAPIService().findTjTravels()
                .subscribe(new BaseObserver<HttpResult<List<Travels>>>() {

                    @Override
                    public void onSuccess(HttpResult<List<Travels>> resp) {
                        if (resp.isSuccess()) {
                            //查询成功
                            System.out.println("spper1");
                            List<Travels> spotsEntites =resp.getData();
                            System.out.println("spper2");
                            mSpotsView.loadData(spotsEntites);
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

    //根据条件查询景区
    public void queryTravelsByCondition(Long cityid,Long levelid,Long themeid,Long pricefwid) {
        getAppComponent().getAPIService().queryTravelsByCondition(cityid,levelid,themeid,pricefwid)
                .subscribe(new BaseObserver<HttpResult<List<Travels>>>() {

                    @Override
                    public void onSuccess(HttpResult<List<Travels>> resp) {
                        if (resp.isSuccess()) {
                            //查询成功
                            System.out.println("spper3");
                            List<Travels> spotsEntites =resp.getData();
                            System.out.println("spper4");
                            mSpotsView.loadData(spotsEntites);
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

}
