package cn.Travels_App.persenter;


import com.google.gson.Gson;

import java.util.List;

import cn.Travels_App.App;
import cn.Travels_App.model.entity.Spots;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.view.HomeView;
import cn.Travels_App.base.BasePresenter;

public class HomePerenter extends BasePresenter<HomeView> {

    HomeView mHomeView;

    public HomePerenter(App app) {
        super(app);
    }

    public HomePerenter(App app, HomeView view){
        super(app);
        this.mHomeView = view;
    }

    public void findTjTravels(){
        System.out.println("hp1");
        getAppComponent().getAPIService().findTjTravels()
                .subscribe(new BaseObserver<HttpResult<List<Travels>>>() {
                    @Override
                    public void onSuccess(HttpResult<List<Travels>> resp) {
                        System.out.println("hp2");
                        if (resp.isSuccess()) {
                            //查询成功
                            System.out.println("hp3");
                            List<Travels> tjSpotsList = resp.getData();
                            System.out.println("hp4");
                            mHomeView.loadData(tjSpotsList);
                            System.out.println("hp5");
                            mHomeView.onCompleted();
                        } else {
                            //查询失败
                            System.out.println("hp6");
                            mHomeView.onFailed(resp.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }


}
