package cn.Travels_App.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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
import cn.Travels_App.persenter.DelicaciesPersenter;
import cn.Travels_App.persenter.LodgingPersenter;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.view.DelicaciesView;



public class DelicaciesActivity extends BaseActivity<DelicaciesView, DelicaciesPersenter> implements DelicaciesView{
    DelicaciesView delicaciesView;
    DelicaciesPersenter delicaciesPersenter;
    CommonUtils commonUtils;

    @BindView(R.id.delicacies_restaurant)
    EditText restaurant;
    @BindView(R.id.delicacies_dishes)
    EditText dishes;
    @BindView(R.id.delicacies_baocun_image)
    ImageButton baocun_image;
    @BindView(R.id.delicacies_baocun_text)
    TextView baocun_text;

    private String delicacies_shuju;

    @Override
    public int getLayoutId() {
        return R.layout.activity_delicacies;
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
    public DelicaciesPersenter createPresenter() {
        delicaciesView= getMvpView();
        if(delicaciesPersenter == null) {
            delicaciesPersenter = new DelicaciesPersenter(getApp(), delicaciesView);
        }
        return delicaciesPersenter;
    }

    @OnClick(R.id.delicacies_top_back)
    void backPage(View view){
        finish();
    }

    @OnClick({R.id.delicacies_shanchu_image,R.id.delicacies_shanchu_text})
    void shanchu(View view){
        finish();
    }

    @OnClick({R.id.delicacies_baocun_image,R.id.delicacies_baocun_text})
    void baocun(View view){
        if("".equals(restaurant.getText().toString()) || "".equals(dishes.getText().toString())){
            Toast.makeText(DelicaciesActivity.this, "请将信息填写完整",Toast.LENGTH_SHORT).show();
        }
        else{
            StringBuilder de= new StringBuilder();
            delicacies_shuju = de.append(restaurant.getText().toString()).append("---").append(dishes.getText().toString()).toString();
            commonUtils.save_travels_resraurantInfo(delicacies_shuju,DelicaciesActivity.this);
            Intent intent = new Intent(DelicaciesActivity.this,
                    MainActivity.class);
            intent.putExtra("tag", "2");
            intent.putExtra("write_resraurantInfo",delicacies_shuju);
            startActivity(intent);
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

    private void xianshi() {
        String getresraurantInfo=commonUtils.get_travels_resraurantInfo(DelicaciesActivity.this);
        if (getresraurantInfo.equals("")){

        }else{
            String [] temp = getresraurantInfo.split("---");
            restaurant.setText(temp[0]);
            dishes.setText(temp[1]);
        }
        /*Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            String getTrafficInfo_1=bundle.getString("resraurantInfo_1");
            String [] temp = getTrafficInfo_1.split("---");
            restaurant.setText(temp[0]);
            dishes.setText(temp[1]);
        }*/
    }
}
