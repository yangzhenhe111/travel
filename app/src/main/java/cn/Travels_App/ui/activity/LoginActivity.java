package cn.Travels_App.ui.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Map;

import butterknife.BindView;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.persenter.LoginPersenter;
import cn.Travels_App.R;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.utils.ToastUtils;
import cn.Travels_App.view.LoginView;
import cn.Travels_App.base.BaseActivity;


/**
 *   描述:登录Activity
 */
public class LoginActivity extends BaseActivity<LoginView,LoginPersenter> implements LoginView {
    //注册组件
    @BindView(R.id.regTv)
     TextView regTv;

    //用户名称
    @BindView(R.id.log_name)
     EditText loginNameEt ;
    //密码
    @BindView(R.id.log_pwd)
     EditText loginPwdEt ;

     LoginView mView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局文件

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //登录监听事件
        regTv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this, RegActivity.class);
                //启动
                startActivity(intent);
            }
        });
        //焦点监听事件
        jiaodian();
    }

    @NonNull
    @Override
    public LoginPersenter createPresenter() {
        mView = getMvpView();
        System.out.println("01");
        return new LoginPersenter(getApp(),mView);
    }

    @Override
    public int getLayoutId() {

            return R.layout.activity_login;
    }



    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    //登录系统
    public void loginSys(View view){
        String username = loginNameEt.getText().toString().trim();
        String pwd = loginPwdEt.getText().toString().trim();
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("password",pwd);

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)){
            Toast.makeText(this,"用户名和密码不能为空", Toast.LENGTH_SHORT).show();
        }else {
            System.out.println("00");
            createPresenter().login(username,pwd);
        }

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {
        System.out.println("3");
        Intent intent=new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        System.out.println("4");
        startActivity(intent);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onFailed(String msg) {
        ToastUtils.showToast(getBaseContext(),msg);
    }

    @Override
    public void onSuccessData(UserEntity userEntity) {
        CommonUtils.storeLoginUser(userEntity, LoginActivity.this);
        System.out.println("2");
        onCompleted();
    }

    public void jiaodian(){
        //焦点监听事件
        loginNameEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        loginPwdEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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