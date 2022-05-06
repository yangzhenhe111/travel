package cn.Travels_App.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
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
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.persenter.ConditionPersenter;
import cn.Travels_App.persenter.ConditionShowPersenter;
import cn.Travels_App.persenter.TravelsPersenter;
import cn.Travels_App.ui.adapter.TravelsAdapter;
import cn.Travels_App.ui.fragment.TravelsFragment;
import cn.Travels_App.view.ConditionShowView;
import cn.Travels_App.view.Conditionview;

public class ConditionShowActivity extends BaseActivity<ConditionShowView, ConditionShowPersenter> implements ConditionShowView {
    @BindView(R.id.spotslistview)
    GridView mListView;
    @BindView(R.id.sousuo_travels_list)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ConditionShow_sousuo)
    EditText conditionShow_sousuo;

    ConditionShowView conditionShowView;
    List<Travels> mSpotsList;
    int pageNum = 1;
    int pageSize = 10;

    TravelsAdapter  adapter;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_conditionshow;
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
        adapter = new TravelsAdapter(ConditionShowActivity.this,R.layout.travels_item,
                mSpotsList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(ConditionShowActivity.this,TravelsDetailActivity.class);
                Long travelsId = mSpotsList.get(position).getId();
                intent.putExtra("travelsId", travelsId);
                startActivity(intent);
            }
        });
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(ConditionShowActivity.this).setSpinnerStyle(SpinnerStyle.Scale));
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

    @Override
    public void initData() {
        Intent intent = getIntent();
        String sousuo = intent.getStringExtra("sousuo");
        System.out.println(sousuo);
        if(sousuo != null&&sousuo!=""){
            createPresenter().queryTravelsByCondition(sousuo,pageNum++);
            String condition = conditionShow_sousuo.getText().toString().trim();
            if (Objects.isNull(condition) || condition.equals("")) {
                conditionShow_sousuo.setText(sousuo);
            }
            if(mSpotsList!=null&&mSpotsList.size()!=0){
            }else {
                Toast.makeText(ConditionShowActivity.this, "抱歉！您搜索内容不存在，已为您展现其他精彩内容。", Toast.LENGTH_SHORT).show();
                mSpotsList.clear();
                createPresenter().queryTravelsByCondition(null,pageNum++);
            }
        }else {
            createPresenter().queryTravelsByCondition(null,pageNum++);
        }

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

    @NonNull
    @Override
    public ConditionShowPersenter createPresenter() {
        conditionShowView = getMvpView();
        return new ConditionShowPersenter(getApp(), conditionShowView);
    }

    @Override
    public void loadData(List<Travels> spotsList) {
        this.mSpotsList.addAll(spotsList);
    }

    @OnClick(R.id.back)
    public void fanhui(){
        Intent intent=new Intent();
        intent.setClass(ConditionShowActivity.this, MainActivity.class);
        intent.putExtra("queryTag","1");
        startActivity(intent);
    }
}
