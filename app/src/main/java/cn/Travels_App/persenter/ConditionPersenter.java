package cn.Travels_App.persenter;


import cn.Travels_App.App;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.view.Conditionview;

public class ConditionPersenter extends BasePresenter<Conditionview> {

    Conditionview mConditionview ;

    public ConditionPersenter(App app) {
        super(app);
    }


    public ConditionPersenter(App app, Conditionview mConditionview){
        super(app);
        this.mConditionview = mConditionview;
    }





}
