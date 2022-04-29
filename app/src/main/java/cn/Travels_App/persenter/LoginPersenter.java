package cn.Travels_App.persenter;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import cn.Travels_App.App;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.view.LoginView;
import cn.Travels_App.base.BasePresenter;

import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;



public class LoginPersenter extends BasePresenter<LoginView> {


    LoginView mLoginView ;

    public LoginPersenter(App app) {
        super(app);
    }

    public LoginPersenter(App app,LoginView view){
        super(app);
        this.mLoginView = view;
    }


    public void login(String username,String password){
        System.out.println("02");
        getAppComponent().getAPIService().login(username,password)
                .subscribe(new BaseObserver<HttpResult<UserEntity>>() {
                    @Override
                    public void onSuccess(HttpResult<UserEntity> resp) {
                        System.out.println("03");
                        if (resp.isSuccess()) {
                            System.out.println("0");
                            UserEntity userEntity=resp.getData();
                            System.out.println("1");
                            mLoginView.onSuccessData(userEntity);
                        } else {
                            //登录失败
                            System.out.println("11");
                            mLoginView.onFailed(resp.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }
}
