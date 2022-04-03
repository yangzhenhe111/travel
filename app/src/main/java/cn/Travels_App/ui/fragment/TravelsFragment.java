package cn.Travels_App.ui.fragment;


import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseFragment;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.persenter.TravelsPersenter;
import cn.Travels_App.ui.activity.ConditionActivity;
import cn.Travels_App.ui.adapter.TravelsAdapter;
import cn.Travels_App.view.Travelsview;

public class TravelsFragment extends BaseFragment<Travelsview, TravelsPersenter> implements Travelsview {



     Travelsview mTravelsview;

     List<Travels> mSpotsList;

     @BindView(R.id.spotslistview)
     GridView mListView;

     //搜索框绑定事件
     @BindView(R.id.spotsSV)
     SearchView mSearchView;

    public static TravelsFragment newInstance() {

        return new TravelsFragment();
    }

    @Override
    public TravelsPersenter createPresenter() {
        mTravelsview = getMvpView();
        return new TravelsPersenter(getApp(), mTravelsview);
    }

    @Override
    public int getLayoutId() {
        return R.layout.travels_frgm;
    }

    @Override
    public void initView() {
        mSearchView.setSubmitButtonEnabled(true);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ConditionActivity.class);
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
        Bundle bundle = this.getArguments();
        if(bundle != null){
           String queryTag =  bundle.getString("queryTag");
            Long cityid = bundle.getLong("cityid");
             Long themeid = bundle.getLong("themeid");
            Long pricefwid = bundle.getLong("pricefwid");
            Long levelid = bundle.getLong("levelid");
            createPresenter().queryTravelsByCondition(cityid,levelid,themeid,pricefwid);
        }else {
            createPresenter().queryAllTravels();
        }
    }

    @Override
    public void loadData(List<Travels> spotsList) {
        this.mSpotsList = spotsList;
        TravelsAdapter travelsAdapter = new TravelsAdapter(getContext(),R.layout.travels_item,
                spotsList);
        mListView.setAdapter(travelsAdapter);
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
