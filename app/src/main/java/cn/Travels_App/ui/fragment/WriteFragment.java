package cn.Travels_App.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import cn.Travels_App.ui.activity.MainActivity;
import cn.Travels_App.utils.GlideEngine;
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
    String write_name;



    Travels travels = new Travels();

    private Context mContext;
    private String imageurl;


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
    }

    @Override
    public void initData() {


    }

    @OnClick(R.id.write_briefDesc)
    public void briefDesc(){
        if(fetchdata()!=null){
            Intent intent=new Intent(getContext(),BriefDescActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("name_1",travels.getName());
            bundle.putString("Address_1",travels.getAddress());
            bundle.putString("BriefDesc_1",travels.getBriefDesc());
            bundle.putString("Opentime_1",travels.getOpentime());
            intent.putExtras(bundle);
            startActivity(intent);
        }else {
            Intent intent=new Intent(getContext(),BriefDescActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.write_trafficInfo)
    public void trafficInfo(){
        if(fetchdata()!=null){
            Intent intent=new Intent(getContext(),TrafficActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("getTrafficInfo_1",travels.getTrafficInfo());
            intent.putExtras(bundle);
            startActivity(intent);
        }else {
            Intent intent=new Intent(getContext(),TrafficActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.write_hotelInfo)
    public void lodgingInfo(){
        if(fetchdata()!=null){
            Intent intent=new Intent(getContext(),LodgingActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("hotelInfo_1",travels.getHotelInfo());
            intent.putExtras(bundle);
            startActivity(intent);
        }else {
            Intent intent=new Intent(getContext(),LodgingActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.write_resraurantInfo)
    public void resraurantInfo(){
        if(fetchdata()!=null){
            Intent intent=new Intent(getContext(),DelicaciesActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("resraurantInfo_1",travels.getResraurantInfo());
            intent.putExtras(bundle);
            startActivity(intent);
        }else {
            Intent intent=new Intent(getContext(),DelicaciesActivity.class);
            startActivity(intent);
        }
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
                            write_sc_but.setImageBitmap(BitmapFactory.decodeFile(media.getPath()));
                    }
                    System.out.println("w1");
                    break;
            }
        }
    }
    }


    @OnClick({R.id.wtite_baocun_image,R.id.wtite_baocun_text})
    public void write_baocun(){
        if(fetchdata()!=null){
            createPresenter().maketravels(fetchdata());
        }else {
            Toast.makeText(mContext, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.wtite_fabiao_image,R.id.wtite_fabiao_text})
    public void write_fabiao(){
        publication();
    }

    //保存游记
    public Travels fetchdata() {
        Bundle bundle = this.getArguments();
        if(bundle != null){
            String writetag =  bundle.getString("tag");
            travels.setName(bundle.getString("write_name"));
            travels.setAddress(bundle.getString("write_address"));
            travels.setOpentime(bundle.getString("write_opentime"));
            travels.setBriefDesc(bundle.getString("write_briefDesc"));
            travels.setTrafficInfo(bundle.getString("write_trafficInfo"));
            travels.setResraurantInfo(bundle.getString("write_resraurantInfo"));
            travels.setHotelInfo(bundle.getString("write_hotelInfo"));
            travels.setCover(imageurl);
            return travels;
        }else {
            return null;
        }
    }
    //发表游记
    private void publication() {
        Bundle bundle = this.getArguments();
        if(bundle != null){
            String writetag =  bundle.getString("tag");
            travels.setName(bundle.getString("write_name"));
            travels.setAddress(bundle.getString("write_address"));
            travels.setOpentime(bundle.getString("write_opentime"));
            travels.setBriefDesc(bundle.getString("write_briefDesc"));
            travels.setCover(imageurl);
            createPresenter().publication(travels);
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

}
