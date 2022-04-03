package cn.Travels_App.ui.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import java.util.List;

import butterknife.BindView;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.persenter.HistoryPersenter;
import cn.Travels_App.persenter.MyTravelsPersenter;
import cn.Travels_App.ui.adapter.TjTravelsAdapter;
import cn.Travels_App.utils.ToastUtils;
import cn.Travels_App.view.HistoryView;
import cn.Travels_App.view.MyTravelsView;

public class HistoryActivity extends BaseActivity<HistoryView, HistoryPersenter> implements HistoryView{
    List<Travels> histravels;
    HistoryView historyView;

    @BindView(R.id.historyListViewId)
    ListView listView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public void initView() {
        initData();
        TjTravelsAdapter adapter=new TjTravelsAdapter(HistoryActivity.this,R.layout.tjlist_item,histravels);
        listView.setAdapter(adapter);
        setListViewHeight(listView);
    }

    @Override
    public void initData() {
        createPresenter().findHistoryTracels();
    }

    @Override
    public void loadData(List<Travels> myTravelList) {
        this.histravels=myTravelList;
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
}
