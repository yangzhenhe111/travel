package cn.Travels_App.persenter;

import java.util.List;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.model.dto.PageBean;
import cn.Travels_App.model.dto.PageRequest;
import cn.Travels_App.model.dto.TravelCollectionDTO;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.view.Collectionview;
import cn.Travels_App.view.HistoryView;

public class CollectionPersenter extends BasePresenter<Collectionview> {
    Collectionview collectionview;
    public CollectionPersenter(App app) {
        super(app);
    }
    public CollectionPersenter(App app, Collectionview view){
        super(app);
        this.collectionview =view;
    }

    public void findCollectionTracels(PageRequest<TravelCollectionDTO> request) {
        getAppComponent().getAPIService().findCollectionTracels(request)
                .subscribe(new BaseObserver<HttpResult<PageBean<TravelCollectionDTO>>>() {
                    @Override
                    public void onSuccess(HttpResult<PageBean<TravelCollectionDTO>> resp) {
                        if (resp.isSuccess()) {
                            //查询成功
                            List<TravelCollectionDTO> myTravelList = resp.getData().getData();
                            collectionview.loadData(myTravelList);
                            collectionview.onCompleted();
                        } else {
                            //查询失败
                            collectionview.onFailed(resp.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

}
