package cn.Travels_App.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.persenter.BriefDescPersenter;
import cn.Travels_App.persenter.TrafficPersenter;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.view.BriefDescView;
import cn.Travels_App.view.TrafficView;

public class TrafficActivity extends BaseActivity<TrafficView, TrafficPersenter> implements TrafficView{
    TrafficView trafficView;
    TrafficPersenter trafficPersenter;
    CommonUtils commonUtils;
    //信息
    @BindView(R.id.Traffic_suggestion)
    EditText suggestion;
    //建议
    @BindView(R.id.Traffic_Information)
    EditText information;
    @BindView(R.id.traffic_baocun_image)
    ImageButton baocun_image;
    @BindView(R.id.traffic_baocun_text)
    TextView baocun_text;

    //需要页面保存的数据
    private String traffic_shuju;
    private String traffic_suggestion;
    private String traffic_Information;

    @Override
    public int getLayoutId() {
        return R.layout.activity_traffic;
    }

    @Override
    public void initView() {
        xianshi();
        //获取焦点
        jiadian();
    }

    @Override
    public void initData() {

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
    public TrafficPersenter createPresenter() {
        trafficView= getMvpView();
        if(trafficPersenter == null) {
            trafficPersenter = new TrafficPersenter(getApp(), trafficView);
        }
        return trafficPersenter;
    }

    @OnClick(R.id.traffic_top_back)
    void backPage(View view){
        finish();
    }

    @OnClick({R.id.traffic_shanchu_image,R.id.traffic_shanchu_text})
    void shanchu(View view){
        finish();
    }

    @OnClick({R.id.traffic_baocun_image,R.id.traffic_baocun_text})
    void baocun(View view){
        if("".equals(suggestion.getText().toString()) || "".equals(information.getText().toString())){
            Toast.makeText(TrafficActivity.this, "请将信息填写完整",Toast.LENGTH_SHORT).show();
        }
        else{
            StringBuilder tr= new StringBuilder();
            traffic_shuju = tr.append(suggestion.getText().toString()).append("---").append(information.getText().toString()).toString();
            commonUtils.save_travels_traffic(traffic_shuju,TrafficActivity.this);
            Intent intent = new Intent(TrafficActivity.this,
                    MainActivity.class);
            intent.putExtra("tag", "2");
            /*intent.putExtra("write_cover",0L);*/
            intent.putExtra("write_trafficInfo",traffic_shuju);
            startActivity(intent);
        }
    }

    private void jiadian() {
        information.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText _v=(EditText)v;
                if (!hasFocus) {// 失去焦点
                    _v.setHint(_v.getTag().toString());
                } else {
                    String hint=_v.getHint().toString();
                    _v.setTag(hint);
                    _v.setHint("");
                }
            }
        });
        suggestion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText _v=(EditText)v;
                if (!hasFocus) {// 失去焦点
                    _v.setHint(_v.getTag().toString());
                } else {
                    String hint=_v.getHint().toString();
                    _v.setTag(hint);
                    _v.setHint("");
                }
            }
        });
    }

    private void xianshi() {
        String getTrafficInfo=commonUtils.get_travels_traffic(TrafficActivity.this);
        if (getTrafficInfo.equals("")){

        }else{
            String [] temp = getTrafficInfo.split("---");
            suggestion.setText(temp[0]);
            information.setText(temp[1]);
        }
        /*Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        System.out.println("B3");
        System.out.println(traffic_Information);*/
        /*if(bundle.getInt("getTrafficInfo_1")==1){
            System.out.println("B4");
            String getTrafficInfo_1=bundle.getString("getTrafficInfo_1");
            String [] temp = getTrafficInfo_1.split("---");
            suggestion.setText(temp[0]);
            information.setText(temp[1]);
        }*/
    }
}
