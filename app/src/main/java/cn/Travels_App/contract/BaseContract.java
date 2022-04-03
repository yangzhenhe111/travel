package cn.Travels_App.contract;

public interface BaseContract {

    interface BasePresenter<T extends BaseContract.BaseView> {

        void attachView(T view);

        void detachView();
    }


    interface BaseView {
        //显示请求成功
        void onSuccess();
        //失败重试
        void onFailure();
    }
}