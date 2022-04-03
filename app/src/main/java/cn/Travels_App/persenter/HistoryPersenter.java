package cn.Travels_App.persenter;

import java.util.List;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.view.HistoryView;

public class HistoryPersenter extends BasePresenter<HistoryView> {
    HistoryView historyView;
    public HistoryPersenter(App app) {
        super(app);
    }

    public HistoryPersenter(App app, HistoryView view){
        super(app);
        this.historyView =view;
    }

    public void findHistoryTracels() {
        getAppComponent().getAPIService().findTjTravels()
                .subscribe(new BaseObserver<HttpResult<List<Travels>>>() {
                    @Override
                    public void onSuccess(HttpResult<List<Travels>> resp) {
                        if (resp.isSuccess()) {
                            //查询成功
                            List<Travels> myTravelList = resp.getData();
                            historyView.loadData(myTravelList);
                            historyView.onCompleted();
                        } else {
                            //查询失败
                            historyView.onFailed(resp.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

}
