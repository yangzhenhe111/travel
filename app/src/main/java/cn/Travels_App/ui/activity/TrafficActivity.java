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
import cn.Travels_App.persenter.BriefDescPersenter;
import cn.Travels_App.persenter.TrafficPersenter;
import cn.Travels_App.view.BriefDescView;
import cn.Travels_App.view.TrafficView;

public class TrafficActivity extends BaseActivity<TrafficView, TrafficPersenter> implements TrafficView{
    TrafficView trafficView;
    TrafficPersenter trafficPersenter;
    //信息
    @BindView(R.id.Traffic_suggestion)
    TextView suggestion;
    //建议
    @BindView(R.id.Traffic_Information)
    TextView information;
    @BindView(R.id.traffic_baocun_image)
    ImageButton baocun_image;
    @BindView(R.id.traffic_baocun_text)
    TextView baocun_text;

    //需要页面保存的数据
    private String traffic_shuju;

    @Override
    public int getLayoutId() {
        return R.layout.activity_traffic;
    }

    @Override
    public void initView() {
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
            traffic_shuju=suggestion.getText().toString()+"|"+information.getText().toString();
            Intent intent = new Intent(TrafficActivity.this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("date", traffic_shuju);
            intent.putExtras(bundle);
            TrafficActivity.this.setResult(2,intent);
            finish();
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
}
