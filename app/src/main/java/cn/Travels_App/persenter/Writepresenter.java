package cn.Travels_App.persenter;

import javax.inject.Inject;

import cn.Travels_App.App;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.ui.fragment.Writeview;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.view.RegView;
import okhttp3.MultipartBody;

public class Writepresenter extends BasePresenter<Writeview>{
    Writeview writeview;
    CommonUtils commonUtils;
    public Writepresenter(App app) {
        super(app);
    }

    @Inject
    public Writepresenter(App app, Writeview view){
        super(app);
        this.writeview = view;
    }

    //保存游记
    public void maketravels(Travels travels) {
        getAppComponent().getAPIService().maketravels(travels)
                .subscribe(new BaseObserver<HttpResult<Travels>>() {

                    @Override
                    public void onSuccess(HttpResult<Travels> resp) {
                        if (resp.isSuccess()) {
                            writeview.onCompleted();
                        } else {
                            //保存失败
                            writeview.onFailed(resp.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }
    //上传游记
    public void publication(Travels travels) {
        getAppComponent().getAPIService().pubtravels(travels)
                .subscribe(new BaseObserver<HttpResult<Travels>>() {
                    @Override
                    public void onSuccess(HttpResult<Travels> resp) {
                        if (resp.isSuccess()) {
                            writeview.onCompleted();
                            System.out.println("上传成功");

                        } else {
                            //上传失败
                            writeview.onFailed(resp.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }
}
