package cn.Travels_App.ui.activity;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.model.dto.PageRequest;
import cn.Travels_App.model.dto.QueryTravelsDTO;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.persenter.MyTravelsPersenter;
import cn.Travels_App.ui.adapter.CollectionAdapter;
import cn.Travels_App.ui.adapter.MyTracelsAdapter;
import cn.Travels_App.ui.adapter.TjTravelsAdapter;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.utils.ToastUtils;
import cn.Travels_App.view.MyTravelsView;

public class MyTravelsActivity extends BaseActivity<MyTravelsView, MyTravelsPersenter> implements MyTravelsView{
    MyTravelsView myTravelsView;
    List<Travels> mytravels = new ArrayList<>();
    CommonUtils commonUtils;

    @BindView(R.id.travelstjListViewId)
    ListView listView;
    @BindView(R.id.mytravels_listView_layout)
    SmartRefreshLayout smartRefreshLayout;

    int pageNum = 1;

    int pageSize = 10;

    MyTracelsAdapter  adapter;


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

    @Override
    public int getLayoutId() {
        return R.layout.activity_mytravels;
    }

    @Override
    public void initView() {
        initData();
        System.out.println("MT2");
        adapter=new MyTracelsAdapter(MyTravelsActivity.this,R.layout.history_item,mytravels);
        System.out.println("MT3");
        listView.setAdapter(adapter);
        setListViewHeight(listView);
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        //上拉
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                new Thread(){
                    @Override
                    public void run() {
                        //通过网络访问服务器端
                        pageNum = 1;
                        mytravels.clear();
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
        UserEntity userEntity= commonUtils.getLoginUser(MyTravelsActivity.this);

        PageRequest<QueryTravelsDTO> request = new PageRequest<>();
        request.setPageNum(pageNum++);
        request.setPageSize(10);
        QueryTravelsDTO queryTravelsDTO = new QueryTravelsDTO();
        System.out.println(userEntity.getId());
        queryTravelsDTO.setCreator(userEntity.getId());
        request.setData(queryTravelsDTO);
        createPresenter().queryTravelsByID(request);
        System.out.println(userEntity.getId());
    }

    @NonNull
    @Override
    public MyTravelsPersenter createPresenter() {
        myTravelsView=getMvpView();
        return new MyTravelsPersenter(getApp(),myTravelsView);
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
        ToastUtils.showToast(MyTravelsActivity.this,msg);
    }

    public void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1))
                + 15;
        listView.setLayoutParams(params);
    }
    @OnClick(R.id.detail_top_back)
    void backPage(View view){
        finish();
    }

    @Override
    public void loadData(List<Travels> myTravelList) {
        System.out.println("MT1");
        this.mytravels.addAll(myTravelList);
    }
}
