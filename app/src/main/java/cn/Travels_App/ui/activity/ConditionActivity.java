package cn.Travels_App.ui.activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
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
                intent.setClass(ConditionActivity.this, ConditionShowActivity.class);
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

    @OnClick(R.id.back)
    void backPage(View view){
        Intent intent=new Intent();
        intent.setClass(ConditionActivity.this, MainActivity.class);
        intent.putExtra("queryTag","1");
        startActivity(intent);
    }

}