package cn.Travels_App.persenter;

import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.view.HistoryView;
import cn.Travels_App.view.PersonalView;
import cn.Travels_App.view.Travelsview;

public class PersonalPersenter extends BasePresenter<PersonalView> {
    PersonalView personalView;
    public PersonalPersenter(App app) {
        super(app);
    }
    public PersonalPersenter(App app, PersonalView view){
        super(app);
        this.personalView = view;
    }
}
