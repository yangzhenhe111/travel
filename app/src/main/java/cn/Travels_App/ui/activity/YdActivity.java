package cn.Travels_App.ui.activity;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.model.entity.CasesEntity;
import cn.Travels_App.model.entity.Hotel;
import cn.Travels_App.model.entity.Order;
import cn.Travels_App.model.entity.OrderDetail;
import cn.Travels_App.model.entity.Spots;
import cn.Travels_App.persenter.YdPerenter;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.view.YdView;

/**
 * 预定景区信息填报
 */
public class YdActivity extends BaseActivity<YdView, YdPerenter> implements YdView {


    private Spots mSpots;

    YdView mYyView;

    YdPerenter mYyPerenter;

    @BindView(R.id.yd_spotsnameTv)
    public TextView yd_spotsnameTv;
    @BindView(R.id.yd_addressTv)
    public TextView yd_addressTv;
    @BindView(R.id.yd_opentimeTv)
    public TextView yd_opentimeTv;
    @BindView(R.id.yd_telTv)
    public TextView yd_telTv;
    @BindView(R.id.yd_traveltimeEt)
    public EditText yd_traveltimeEt;
    @BindView(R.id.yd_adultsnumEt)
    public EditText yd_adultsnumEt;
    @BindView(R.id.yd_childnumEt)
    public EditText yd_childnumEt;
    @BindView(R.id.yd_price1Tv)
    public TextView yd_price1Tv;
    @BindView(R.id.yd_price2Tv)
    public TextView yd_price2Tv;
    @BindView(R.id.yd_totalpriceTv)
    public TextView yd_totalpriceTv;

    @BindView(R.id.yd_travelnameTv)
    public EditText yd_travelnameTv;
    @BindView(R.id.yd_cardTv)
    public EditText yd_cardTv;



    //需要提交到后台数据
    public Hotel submitHotelObj ;
    //酒店房间数
    public Long hotelnum = 1L;
    //成人数量
    public Long adultsnum = 1L;
    //儿童数量
    public Long childnum = 0L;

    public Double totalprice=0.0;




    @Override
    public int getLayoutId() {
        return R.layout.activity_yd;
    }



    @Override
    public void initView() {

    }

    public void calcTotalPrice(){
        adultsnum = Long.valueOf(yd_adultsnumEt.getText().toString());
        childnum = Long.valueOf(yd_childnumEt.getText().toString());
        totalprice = adultsnum * mSpots.getPrice() + childnum * mSpots.getPrice() + hotelnum * submitHotelObj.getHotelprice();
        yd_totalpriceTv.setText("合计:"+String.valueOf(totalprice));
    }

    @Override
    public void initData() {
        //获取景区信息
        mSpots = (Spots) getIntent().getExtras().get("spotsEntity");
        //设置景区的基本信息
        yd_spotsnameTv.setText(mSpots.getName());
        yd_addressTv.setText(mSpots.getAddress());
        yd_opentimeTv.setText(mSpots.getOpentime());
        yd_telTv.setText(mSpots.getTel());
        yd_price1Tv.setText(" x "+mSpots.getPrice() + "元");
        yd_price2Tv.setText(" x 0.5 x  "+mSpots.getPrice() + "元");

        yd_adultsnumEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    //得到焦点
                }else{
                    //失去焦点--计算总价
                    calcTotalPrice();
                }
            }
        });

        yd_childnumEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    //得到焦点
                }else{
                    //失去焦点--计算总价
                    calcTotalPrice();
                }
            }
        });

    }

    @Override
    public void onSuccessData(Map resultMap) {

    }

    @Override
    public void loadData(List<CasesEntity> casesEntityList) {

    }

    @Override
    public void showProgress() {

    }

    @OnClick(R.id.ydSaveBtn)
    public void saveYdOrder(View view){
        Order saveOrder = new Order();
        saveOrder.setSpotsid(mSpots.getSpotsid());
        saveOrder.setSpotsname(mSpots.getName());
        saveOrder.setSpotstel(mSpots.getTel());
        saveOrder.setTraveltime(yd_traveltimeEt.getText().toString());
        saveOrder.setAdultsnum(adultsnum);
        saveOrder.setChildnum(childnum);
        saveOrder.setHotelprice(submitHotelObj.getHotelprice());
        saveOrder.setHotelid(submitHotelObj.getId());
        saveOrder.setHotelnum(hotelnum);
        saveOrder.setTotalprice(totalprice);
        saveOrder.setCreatorid(Long.parseLong(CommonUtils.getLoginUser(this).getId()+""));
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail  = new OrderDetail();
        orderDetail.setCardNum(yd_cardTv.getText().toString());
        orderDetail.setTravelerName(yd_travelnameTv.getText().toString());
        orderDetails.add(orderDetail);
        saveOrder.setTravels(orderDetails);
    }

    @Override
    public void onCompleted() {
        Toast.makeText(this,"下单成功,请到个人中心查看!", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this,"下单失败"+msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.yy_top_back)
    void backPage(View view){
        Intent intent=new Intent();
        intent.setClass(this, MainActivity.class);
        //启动
        startActivity(intent);
    }

    @NonNull
    @Override
    public YdPerenter createPresenter() {
        mYyView = getMvpView();
        if(mYyPerenter == null) {
            mYyPerenter = new YdPerenter(getApp(), mYyView);
        }
        return mYyPerenter;
    }
}