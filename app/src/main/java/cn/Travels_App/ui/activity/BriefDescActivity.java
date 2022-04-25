package cn.Travels_App.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import cn.Travels_App.ui.fragment.WriteFragment;
import cn.Travels_App.view.BriefDescView;

public class BriefDescActivity extends BaseActivity<BriefDescView, BriefDescPersenter> implements BriefDescView{

    BriefDescView briefDescView;
    BriefDescPersenter briefDescPersenter;
    private WriteFragment writeFragment;

    @BindView(R.id.BriefDesc_name)
    EditText name;
    @BindView(R.id.BriefDesc_address)
    EditText address;
    @BindView(R.id.BriefDesc_briefDesc)
    EditText briefDesc;
    @BindView(R.id.BriefDesc_time)
    EditText time;
    @BindView(R.id.briefdesc_baocun_image)
    ImageButton baocun_image;
    @BindView(R.id.briefdesc_baocun_text)
    TextView baocun_text;

    //需要页面保存的数据
    private String briefdesc_shuju;
    private View view;




    @NonNull
    @Override
    public BriefDescPersenter createPresenter() {
        briefDescView= getMvpView();
        if(briefDescPersenter == null) {
            briefDescPersenter = new BriefDescPersenter(getApp(), briefDescView);
        }
        return briefDescPersenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_briefdesc;
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

    @OnClick(R.id.brief_top_back)
    public void backPage(){
        finish();
    }

    @OnClick({R.id.briefdesc_shanchu_image,R.id.briefdesc_shanchu_text})
    public void shanchu(){
        finish();
    }

    @OnClick({R.id.briefdesc_baocun_image,R.id.briefdesc_baocun_text})
    public void baocun(){
        if(TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(address.getText().toString()) ||
                TextUtils.isEmpty(briefDesc.getText().toString()) || TextUtils.isEmpty(time.getText().toString())){
            Toast.makeText(BriefDescActivity.this, "请将信息填写完整",Toast.LENGTH_SHORT).show();
        }
        else{
            System.out.println("B1");
            Intent intent = new Intent(BriefDescActivity.this,
                    MainActivity.class);
            intent.putExtra("tag", "2");
            /*intent.putExtra("write_cover",0L);*/
            intent.putExtra("write_name",name.getText().toString());
            intent.putExtra("write_address",address.getText().toString());
            intent.putExtra("write_opentime",time.getText().toString());
            intent.putExtra("write_briefDesc",briefDesc.getText().toString());
            System.out.println("B2");
            startActivity(intent);
        }
    }


    private void jiadian() {
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        briefDesc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        System.out.println("B3");
        if(bundle!=null){
            System.out.println("B4");
            name.setText(bundle.getString("name_1"));
            address.setText(bundle.getString("Address_1"));
            briefDesc.setText(bundle.getString("BriefDesc_1"));
            time.setText(bundle.getString("Opentime_1"));
        }
    }
}
