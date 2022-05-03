package cn.Travels_App.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.di.componet.DaggerRegComponent;
import cn.Travels_App.di.module.RegModule;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.persenter.RegPersenter;
import cn.Travels_App.utils.ItFxqConstants;
import cn.Travels_App.utils.MD5Utils;
import cn.Travels_App.view.RegView;

import static com.blankj.utilcode.util.RegexUtils.isEmail;
import static com.blankj.utilcode.util.RegexUtils.isMobileSimple;


/**
 *   描述:RegActivity 注册Activity
 */
public class RegActivity extends BaseActivity<RegView, RegPersenter> implements RegView {
    //点击去登录组件
    @BindView(R.id.goLoginTv)
    TextView goLoginTv ;
    //用户名编辑框
    @BindView(R.id.username)
     EditText usernameEt ;
    //密码编辑框
    @BindView(R.id.pwd)
     EditText pwdEt ;
    //邮件编辑框
    @BindView(R.id.email)
     EditText emailEt ;
    //电话号码编辑框
    @BindView(R.id.tel)
     EditText telEt ;


    //注册成功
     RegHandler mRegHandler;



    private boolean sex;

    RegView mRegView;

    @Inject
    RegPersenter mRegPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置注册布局
        DaggerRegComponent.builder().
                regModule(new RegModule(getApp(), getMvpView()))
                .build().injectReg(this);
        mRegHandler=new RegHandler();


        goLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        //焦点监听
        jiaodian();
    }

    @NonNull
    @Override
    public RegPersenter createPresenter() {
        mRegView = getMvpView();
        return new RegPersenter(getApp(),mRegView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_reg;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    //注册保存用户信息
    @OnClick(R.id.regBtn)
    void saveUser(View view){
        //获取存入的内容-用户名
        String username = usernameEt.getText().toString().trim();
        //获取存入的内容-密码
        String pwd = pwdEt.getText().toString().trim();
        //获取存入的内容-电话
        String tel = telEt.getText().toString().trim();

        //获取存入的内容-邮件
        String email = emailEt.getText().toString().trim();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(tel) || TextUtils.isEmpty(email)){
            Toast.makeText(this,"个人信息不能为空", Toast.LENGTH_SHORT).show();
        }else if (isEmail(email)!=true){
            Toast.makeText(this,"请输入正确的邮箱", Toast.LENGTH_SHORT).show();
        } else if (isMobileSimple(tel)!=true){
            Toast.makeText(this,"请输入正确的电话号码", Toast.LENGTH_SHORT).show();
        } else{
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(MD5Utils.getMD5Code(pwd));
            userEntity.setTel(tel);
            userEntity.setEmail(email);
            /*DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(Calendar.getInstance().getTime());*/
            /*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date=new java.util.Date();
            String str=sdf.format(date);
            System.out.println(str);
            userEntity.setCreatetime(date);*/
            System.out.println("1");
            mRegPersenter.reg(userEntity);
        }
    }
    //邮箱判断
   /* public static boolean isEmail(String email) {
        String str = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }*/
    //手机号码判断
    /*public static boolean checkPhoneNum(String phoneNumber) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5-9])|(166)|(19[8,9])|)\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }*/

    @Override
    public void onSuccessData(UserEntity userEntity) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {
        Toast.makeText(this,"注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this,"注册失败"+msg, Toast.LENGTH_SHORT).show();
    }

    class RegHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case ItFxqConstants.OK_STATUS:
                    //解析获取的JSON数据
                    Gson gson = new Gson();
                    //通过反射得到type对象
                    Type listType = new TypeToken<Map>() {
                    }.getType();
                    Map resultMap = gson.fromJson((String)msg.obj, listType);
                    Boolean isSuccess =  (Boolean)resultMap.get("isSuccess");
                    String resultMsg =  (String)resultMap.get("msg");
                    //注册成功
                    if(isSuccess){
                        AlertDialog.Builder dlog = new AlertDialog.Builder(RegActivity.this);
                        dlog.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dlg, int arg1) {
                                Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                        dlog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dlg, int arg1) {
                                dlg.dismiss();;
                            }
                        });
                        dlog.setMessage("保存成功,返回登录。");
                        dlog.setTitle("温馨提示");
                        dlog.show();
                    }else{
                        Toast.makeText(RegActivity.this,resultMsg, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case ItFxqConstants.ERROR_STATUS:
                    Toast.makeText(RegActivity.this,"注册失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


    //焦点监听事件
    public void jiaodian(){
        //焦点监听事件
        usernameEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        pwdEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        emailEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        telEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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