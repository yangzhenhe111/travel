package cn.Travels_App.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import cn.Travels_App.common.Constants;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.ui.activity.MainActivity;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.utils.GlideEngine;
import cn.Travels_App.utils.UpLoadUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseFragment;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.persenter.Writepresenter;
import cn.Travels_App.ui.activity.BriefDescActivity;
import cn.Travels_App.ui.activity.DelicaciesActivity;
import cn.Travels_App.ui.activity.LodgingActivity;
import cn.Travels_App.ui.activity.TrafficActivity;
import okhttp3.MultipartBody;
import retrofit2.http.Url;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.luck.picture.lib.config.PictureConfig.REQUEST_CAMERA;
import static org.greenrobot.eventbus.EventBus.TAG;

public class WriteFragment extends BaseFragment<Writeview,Writepresenter> implements Writeview{
    @BindView(R.id.write_briefDesc)
    TextView briefDesc;

    @BindView(R.id.write_trafficInfo)
    TextView trafficInfo;

    @BindView(R.id.write_hotelInfo)
    TextView hotelInfo;

    @BindView(R.id.write_resraurantInfo)
    TextView resraurantInfo;

    @BindView(R.id.write_sc_but)
    ImageView write_sc_but;


    @BindView(R.id.ceshi)
    TextView ceshi;
    @BindView(R.id.ceshi1)
    TextView ceshi1;
    @BindView(R.id.ceshi2)
    TextView ceshi2;
    @BindView(R.id.ceshi3)
    TextView ceshi3;


    Writeview writeview;
    CommonUtils commonUtils;
    String write_name;




    private Context mContext;
    private String imageurl;
    private String imgUrl;
    private int a;
    private int b;
    private int c;
    private int d;


    public static WriteFragment newInstance() {
        return new WriteFragment();
    }

    @Override
    public Writepresenter createPresenter() {
        writeview = getMvpView();
        return new Writepresenter(getApp(),writeview);
    }

    @Override
    public int getLayoutId() {
        return R.layout.write_fragment;
    }

    @Override
    public void initView() {
        this.mContext=getActivity();
        requestWritePermission();

    }

    @Override
    public void initData() {
        if(commonUtils.get_travels_imageurl(getContext())!=null||commonUtils.get_travels_imageurl(getContext())!=""){
            Glide.with(mContext).load(commonUtils.get_travels_imageurl(getContext())).into(write_sc_but);
        }
    }

    @OnClick(R.id.write_briefDesc)
    public void briefDesc(){
        /*if(fetchdata()!=null){*/
            Intent intent=new Intent(getContext(),BriefDescActivity.class);
            /*Bundle bundle=new Bundle();
            bundle.putString("name_1",travels.getName());
            bundle.putString("Address_1",travels.getAddress());
            bundle.putString("BriefDesc_1",travels.getBriefDesc());
            bundle.putString("Opentime_1",travels.getOpentime());
            System.out.println(travels.getName());
            intent.putExtras(bundle);*/
            startActivity(intent);
        /*}else {
            Intent intent=new Intent(getContext(),BriefDescActivity.class);
            startActivity(intent);
        }*/
    }

    @OnClick(R.id.write_trafficInfo)
    public void trafficInfo(){
        /*if(fetchdata()!=null){*/
            Intent intent=new Intent(getContext(),TrafficActivity.class);
            /*Bundle bundle=new Bundle();
            bundle.putInt("getTrafficInfo_1",0);
            intent.putExtras(bundle);*/
            startActivity(intent);
        /*}else {
            Intent intent=new Intent(getContext(),TrafficActivity.class);
            startActivity(intent);
        }*/
    }

    @OnClick(R.id.write_hotelInfo)
    public void lodgingInfo(){
        /*if(fetchdata()!=null){*/
            Intent intent=new Intent(getContext(),LodgingActivity.class);
            /*Bundle bundle=new Bundle();
            bundle.putString("hotelInfo_1",travels.getHotelInfo());
            intent.putExtras(bundle);*/
            startActivity(intent);
        /*}else {
            Intent intent=new Intent(getContext(),LodgingActivity.class);
            startActivity(intent);
        }*/
    }

    @OnClick(R.id.write_resraurantInfo)
    public void resraurantInfo(){
        /*if(fetchdata()!=null){*/
            Intent intent=new Intent(getContext(),DelicaciesActivity.class);
            /*Bundle bundle=new Bundle();
            bundle.putString("resraurantInfo_1",travels.getResraurantInfo());
            intent.putExtras(bundle);*/
            startActivity(intent);
        /*}else {
            Intent intent=new Intent(getContext(),DelicaciesActivity.class);
            startActivity(intent);
        }*/
    }

    @OnClick(R.id.write_sc_but)
    public void Uploadcover(){
        PictureSelector
                .create(WriteFragment.this)
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
                            commonUtils.save_travels_imageurl(imageurl,getContext());
                            imgUrl = UpLoadUtils.uploadImg(getApp().getApplicationContext(),media.getPath(), Constants.BASE_URL+"front/travels/uploadCover");
                            Log.e("imgUrl",imgUrl);
                            commonUtils.save_travels_imgUrl(imgUrl,getContext());
                            /*write_sc_but.setImageBitmap(BitmapFactory.decodeFile(media.getPath()));*/
                            String imageurl1=commonUtils.get_travels_imageurl(getContext());
                            if(imageurl1!=null&&imageurl1!=""){
                                Glide.with(mContext).load(imageurl1).into(write_sc_but);
                            }
                    }
                    System.out.println("w1");
                    break;
            }
        }
    }
    }


    @OnClick({R.id.wtite_baocun_image,R.id.wtite_baocun_text})
    public void write_baocun(){
        /*commonUtils.save_travels_briefdesc("","","","",getContext());
        commonUtils.save_travels_traffic("",getContext());
        commonUtils.save_travels_hotelInfo("",getContext());
        commonUtils.save_travels_resraurantInfo("",getContext());
        commonUtils.save_travels_imageurl("",getContext());
        Glide.with(mContext).load(R.drawable.shangchuanfengmian).into(write_sc_but);*/

        Travels travels=new Travels();
        travels.setBriefDesc(commonUtils.get_travels_briefdesc(getContext()).getBriefDesc());
        travels.setName(commonUtils.get_travels_briefdesc(getContext()).getName());
        travels.setAddress(commonUtils.get_travels_briefdesc(getContext()).getAddress());
        travels.setOpentime(commonUtils.get_travels_briefdesc(getContext()).getOpentime());
        travels.setTrafficInfo(commonUtils.get_travels_traffic(getContext()));
        travels.setResraurantInfo(commonUtils.get_travels_resraurantInfo(getContext()));
        travels.setHotelInfo(commonUtils.get_travels_hotelInfo(getContext()));
        /*travels.setCover(imgUrl);*/
        travels.setCreator(gerenxinxi().getId());
        travels.setCreatorCover(gerenxinxi().getHeadImg());
        fetchdata(travels);
    }

    @OnClick({R.id.wtite_fabiao_image,R.id.wtite_fabiao_text})
    public void write_fabiao(){
        /*System.out.println("上传成功");
        commonUtils.save_travels_briefdesc("","","","",getContext());
        commonUtils.save_travels_traffic("",getContext());
        commonUtils.save_travels_hotelInfo("",getContext());
        commonUtils.save_travels_resraurantInfo("",getContext());
        commonUtils.save_travels_imageurl("",getContext());
        Glide.with(mContext).load(R.drawable.shangchuanfengmian).into(write_sc_but);*/

        Travels travels=new Travels();
        travels.setBriefDesc(commonUtils.get_travels_briefdesc(getContext()).getBriefDesc());
        travels.setName(commonUtils.get_travels_briefdesc(getContext()).getName());
        travels.setAddress(commonUtils.get_travels_briefdesc(getContext()).getAddress());
        travels.setOpentime(commonUtils.get_travels_briefdesc(getContext()).getOpentime());
        travels.setTrafficInfo(commonUtils.get_travels_traffic(getContext()));
        travels.setResraurantInfo(commonUtils.get_travels_resraurantInfo(getContext()));
        travels.setHotelInfo(commonUtils.get_travels_hotelInfo(getContext()));
        String imgurl=commonUtils.get_travels_imgUrl(getContext());
        travels.setCover(imgurl);
        travels.setCreator(gerenxinxi().getId());
        travels.setCreatorCover(gerenxinxi().getHeadImg());
        publication(travels);
    }

    //保存游记
    public void fetchdata(Travels travels) {
        /*Bundle bundle = this.getArguments();*/
        String imageurl1=commonUtils.get_travels_imageurl(getContext());
        if(imageurl1 != null&&imageurl1!=""){
            /*String writetag =  bundle.getString("tag");*/
            /*Travels travels=new Travels();
            travels.setName(bundle.getString("write_name"));
            travels.setAddress(bundle.getString("write_address"));
            travels.setOpentime(bundle.getString("write_opentime"));
            travels.setBriefDesc(bundle.getString("write_briefDesc"));
            travels.setTrafficInfo(bundle.getString("write_trafficInfo"));
            travels.setResraurantInfo(bundle.getString("write_resraurantInfo"));
            travels.setHotelInfo(bundle.getString("write_hotelInfo"));
            travels.setCover(imgUrl);
            travels.setCreator(gerenxinxi().getId());
            travels.setCreatorCover(gerenxinxi().getHeadImg());*/
            createPresenter().maketravels(travels);
        }else {
            Toast.makeText(mContext, "请将信息填写完整", Toast.LENGTH_SHORT).show();
        }
    }
    //发表游记
    private void publication(Travels travels) {
        /*Bundle bundle = this.getArguments();*/
        String imageurl1=commonUtils.get_travels_imageurl(getContext());
        if(imageurl1 != null&&imageurl1!=""){
           /* String writetag =  bundle.getString("tag");
            Travels travels=new Travels();
            travels.setName(bundle.getString("write_name"));
            travels.setAddress(bundle.getString("write_address"));
            travels.setOpentime(bundle.getString("write_opentime"));
            travels.setBriefDesc(bundle.getString("write_briefDesc"));
            travels.setTrafficInfo(bundle.getString("write_trafficInfo"));
            travels.setResraurantInfo(bundle.getString("write_resraurantInfo"));
            travels.setHotelInfo(bundle.getString("write_hotelInfo"));
            travels.setCover(imgUrl);
            travels.setCreator(gerenxinxi().getId());
            travels.setCreatorCover(gerenxinxi().getHeadImg());*/
            System.out.println("上传成功");
            createPresenter().publication(travels);
            commonUtils.save_travels_briefdesc("","","","",getContext());
            commonUtils.save_travels_traffic("",getContext());
            commonUtils.save_travels_hotelInfo("",getContext());
            commonUtils.save_travels_resraurantInfo("",getContext());
            commonUtils.save_travels_imageurl("",getContext());
            commonUtils.save_travels_imgUrl("",getContext());
            Glide.with(mContext).load(R.drawable.shangchuanfengmian).into(write_sc_but);
        }else {
            Toast.makeText(mContext, "请将信息填写完整", Toast.LENGTH_SHORT).show();
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

    private void requestWritePermission(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    public UserEntity gerenxinxi(){
        UserEntity userEntity=commonUtils.getLoginUser(getContext());
        return userEntity;
    }

}
