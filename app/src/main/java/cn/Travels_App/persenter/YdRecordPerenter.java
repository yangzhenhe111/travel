package cn.Travels_App.persenter;


import java.util.List;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.model.entity.Order;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.view.YdRecordView;

public class YdRecordPerenter extends BasePresenter<YdRecordView> {

    YdRecordView mYyRecordView;

    public YdRecordPerenter(App app) {
        super(app);
    }

    public YdRecordPerenter(App app, YdRecordView mYyRecordView){
        super(app);
        this.mYyRecordView = mYyRecordView;
    }

    //查询我的预约
    public void findMyYdOrder(Long id) {

        getAppComponent().getAPIService().findMyYdOrder(id)
                .subscribe(new BaseObserver<HttpResult<String>>() {

                    @Override
                    public void onSuccess(HttpResult<String> resp) {
                        if (resp.isSuccess()) {
                            //查询成功
                            List<Order> ydMyOrderList = CommonUtils.transeToYdList(resp.getData());
                            mYyRecordView.loadData(ydMyOrderList);

                            mYyRecordView.onCompleted();

                        } else {
                            //查询失败
                            mYyRecordView.onFailed(resp.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }


    public void payMyOrder(Long id) {
        getAppComponent().getAPIService().payMyOrder(id)
                .subscribe(new BaseObserver<HttpResult<String>>() {

                    @Override
                    public void onSuccess(HttpResult<String> resp) {
                        if (resp.isSuccess()) {
                            //查询成功
                            mYyRecordView.onDoPayOrderResponse();

                        } else {
                            //查询失败
                            mYyRecordView.onFailed(resp.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }
}
