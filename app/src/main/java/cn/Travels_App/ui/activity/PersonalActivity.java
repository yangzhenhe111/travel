package cn.Travels_App.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.persenter.Minepresenter;
import cn.Travels_App.persenter.PersonalPersenter;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.view.PersonalView;

public class PersonalActivity extends BaseActivity<PersonalView, PersonalPersenter> implements PersonalView{
    @BindView(R.id.my_name)
    TextView myNameTv;

    @BindView(R.id.my_email)
    TextView myEmailTv;

    @BindView(R.id.my_tel)
    TextView myTelTv;

    @BindView(R.id.gerentouxiang)
    ImageView mycove;

    @NonNull
    @Override
    public PersonalPersenter createPresenter() {
        return new PersonalPersenter(getApp());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    public void initView() {
        System.out.println("P1");
        UserEntity loginUser = CommonUtils.getLoginUser(PersonalActivity.this);
        myNameTv.setText(loginUser.getUsername());
        myEmailTv.setText(loginUser.getEmail());
        myTelTv.setText(loginUser.getTel());
    }

    @Override
    public void initData() {
        /*if(TextUtils.isEmpty(loginUser.getHeadImg())){*/
            Glide.with(this)
                .load(R.drawable.login1)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(mycove);

        /*}else {
            Glide.with(fragment)
                .load(loginUser.getHeadImg())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(mycove);
        }*/
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
    @OnClick(R.id.detail_top_back)
    void backPage(View view){
        finish();
    }

}
