package cn.Travels_App.persenter;


import java.util.List;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.model.entity.Comment;
import cn.Travels_App.model.entity.CommentResp;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
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

  /*  //查询所有的一级评论
    public void queryAllComment(Long id) {
        getAppComponent().getAPIService().queryAllComment(id)
                .subscribe(new BaseObserver<HttpResult<List<CommentResp>>>() {
                    @Override
                    public void onSuccess(HttpResult<List<CommentResp>> resp) {
                        if (resp.isSuccess()) {
                            //查询成功
                            System.out.println("spper1");
                            List<CommentResp> comments =resp.getData();
                            System.out.println("spper2");
                            mTravelsDetailView.CommentData(comments);
                        } else {

                        }
                    }
                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }*/

/*    //查询所有的二级评论
    public void querySecondaryComment(Long id) {
        getAppComponent().getAPIService().queryAllComment(id)
                .subscribe(new BaseObserver<HttpResult<List<Comment>>>() {
                    @Override
                    public void onSuccess(HttpResult<List<Comment>> resp) {
                        if (resp.isSuccess()) {
                            //查询成功
                            System.out.println("spper1");
                            List<Comment> comments =resp.getData();
                            System.out.println("spper2");
                            mTravelsDetailView.CommentData(comments);
                        } else {

                        }
                    }
                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }*/




}


