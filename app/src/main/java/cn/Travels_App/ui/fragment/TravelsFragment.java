package cn.Travels_App.ui.fragment;


import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseFragment;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.persenter.TravelsPersenter;
import cn.Travels_App.ui.activity.CollectionActivity;
import cn.Travels_App.ui.activity.ConditionActivity;
import cn.Travels_App.ui.adapter.CollectionAdapter;
import cn.Travels_App.ui.adapter.TravelsAdapter;
import cn.Travels_App.view.Travelsview;

public class TravelsFragment extends BaseFragment<Travelsview, TravelsPersenter> implements Travelsview {



     Travelsview mTravelsview;

     List<Travels> mSpotsList;

     @BindView(R.id.spotslistview)
     GridView mListView;

    @BindView(R.id.sousuo_travels_list)
    SmartRefreshLayout smartRefreshLayout;

    int pageNum = 1;

    int pageSize = 10;

    TravelsAdapter  adapter;

    String sousuo;


    private Handler mainHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    adapter.notifyDataSetChanged();//刷新界面
                    //结束刷新动画
                    smartRefreshLayout.finishRefresh();
                    break;
            }
        }
    };

    private class LoadMoreTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            initData();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            //刷新界面
            adapter.notifyDataSetChanged();
            //结束加载更多动画
            smartRefreshLayout.finishLoadMore();
        }
    }

     //搜索框绑定事件
    /* @BindView(R.id.spotsSV)
     SearchView mSearchView;*/

    public static TravelsFragment newInstance() {
        return new TravelsFragment();
    }

    public TravelsFragment() {
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
        if (this.mSpotsList == null) {
            this.mSpotsList = new ArrayList<>();
        }else {
            this.mSpotsList.clear();
        }
        initData();
        System.out.println(mSpotsList);
        adapter = new TravelsAdapter(getContext(),R.layout.travels_item,
                mSpotsList);
        mListView.setAdapter(adapter);
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                new Thread(){
                    @Override
                    public void run() {
                        //通过网络访问服务器端
                        pageNum = 1;
                        mSpotsList.clear();
                        initData();

                        //使用Handler发送Message
                        Message message = new Message();
                        message.what = 100;
                        mainHandler.sendMessage(message);
                    }
                }.start();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                new LoadMoreTask().execute();
            }
        });
    }

    @Override
    public void initData() {
        Bundle bundle = this.getArguments();
        if(bundle != null){
           String queryTag =  bundle.getString("queryTag");
            sousuo = bundle.getString("sousuo");

            createPresenter().queryTravelsByCondition(sousuo,pageNum++);
            if(mSpotsList!=null&&mSpotsList.size()!=0){
            }else {
                Toast.makeText(getContext(), "抱歉！您搜索内容不存在，已为您展现其他精彩内容。", Toast.LENGTH_SHORT).show();
                mSpotsList.clear();
                createPresenter().queryTravelsByCondition(null,pageNum++);
            }
        }else {
            createPresenter().queryTravelsByCondition(null,pageNum++);
        }
    }

    @Override
    public void loadData(List<Travels> spotsList) {
        System.out.println("TF1");
        this.mSpotsList.addAll(spotsList);

    }

    @OnClick(R.id.travels_sousuo)
    public void sousuo(){
        Intent intent=new Intent();
        intent.setClass(getContext(), ConditionActivity.class);
        startActivity(intent);
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
