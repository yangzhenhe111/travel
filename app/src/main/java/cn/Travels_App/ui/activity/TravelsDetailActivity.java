package cn.Travels_App.ui.activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.persenter.TravelsDetailPersenter;
import cn.Travels_App.view.TravelsDetailView;

public class TravelsDetailActivity extends BaseActivity<TravelsDetailView, TravelsDetailPersenter> implements TravelsDetailView {

    Travels mSpots;

    @BindView(R.id.spotsdetail_nameTv)
    TextView nameTv;
    @BindView(R.id.spotsdetail_opentimeTv)
    TextView opentimeTv;
    @BindView(R.id.spotsdetail_briefDesc)
    TextView briefDesc;
    @BindView(R.id.spotsdetail_addressTv)
    TextView addressTv;
    @BindView(R.id.spotsdetail_fmUrlTv)
    ImageView fmUrlTv;
    @BindView(R.id.faburenxingming)
    TextView fbname;
    @BindView(R.id.fabushijian)
    TextView fbtime;
    @BindView(R.id.spotsdetail_jiaotong)
    TextView jiaotong;
    @BindView(R.id.spotsdetail_zhusu)
    TextView zhusu;
    @BindView(R.id.spotsdetail_meishi)
    TextView meishi;


    TravelsDetailView mTravelsDetailView;

    TravelsDetailPersenter travelsDetailPersenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @OnClick(R.id.detail_top_back)
    void backPage(View view){
        /*Intent intent=new Intent();
        intent.setClass(this, MainActivity.class);
        //启动
        startActivity(intent);*/
        finish();
    }


    @NonNull
    @Override
    public TravelsDetailPersenter createPresenter() {
        mTravelsDetailView = getMvpView();
        if(travelsDetailPersenter == null) {
            travelsDetailPersenter = new TravelsDetailPersenter(getApp(), mTravelsDetailView);
        }
        return travelsDetailPersenter;

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_travels_detail;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mSpots = (Travels) getIntent().getExtras().get("travelEntity");
        fbname.setText(mSpots.getCreatorName());
        String[] str=mSpots.getCreatetime().split("[T]");
        fbtime.setText("发布于 "+str[0]);
        jiaotong.setText(mSpots.getTrafficInfo());
        zhusu.setText(mSpots.getHotelInfo());
        meishi.setText(mSpots.getResraurantInfo());
        nameTv.setText(mSpots.getName());
        addressTv.setText(mSpots.getAddress());
        opentimeTv.setText("开放时间:"+mSpots.getOpentime());
        briefDesc.setText(mSpots.getBriefDesc());

        Glide.with(getApplicationContext())
                .load(mSpots.getCover())
                .into(fmUrlTv);
    }

    private TravelsDetailActivity.LoadWebListener mWebListener;

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
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }


    /**
     * 页面加载的回调
     */
    public interface LoadWebListener {
        void onLoadFinished();
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

    /*@OnClick(R.id.ydSpotsBtn)
    void goYdSpotsForm(View view){

        Intent intent=new Intent(this, YdActivity.class);
        intent.putExtra("spotsEntity", mSpots);
        startActivity(intent);
    }*/






}