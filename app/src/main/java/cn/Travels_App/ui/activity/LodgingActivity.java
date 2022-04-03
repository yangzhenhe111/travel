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
import cn.Travels_App.persenter.LodgingPersenter;
import cn.Travels_App.view.LodgingView;

public class LodgingActivity extends BaseActivity<LodgingView, LodgingPersenter> implements LodgingView{
    LodgingView lodgingView;
    LodgingPersenter lodgingPersenter;

    @BindView(R.id.lodging_restaurant)
    TextView restaurant;
    @BindView(R.id.lodging_dishes)
    TextView dishes;
    @BindView(R.id.lodging_baocun_image)
    ImageButton baocun_image;
    @BindView(R.id.lodging_baocun_text)
    TextView baocun_text;

    private String lodging_shuju;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lodging;
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
    public LodgingPersenter createPresenter() {
        lodgingView= getMvpView();
        if(lodgingPersenter == null) {
            lodgingPersenter = new LodgingPersenter(getApp(), lodgingView);
        }
        return lodgingPersenter;
    }

    @OnClick(R.id.lodging_top_back)
    void backPage(View view){
        finish();
    }

    @OnClick({R.id.lodging_shanchu_image,R.id.lodging_shanchu_text})
    void shanchu(View view){
        finish();
    }

    @OnClick({R.id.lodging_baocun_image,R.id.lodging_baocun_text})
    void baocun(View view){
        if("".equals(restaurant.getText().toString()) || "".equals(dishes.getText().toString())){
            Toast.makeText(LodgingActivity.this, "请将信息填写完整",Toast.LENGTH_SHORT).show();
        }
        else{
            lodging_shuju=restaurant.getText().toString()+"|"+dishes.getText().toString();
            Intent intent = new Intent(LodgingActivity.this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("date", lodging_shuju);
            intent.putExtras(bundle);
            LodgingActivity.this.setResult(3,intent);
            finish();
        }
    }


    private void jiadian() {
        restaurant.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        dishes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
