package cn.Travels_App.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.base.BasePresenter;
import cn.Travels_App.persenter.BriefDescPersenter;
import cn.Travels_App.persenter.YdPerenter;
import cn.Travels_App.ui.fragment.WriteFragment;
import cn.Travels_App.view.BriefDescView;
import cn.Travels_App.view.YdView;

public class BriefDescActivity extends BaseActivity<BriefDescView, BriefDescPersenter> implements BriefDescView{

    BriefDescView briefDescView;
    BriefDescPersenter briefDescPersenter;

    @BindView(R.id.BriefDesc_name)
    TextView name;
    @BindView(R.id.BriefDesc_address)
    TextView address;
    @BindView(R.id.BriefDesc_briefDesc)
    TextView briefDesc;
    @BindView(R.id.BriefDesc_time)
    TextView time;
    @BindView(R.id.briefdesc_baocun_image)
    ImageButton baocun_image;
    @BindView(R.id.briefdesc_baocun_text)
    TextView baocun_text;

    //需要页面保存的数据
    private String briefdesc_shuju;




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
    void backPage(View view){
        Intent intent = new Intent(BriefDescActivity.this,MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("date", "fanhui");
        intent.putExtras(bundle);
        BriefDescActivity.this.setResult(RESULT_CANCELED,intent);
        finish();
    }

    @OnClick({R.id.briefdesc_shanchu_image,R.id.briefdesc_shanchu_text})
    void shanchu(View view){
        finish();
    }

    @OnClick({R.id.briefdesc_baocun_image,R.id.briefdesc_baocun_text})
    void baocun(View view){
        if("".equals(name.getText().toString()) || "".equals(address.getText().toString()) ||
                "".equals(briefDesc.getText().toString()) || "".equals(time.getText().toString())){
            Toast.makeText(BriefDescActivity.this, "请将信息填写完整",Toast.LENGTH_SHORT).show();
        }
        else{
            briefdesc_shuju=name.getText().toString()+"|"+address.getText().toString()+"|"+time.getText().toString()+"|"+briefDesc.getText().toString();
            Intent intent = new Intent(BriefDescActivity.this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("date", briefdesc_shuju);
            intent.putExtras(bundle);
            BriefDescActivity.this.setResult(1,intent);
            finish();
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
}
