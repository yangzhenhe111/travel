package cn.Travels_App.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.ui.activity.HistoryActivity;
import cn.Travels_App.ui.activity.LoginActivity;
import cn.Travels_App.persenter.Minepresenter;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseFragment;
import cn.Travels_App.ui.activity.MyTravelsActivity;
import cn.Travels_App.ui.activity.YdRecordActivity;
import cn.Travels_App.utils.CommonUtils;

public class MineFragment extends BaseFragment<MineFview, Minepresenter> {

    @BindView(R.id.my_name)
    TextView myNameTv;

    @BindView(R.id.my_email)
    TextView myEmailTv;

    @BindView(R.id.my_tel)
    TextView myTelTv;

    @BindView(R.id.gerentouxiang)
    ImageView mycove;
    Fragment fragment;

    @OnClick(R.id.his)
    public void his(){
        Intent intent=new Intent();
        intent.setClass(getContext(), HistoryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.mytv)
    public void mytv(){
        Intent intent=new Intent();
        intent.setClass(getContext(), MyTravelsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.logout)
    public void logout(){
        Intent intent=new Intent();
        intent.setClass(getContext(), LoginActivity.class);
        startActivity(intent);
    }



    public static MineFragment newInstance() {

        return new MineFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_frgm;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        UserEntity loginUser = CommonUtils.getLoginUser(getContext());
        myNameTv.setText(loginUser.getUsername());
        myEmailTv.setText(loginUser.getEmail());
        myTelTv.setText(loginUser.getTel());
        /*Glide.with(fragment)
                .load(mycove.getHandler())
                .placeholder(R.drawable.login1)
                .into(mycove);*/
    }

    @Override
    public Minepresenter createPresenter() {
        return new Minepresenter(getApp());
    }

}
