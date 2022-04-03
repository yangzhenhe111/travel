package cn.Travels_App.persenter;


import javax.inject.Inject;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.view.RegView;


public class RegPersenter extends BasePresenter<RegView> {


    RegView mRegView ;


    public RegPersenter(App app) {
        super(app);
    }

    @Inject
    public RegPersenter(App app, RegView view){
        super(app);
        this.mRegView = view;
    }



    public void reg(UserEntity userEntity){
        System.out.println("2");
        getAppComponent().getAPIService().reg(userEntity)
                .subscribe(new BaseObserver<HttpResult<UserEntity>>() {
                    @Override
                    public void onSuccess(HttpResult<UserEntity> resp) {
                        System.out.println("3");
                        if (resp.isSuccess()) {
                            mRegView.onCompleted();
                            System.out.println("4");
                        } else {
                            //登录失败
                            mRegView.onFailed(resp.getMsg());
                            System.out.println("5");
                        }
                    }
                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });

    }
}
