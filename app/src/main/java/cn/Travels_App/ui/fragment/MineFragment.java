package cn.Travels_App.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.ui.activity.CollectionActivity;
import cn.Travels_App.ui.activity.HistoryActivity;
import cn.Travels_App.ui.activity.LoginActivity;
import cn.Travels_App.persenter.Minepresenter;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseFragment;
import cn.Travels_App.ui.activity.MyTravelsActivity;
import cn.Travels_App.ui.activity.PersonalActivity;
import cn.Travels_App.utils.CommonUtils;

public class MineFragment extends BaseFragment<MineFview, Minepresenter> {

    /*@BindView(R.id.geren)
    View geren;
    @BindView(R.id.touxiang)
    View touxiang;
*/

    /*@OnClick(R.id.geren)
    public void geren(){
        Intent intent=new Intent();
        intent.setClass(getContext(), PersonalActivity.class);
        startActivity(intent);
    }*/
    CommonUtils commonUtils;
    @BindView(R.id.touxiang)
    ImageView mycove;
    @BindView(R.id.my_name)
    TextView myname;
    @BindView(R.id.my_jianjie)
    TextView myjianjie;

    @OnClick({R.id.touxiang,R.id.my_name,R.id.my_jianjie})
    public void touxiang(){
        Intent intent=new Intent();
        intent.setClass(getContext(), PersonalActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.my_his)
    public void his(){
        Intent intent=new Intent();
        intent.setClass(getContext(), HistoryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.my_tr)
    public void mytv(){
        Intent intent=new Intent();
        intent.setClass(getContext(), MyTravelsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.my_shoucang)
    public void myshoucang(){
        Intent intent=new Intent();
        intent.setClass(getContext(), CollectionActivity.class);
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
        UserEntity userEntity=commonUtils.getLoginUser(getContext());
        myname.setText(userEntity.getUsername());
        System.out.println(userEntity.getHeadImg());
        if(userEntity.getHeadImg()!=""&&userEntity.getHeadImg()!=null){
            String img=userEntity.getHeadImg();
            Glide.with(this)
                    .load(img)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(mycove);
        }else {
            Glide.with(this)
                    .load(R.drawable.login1)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(mycove);
        }
    }

    @Override
    public Minepresenter createPresenter() {
        return new Minepresenter(getApp());
    }

}
