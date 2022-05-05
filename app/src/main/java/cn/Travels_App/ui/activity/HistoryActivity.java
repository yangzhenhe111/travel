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
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
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
import cn.Travels_App.model.dto.TravelCollectionDTO;
import cn.Travels_App.model.dto.TravelsHistoryDTO;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.persenter.HistoryPersenter;
import cn.Travels_App.persenter.MyTravelsPersenter;
import cn.Travels_App.ui.adapter.HistoryAdapter;
import cn.Travels_App.ui.adapter.TjTravelsAdapter;
import cn.Travels_App.utils.ToastUtils;
import cn.Travels_App.view.HistoryView;
import cn.Travels_App.view.MyTravelsView;

public class HistoryActivity extends BaseActivity<HistoryView, HistoryPersenter> implements HistoryView{
    List<TravelsHistoryDTO> histravels = new ArrayList<>();
    HistoryView historyView;

    @BindView(R.id.historyListViewId)
    ListView listView;

    @BindView(R.id.history_listView_layout)
    SmartRefreshLayout smartRefreshLayout;

    int pageNum = 1;

    int pageSize = 10;

    HistoryAdapter adapter;


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

    private class LoadMoreTask extends AsyncTask{
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
        return R.layout.activity_history;
    }

    @Override
    public void initView() {
        initData();
        adapter = new HistoryAdapter(HistoryActivity.this, R.layout.history_item, histravels);
        listView.setAdapter(adapter);
//        setListViewHeight(listView);
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        //上拉
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {//下拉刷新时调用
                new Thread(){
                    @Override
                    public void run() {
                        //通过网络访问服务器端
                        pageNum = 1;
                        histravels.clear();
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
        PageRequest<TravelsHistoryDTO> query = new PageRequest<>();
        query.setPageNum(pageNum++);
        query.setPageSize(pageSize);
        query.setData(new TravelsHistoryDTO());
        createPresenter().findHistoryTracels(query);
    }

    @Override
    public void loadData(List<TravelsHistoryDTO> myTravelList) {
        this.histravels.addAll(myTravelList);
//        this.histravels=myTravelList;
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
        ToastUtils.showToast(HistoryActivity.this,msg);
    }

    @NonNull
    @Override
    public HistoryPersenter createPresenter() {
        historyView=getMvpView();
        return new HistoryPersenter(getApp(),historyView);
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

}
