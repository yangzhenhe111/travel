package cn.Travels_App.ui.fragment;

import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;

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

public class WriteFragment extends BaseFragment<Writeview,Writepresenter> implements Writeview{
    @BindView(R.id.write_briefDesc)
    TextView briefDesc;

    @BindView(R.id.write_trafficInfo)
    TextView trafficInfo;

    @BindView(R.id.write_hotelInfo)
    TextView hotelInfo;

    @BindView(R.id.write_resraurantInfo)
    TextView resraurantInfo;


    @BindView(R.id.ceshi)
    TextView ceshi;
    @BindView(R.id.ceshi1)
    TextView ceshi1;
    @BindView(R.id.ceshi2)
    TextView ceshi2;
    @BindView(R.id.ceshi3)
    TextView ceshi3;

    Writeview writeview;



    Travels travels = new Travels();
    private int a;
    private int b;
    private int c;
    private int d;
    private Context mContext;


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
        startActivityForResult(new Intent(getContext(),BriefDescActivity.class),1);
    }

    @OnClick(R.id.write_trafficInfo)
    public void trafficInfo(){
        startActivityForResult(new Intent(getContext(), TrafficActivity.class),2);
    }

    @OnClick(R.id.write_hotelInfo)
    public void lodgingInfo(){
        startActivityForResult(new Intent(getContext(), LodgingActivity.class),3);
    }

    @OnClick(R.id.write_resraurantInfo)
    public void resraurantInfo(){
        startActivityForResult(new Intent(getContext(), DelicaciesActivity.class),4);
    }


    @OnClick({R.id.wtite_baocun_image,R.id.wtite_baocun_text})
    public void write_baocun(){

    }

    @OnClick({R.id.wtite_fabiao_image,R.id.wtite_fabiao_text})
    void write_fabiao(View view){
        if(a==0&&b==0&&c==0&&d==0){
            File file=new File("balabala");
            MediaType mediaType = MediaType.parse("image/png");
            RequestBody requestBody = RequestBody.create(mediaType,file);
            MultipartBody multipartBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getName(),requestBody)
                    .build();
            createPresenter().maketravels(multipartBody,travels);

        }else {
            Toast.makeText(mContext, "请将信息填写完整", Toast.LENGTH_SHORT).show();
        }

    }


    //获取分项数据
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case(RESULT_CANCELED):
                {
                    Bundle bundle = data.getExtras();
                    System.out.print(bundle.get("date")); }break;
            case (1):
            {
                Bundle bundle = data.getExtras();
                String briefDesc = bundle.getString("date");
                String[] str=briefDesc.split("[|]");
                travels.setName(str[0]);
                travels.setAddress(str[1]);
                travels.setOpentime(str[2]);
                travels.setBriefDesc(str[3]);
                a=0;
                ceshi.setText(briefDesc);
                System.out.print(briefDesc);
            }break;
            case (2):
            {
                Bundle bundle = data.getExtras();
                String traffic = bundle.getString("date");
                travels.setTrafficInfo(traffic);
                b=0;
                ceshi1.setText(traffic);
                System.out.print(traffic);
            }break;
            case (3):
            {
                Bundle bundle = data.getExtras();
                String lodging = bundle.getString("date");
                travels.setHotelInfo(lodging);
                c=0;
                ceshi2.setText(lodging);
                System.out.print(lodging);
            }break;
            case (4):
            {
                Bundle bundle = data.getExtras();
                String delicacies = bundle.getString("date");
                travels.setResraurantInfo(delicacies);
                d=0;
                ceshi3.setText(delicacies);
                System.out.print(delicacies);
            }
            break;
        }

        /*switch (requestCode){
            case REQUEST_CAMERA:
                if (resultCode == RESULT_OK){
                    crop(mTmpFile.getAbsolutePath());
                }else {
                    Toast.makeText(mContext, "拍照失败", Toast.LENGTH_SHORT).show();
                }
                break;

            case REQUEST_CROP:
                if (resultCode == RESULT_OK){
                    rvHead.setImageURI(Uri.fromFile(mCropImageFile));
                    upHead(mCropImageFile+""); // 把图片上传到服务器
                }else {
                    Toast.makeText(mContext, "截图失败", Toast.LENGTH_SHORT).show();
                }
                break;

            case REQUEST_GALLERY:
                if (resultCode == RESULT_OK && data != null){
                    String imagePath = handleImage(data);
                    crop(imagePath);
                }else {
                    Toast.makeText(mContext, "打开图库失败", Toast.LENGTH_SHORT).show();
                }
                break;

        }
*/
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

   /* @OnClick(R.id.write_sc_but)
    public void onViewClicked(){
        setupDialog();
    }
    private void setupDialog(){
        final String[] items = {"拍照", "相册"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(context);
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    camera();
                }else if (i == 1){
                    gallery();
                }
            }
        });
        listDialog.show();
    }

    private void gallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    private void camera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            mTmpFile = new File(FileUtils.createRootPath(getBaseContext()) + "/" + System.currentTimeMillis() + ".jpg");
            FileUtils.createFile(mTmpFile);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        FileProvider.getUriForFile(getBaseContext(), BuildConfig.APPLICATION_ID + ".provider", mTmpFile));
            }else {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
            }
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
            startActivityForResult(cameraIntent, REQUEST_CAMERA);
        }
    }


    private void crop(String imagePath){
        //mCropImageFile = FileUtils.createTmpFile(getBaseContext());
        mCropImageFile = getmCropImageFile();
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(getImageContentUri(new File(imagePath)), "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCropImageFile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUEST_CROP);
    }

    //把fileUri转换成ContentUri
    public Uri getImageContentUri(File imageFile){
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    //获取裁剪的图片保存地址
    private File getmCropImageFile(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),"temp.jpg");
            File file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
            return file;
        }
        return null;
    }

    private String handleImage(Intent data) {
        Uri uri = data.getData();
        String imagePath = null;
        if (Build.VERSION.SDK_INT >= 19) {
            if (DocumentsContract.isDocumentUri(this, uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                    String id = docId.split(":")[1];
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("" +
                            "content://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(contentUri, null);
                }
            } else if ("content".equals(uri.getScheme())) {
                imagePath = getImagePath(uri, null);
            }
        } else {
            imagePath = getImagePath(uri, null);
        }
        return imagePath;
    }

    private String getImagePath(Uri uri, String seletion) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, seletion, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }*/





}
