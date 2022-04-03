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
import cn.Travels_App.persenter.MyTravelsPersenter;
import cn.Travels_App.ui.adapter.TjTravelsAdapter;
import cn.Travels_App.utils.ToastUtils;
import cn.Travels_App.view.MyTravelsView;

public class MyTravelsActivity extends BaseActivity<MyTravelsView, MyTravelsPersenter> implements MyTravelsView{
    MyTravelsView myTravelsView;
    List<Travels> mytravels;

    @BindView(R.id.travelstjListViewId)
    ListView listView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_mytravels;
    }

    @Override
    public void initView() {
        initData();
        TjTravelsAdapter adapter=new TjTravelsAdapter(MyTravelsActivity.this,R.layout.tjlist_item,mytravels);
        listView.setAdapter(adapter);
        setListViewHeight(listView);
    }

    @Override
    public void initData() {
        createPresenter().findMyTracels();

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

    @Override
    public void loadData(List<Travels> myTravelList) {
        this.mytravels=myTravelList;
    }
}
