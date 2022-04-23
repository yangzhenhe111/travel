package cn.Travels_App.ui.activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

import java.util.Map;

import butterknife.BindView;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.common.FlowAutoRadioGroup;
import cn.Travels_App.model.entity.City;
import cn.Travels_App.model.entity.Level;
import cn.Travels_App.model.entity.PriceArea;
import cn.Travels_App.model.entity.Theme;
import cn.Travels_App.persenter.ConditionPersenter;
import cn.Travels_App.view.Conditionview;

public class ConditionActivity extends BaseActivity<Conditionview, ConditionPersenter> implements Conditionview  {


    public Conditionview conditionview;

    @BindView(R.id.spotsSV)
    SearchView mSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public ConditionPersenter createPresenter() {
        conditionview = getMvpView();
        return new ConditionPersenter(getApp(),conditionview);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_condition;
    }

    @Override
    public void initView() {
        mSearchView.setSubmitButtonEnabled(true);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent=new Intent();
                intent.setClass(ConditionActivity.this, MainActivity.class);
                intent.putExtra("queryTag", "1");
                /*intent.putExtra("write_cover",0L);*/
                intent.putExtra("sousuo",s);
                startActivity(intent);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    @Override
    public void initData() {

    }


    @Override
    public void onSuccessData(Map resultMap) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onFailed(String msg) {

    }
}