package cn.Travels_App.base;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface BaseView extends MvpView{

    void showProgress();

    void onCompleted();

    void onError(Throwable e);

    void onFailed(String msg);


}
