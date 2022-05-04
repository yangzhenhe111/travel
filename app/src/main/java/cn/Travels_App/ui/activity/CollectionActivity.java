package cn.Travels_App.ui.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

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
import cn.Travels_App.ui.adapter.TjTravelsAdapter;
import cn.Travels_App.utils.ToastUtils;
import cn.Travels_App.view.Collectionview;

public class CollectionActivity extends BaseActivity<Collectionview, CollectionPersenter> implements Collectionview{
    List<TravelCollectionDTO> collectiontravels;
    Collectionview collectionview;

    @BindView(R.id.collectionlistView)
    ListView listView;

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
            CollectionAdapter adapter=new CollectionAdapter(CollectionActivity.this,R.layout.history_item,collectiontravels);
            listView.setAdapter(adapter);
            setListViewHeight(listView);
        }
    }

    @Override
    public void initData() {
        PageRequest<TravelCollectionDTO> query = new PageRequest<>();
        query.setPageNum(1);
        query.setPageSize(10);
        query.setData(new TravelCollectionDTO());
        createPresenter().findCollectionTracels(query);
    }

    @Override
    public void loadData(List<TravelCollectionDTO> collectTravelList) {
        this.collectiontravels=collectTravelList;
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
