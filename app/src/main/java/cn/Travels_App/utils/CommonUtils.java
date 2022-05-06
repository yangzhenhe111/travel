package cn.Travels_App.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.model.entity.UserEntity;


public class CommonUtils {

    /**
     * 得到时间的字符串
     * @param date
     * @return
     */
    public static String getDateStr(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = simpleDateFormat.format(date);
        return result;
    }

    /**
     * 通过毫秒数返回时间
     * @param millioms
     * @return
     */
    public static String getStrByDate(Long millioms){
        Date date = new Date();
        date.setTime(millioms);
        return getDateStr(date);
    }



    //存储登录用户信息
    public static void storeLoginUser(UserEntity userEntity, Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong("id", (Long) userEntity.getId());
        editor.putString("username",String.valueOf(userEntity.getUsername()));
        editor.putString("password",String.valueOf(userEntity.getPassword()));
        editor.putString("tel",String.valueOf(userEntity.getTel()));
        editor.putString("email",String.valueOf(userEntity.getEmail()));
        if(userEntity.getHeadImg()!=null){
            editor.putString("headImg",String.valueOf(userEntity.getHeadImg()));
        }else {
            editor.putString("headImg","");
        }
//        editor.putString("bodyweight",(String)userMap.get("bodyweight"));
//        editor.putString("bodyheight",(String)userMap.get("bodyheight"));
        editor.commit();
    }

    //读取用户的信息
    public static UserEntity getLoginUser(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(settings.getLong("id",0));
        userEntity.setUsername(settings.getString("username",""));
        userEntity.setPassword(settings.getString("password",""));
        userEntity.setEmail(settings.getString("email",""));
        userEntity.setTel(settings.getString("tel",""));
        userEntity.setHeadImg(settings.getString("headImg",""));
//        userEntity.setCreatetime(settings.getString("createtime",""));
//        userEntity.setAge(settings.getString("age",""));
//        userEntity.setBodyweight(settings.getString("bodyweight",""));
//        userEntity.setBodyheight(settings.getString("bodyheight",""));
        return userEntity;
    }

    //储存搜索信息
    public static void save_travels_Condition(String sousuo,String queryTag,Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Condition",sousuo);
        editor.putString("queryTag",queryTag);
        editor.commit();
    }

    //读取搜索信息
    public static String get_travels_Condition(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        String sousuo;
        sousuo=settings.getString("Condition","");
        return sousuo;
    }

    //读取搜索信息
    public static String get_travels_queryTag(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        String sousuo;
        sousuo=settings.getString("queryTag","");
        return sousuo;
    }

    //储存当地概况信息
    public static void save_travels_briefdesc(String name,String address,String briefDesc,String opentime,Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("name",name);
        editor.putString("address",address);
        editor.putString("briefDesc",briefDesc);
        editor.putString("opentime",opentime);
        editor.commit();
    }

    //读取当地概况信息
    public static Travels get_travels_briefdesc(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        Travels travels = new Travels();
        travels.setName(settings.getString("name",""));
        travels.setAddress(settings.getString("address",""));
        travels.setBriefDesc(settings.getString("briefDesc",""));
        travels.setOpentime(settings.getString("opentime",""));
        return travels;
    }

    //储存交通信息
    public static void save_travels_traffic(String trafficInfo,Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("trafficInfo",trafficInfo);
        editor.commit();
    }

    //读取交通信息
    public static String get_travels_traffic(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        String traffic;
        traffic=settings.getString("trafficInfo","");
        return traffic;
    }

    //储存住宿信息
    public static void save_travels_hotelInfo(String hotelInfo,Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("hotelInfo",hotelInfo);
        editor.commit();
    }

    //读取住宿信息
    public static String get_travels_hotelInfo(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        String hotelInfo;
        hotelInfo=settings.getString("hotelInfo","");
        return hotelInfo;
    }

    //储存餐饮信息
    public static void save_travels_resraurantInfo(String resraurantInfo,Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("resraurantInfo",resraurantInfo);
        editor.commit();
    }

    //读取餐饮信息
    public static String get_travels_resraurantInfo(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        String resraurantInfo;
        resraurantInfo=settings.getString("resraurantInfo","");
        return resraurantInfo;
    }

    //储存封面信息
    public static void save_travels_imageurl(String imageurl,Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("imageurl",imageurl);
        editor.commit();
    }

    //读取封面信息
    public static String get_travels_imageurl(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        String traffic;
        traffic=settings.getString("imageurl","");
        return traffic;
    }

    //储存封面信息
    public static void save_travels_imgUrl(String imageurl,Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("imgUrl",imageurl);
        editor.commit();
    }

    //读取封面信息
    public static String get_travels_imgUrl(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        String traffic;
        traffic=settings.getString("imgUrl","");
        return traffic;
    }


    //储存签名信息
    public static void save_travels_signature(String signature,Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("signature",signature);
        editor.commit();
    }

    //读取签名信息
    public static String get_travels_signature(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        String signature;
        signature=settings.getString("signature","");
        return signature;
    }







    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /*//转换json的data字符串 为 对象集合
    public static List<Travels> transeToSpotsList(String data) {

        Gson gson = new Gson();
        //通过反射得到type对象
        *//*Type listType = new TypeToken<List<Travels>>() {
        }.getType();*//*

        List<Travels> tjSpotsList =
        *//*gson.fromJson(data, listType);*//*
        return tjSpotsList;
    }*/

}
