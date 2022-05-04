package cn.Travels_App.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.common.Constants;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.persenter.Minepresenter;
import cn.Travels_App.persenter.PersonalPersenter;
import cn.Travels_App.ui.fragment.WriteFragment;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.utils.GlideEngine;
import cn.Travels_App.utils.UpLoadUtils;
import cn.Travels_App.view.PersonalView;

import static org.greenrobot.eventbus.EventBus.TAG;

public class PersonalActivity extends BaseActivity<PersonalView, PersonalPersenter> implements PersonalView{
    CommonUtils commonUtils;
    private Context mContext;
    private String imageurl;
    private String imgUrl;
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
        UserEntity loginUser = CommonUtils.getLoginUser(PersonalActivity.this);
        myNameTv.setText(loginUser.getUsername());
        myEmailTv.setText(loginUser.getEmail());
        myTelTv.setText(loginUser.getTel());
    }

    @Override
    public void initData() {
        UserEntity userEntity=commonUtils.getLoginUser(PersonalActivity.this);
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
        /*Intent intent=new Intent(PersonalActivity.this,MainActivity.class);
        intent.putExtra("gotoFragmentTag", "3");
        startActivity(intent);*/
        finish();
    }

    @OnClick(R.id.gerentouxiang)
    public void Uploadcover(){
        PictureSelector
                .create(PersonalActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .isWeChatStyle(true)//是否设置微信样式
                .loadImageEngine(GlideEngine.createGlideEngine())
                .withAspectRatio(1, 1)
                .circleDimmedLayer(false)
                .showCropFrame(true)
                .showCropGrid(true)
                .rotateEnabled(false)
                .maxSelectNum(1)// 最大图片选择数量 int
                .forResult(PictureConfig.CHOOSE_REQUEST);
                /*.enableCrop(true)// 是否裁剪 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    if (data != null) {
                        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                        for (LocalMedia media : selectList) {
                            Log.i(TAG, "压缩::" + media.getCompressPath());
                            Log.i(TAG, "原图::" + media.getPath());
                            Log.i(TAG, "裁剪::" + media.getCutPath());
                            Log.i(TAG, "Android Q 特有Path::" + media.getAndroidQToPath());
                            imageurl=media.getPath();
                            imgUrl = UpLoadUtils.uploadImg(getApp().getApplicationContext(),media.getPath(), Constants.BASE_URL+"front/user/uploadHeadImg");
                            Log.e("imgUrl",imgUrl);
                            Glide.with(this)
                                    .load(imageurl)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mycove);
                            UserEntity userEntity=commonUtils.getLoginUser(PersonalActivity.this);
                            userEntity.setHeadImg(imageurl);
                            commonUtils.storeLoginUser(userEntity,PersonalActivity.this);

                        }
                        break;
                    }
            }
        }
    }

}
