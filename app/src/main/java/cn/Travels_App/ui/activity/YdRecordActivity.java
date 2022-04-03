package cn.Travels_App.ui.activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.model.entity.Order;
import cn.Travels_App.persenter.YdRecordPerenter;
import cn.Travels_App.ui.adapter.YdRecordAdapter;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.utils.ToastUtils;
import cn.Travels_App.view.YdRecordView;

public class YdRecordActivity extends BaseActivity<YdRecordView, YdRecordPerenter> implements YdRecordView {

    YdRecordView mYdRecordView;

    YdRecordPerenter mYdRecordPerenter;

    @BindView(R.id.myYyRecordLv)
    ListView mListView;

    List<Order> mYdOrderList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yd_record;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

        createPresenter().findMyYdOrder(Long.valueOf(CommonUtils.getLoginUser(this).getId()+""));
    }

    @Override
    public void onSuccessData(Map resultMap) {

    }

    @OnClick(R.id.myyy_top_back)
    void backPage(View view){
        Intent intent=new Intent();
        intent.setClass(this, MainActivity.class);
        //启动
        startActivity(intent);
    }


    @Override
    public void loadData(List<Order> ydOrderList) {
        this.mYdOrderList = ydOrderList;
        YdRecordAdapter yyRecordAdapter = new YdRecordAdapter(this,R.layout.yd_myitem,
                ydOrderList,mYdRecordPerenter);

        mListView.setAdapter(yyRecordAdapter);
        setListViewAutoHeight(mListView);
    }

    @Override
    public void onDoPayOrderResponse() {
        ToastUtils.showToast(getApplicationContext(),"支付成功");
        createPresenter().findMyYdOrder(Long.valueOf(CommonUtils.getLoginUser(this).getId()+""));
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

       // initData();

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onFailed(String msg) {

    }

    @NonNull
    @Override
    public YdRecordPerenter createPresenter() {
        mYdRecordView = getMvpView();
        if(mYdRecordPerenter == null) {
            mYdRecordPerenter = new YdRecordPerenter(getApp(), mYdRecordView);
        }
        return mYdRecordPerenter;
    }

    /** 计算高度 */
    private void setListViewAutoHeight(ListView lv) {
        if (lv == null || lv.getCount() <= 0) return;
        int totalHeight = 0;
        for (int i = 0; i < lv.getCount(); i++) {
            View view = lv.getAdapter().getView(i, null, lv);
            view.measure(0, 0);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = totalHeight + lv.getDividerHeight() * (lv.getCount() - 1) + lv.getPaddingTop() + lv.getPaddingBottom();
        lv.setLayoutParams(params);
    }

}