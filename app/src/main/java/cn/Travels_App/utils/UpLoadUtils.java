package cn.Travels_App.utils;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import cn.Travels_App.common.Constants;
import cn.Travels_App.network.HttpResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpLoadUtils {

    private static Gson gson = new Gson();

    public static String uploadImg(Context context,String path,String url) {
        String result = null;
        File file = new File(path);
        if (!file.exists()) {
            ToastUtils.showToast(context,"文件不存在");
            return path;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("file",path, RequestBody.create(MediaType.parse("image/jpg"),file));
        RequestBody requestBody = builder.build();
        Request.Builder reqBuilder = new Request.Builder();
        Request request = reqBuilder
                .url(url)
                .header("header-user", new Gson().toJson(CommonUtils.getLoginUser(context)))
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            result = response.body().string();
            HttpResult httpResult = gson.fromJson(result, HttpResult.class);
            if (httpResult.isSuccess()) {
                result = httpResult.getMsg();
            }else {
                ToastUtils.showToast(context,httpResult.getMsg());
                result = path;
            }
        } catch (IOException e) {
            e.printStackTrace();
            ToastUtils.showToast(context,e.getMessage());
        }
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                //失败
//                ToastUtils.showToast(context,"上传图片失败");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                result[0] = response.body().toString();
//            }
//        });
        return result;
    }
}
