package cn.Travels_App.persenter;


import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.view.DoctorDetailView;


public class DoctorDetailPersenter extends BasePresenter<DoctorDetailView> {


    DoctorDetailView mDoctorDetailView;

    public DoctorDetailPersenter(App app) {
        super(app);
    }

    public DoctorDetailPersenter(App app, DoctorDetailView view) {
        super(app);
        this.mDoctorDetailView = view;
    }





}


