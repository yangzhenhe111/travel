package cn.Travels_App.ui.activity;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
import cn.Travels_App.model.dto.TravelCollectionDTO;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.persenter.CollectionPersenter;
import cn.Travels_App.ui.adapter.CollectionAdapter;
import cn.Travels_App.ui.adapter.HistoryAdapter;
import cn.Travels_App.ui.adapter.TjTravelsAdapter;
import cn.Travels_App.utils.ToastUtils;
import cn.Travels_App.view.Collectionview;

public class CollectionActivity extends BaseActivity<Collectionview, CollectionPersenter> implements Collectionview{
    List<TravelCollectionDTO> collectiontravels = new ArrayList<>();
    Collectionview collectionview;

    @BindView(R.id.collectionlistView)
    ListView listView;
    @BindView(R.id.collection_listView_layout)
    SmartRefreshLayout smartRefreshLayout;

    int pageNum = 1;

    int pageSize = 10;

    CollectionAdapter adapter;


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
       return R.layout.activity_collection;
    }

    @Override
    public void initView() {
        initData();
        if(collectiontravels.isEmpty()){
            Toast.makeText(CollectionActivity.this, "还没有添加过收藏偶！收藏到了喜欢的游记再来吧！", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            adapter=new CollectionAdapter(CollectionActivity.this,R.layout.history_item,collectiontravels);
            listView.setAdapter(adapter);
//            setListViewHeight(listView);
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
                            collectiontravels.clear();
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
    }

    @Override
    public void initData() {
        PageRequest<TravelCollectionDTO> query = new PageRequest<>();
        query.setPageNum(pageNum++);
        query.setPageSize(pageSize);
        query.setData(new TravelCollectionDTO());
        createPresenter().findCollectionTracels(query);
    }

    @Override
    public void loadData(List<TravelCollectionDTO> collectTravelList) {
        this.collectiontravels.addAll(collectTravelList);
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
        ToastUtils.showToast(CollectionActivity.this,msg);
    }

    @NonNull
    @Override
    public CollectionPersenter createPresenter() {
        collectionview=getMvpView();
        return new CollectionPersenter(getApp(),collectionview);
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
