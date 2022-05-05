package cn.Travels_App.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.common.Constants;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.persenter.PersonalPersenter;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.utils.GlideEngine;
import cn.Travels_App.utils.UpLoadUtils;
import cn.Travels_App.view.PersonalView;

import static org.greenrobot.eventbus.EventBus.TAG;

public class PersonalActivity extends BaseActivity<PersonalView, PersonalPersenter> implements PersonalView{
    CommonUtils commonUtils;
    private String imageurl;
    private String imgUrl;
    private UserEntity userEntity;
    @BindView(R.id.my_name)
    TextView myNameTv;

    @BindView(R.id.my_email)
    TextView myEmailTv;

    @BindView(R.id.my_tel)
    TextView myTelTv;

    @BindView(R.id.my_sex)
    TextView mysex;

    @BindView(R.id.my_signature)
    TextView mysignature;

    @BindView(R.id.gerentouxiang)
    ImageView mycove;

    String[] sexArry = {"男","保密","女"};

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
        UserEntity userEntity1=new UserEntity();
        UserEntity loginUser = CommonUtils.getLoginUser(PersonalActivity.this);
        System.out.println(loginUser.getId());
        userEntity1.setId(loginUser.getId());
        queryUserEntityByID(userEntity1);

        myNameTv.setText(userEntity.getUsername());
        myEmailTv.setText(userEntity.getEmail());
        myTelTv.setText(userEntity.getTel());
        if(TextUtils.isEmpty(userEntity.getSex())){
            mysex.setText("秘密");
        }else {
            mysex.setText(userEntity.getSex());
        }
        if (TextUtils.isEmpty(userEntity.getSignature())){
            mysignature.setText("什么也没有偶！");
        }else {
            mysignature.setText(userEntity.getSignature());
        }
    }

    @Override
    public void initData() {
        UserEntity userEntity2=commonUtils.getLoginUser(PersonalActivity.this);
        if(userEntity2.getHeadImg()!=""&&userEntity2.getHeadImg()!=null){
            String img=userEntity2.getHeadImg();
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

    //性别
    @OnClick(R.id.my_sex)
    void my_sex(View my_sex){
        int a;
        if(userEntity.getSex().equals("男")){
            a=0;
        } else if(userEntity.getSex().equals("女")){
            a=2;
        }else{
            a=1;
        }
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);// 自定义对话框
        // checkedItem默认的选中 setSingleChoiceItems设置单选按钮组
        builder3.setSingleChoiceItems(sexArry, a, (dialog, which) -> {// which是被选中的位置
            mysex.setText(sexArry[which]);
            dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            if(sexArry[which].equals(userEntity.getSex())){
            }else {
                UserEntity userEntity=commonUtils.getLoginUser(PersonalActivity.this);
                userEntity.setSex(mysex.getText().toString());
                saveUserEntity(userEntity);
            }
        });
        builder3.show();// 让弹出框显示
    }

    //签名
    @OnClick(R.id.my_signature)
    void my_signature(View my_signature){
        BottomSheetDialog dialog = new BottomSheetDialog(PersonalActivity.this);
        View commentView = LayoutInflater.from(PersonalActivity.this).inflate(R.layout.signature_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String signature = commentText.getText().toString().trim();
                UserEntity userEntity=commonUtils.getLoginUser(PersonalActivity.this);
                userEntity.setSignature(signature);
                saveUserEntity(userEntity);
                mysignature.setText(signature);
                Toast.makeText(PersonalActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
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

    //根据ID查询用户信息
    public void queryUserEntityByID(UserEntity userEntity3) {
        System.out.println("P1");
        getApp().getAppComponent().getAPIService().finduserbyid(userEntity3)
                .subscribe(new BaseObserver<HttpResult<UserEntity>>() {
                    @Override
                    public void onSuccess(HttpResult<UserEntity> resp) {
                        if (resp.isSuccess()) {
                            //查询成功
                            UserEntity spotsEntites =resp.getData();
                            userEntity=spotsEntites;
                            System.out.println(userEntity.getUsername());
                        } else {

                        }
                    }
                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

    //编辑用户信息
    public void saveUserEntity(UserEntity userEntity3) {
        getApp().getAppComponent().getAPIService().saveUserEntityByID(userEntity3)
                .subscribe(new BaseObserver<HttpResult<UserEntity>>() {
                    @Override
                    public void onSuccess(HttpResult<UserEntity> resp) {
                        if (resp.isSuccess()) {
                            //查询成功
                            UserEntity spotsEntites =resp.getData();
                            userEntity=spotsEntites;
                        } else {

                        }
                    }
                    @Override
                    public void onFailure(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

    @Override
    public void loadData(UserEntity userEntity) {
    }

}
