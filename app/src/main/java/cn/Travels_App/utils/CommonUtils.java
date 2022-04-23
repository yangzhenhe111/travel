package cn.Travels_App.utils;

import android.content.Context;
import android.content.Intent;
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
import java.util.Map;

import cn.Travels_App.model.entity.CasesEntity;
import cn.Travels_App.model.entity.DoctorEntity;
import cn.Travels_App.model.entity.HealthyEntity;
import cn.Travels_App.model.entity.Hotel;
import cn.Travels_App.model.entity.Order;
import cn.Travels_App.model.entity.Qa;
import cn.Travels_App.model.entity.Spots;
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

    /**
     * 转换医生列表
     * @param resultJson
     * @return
     */

    public static List<DoctorEntity> transeToNewsList(String resultJson) {
        Gson gson = new Gson();
        //通过反射得到type对象
        Type listType = new TypeToken<List<DoctorEntity>>() {
        }.getType();

        List<DoctorEntity> newsList = gson.fromJson(resultJson, listType);
        return newsList;
    }



    public static List<DoctorEntity> getListByPos(int pos, List<DoctorEntity> newsList){

        String[] titles = {"推荐","热门","社会","体育","娱乐","军事","财经","房产"};
        List resultList = new ArrayList();
        switch (pos){
            case 0:
                newsList.forEach(news->{
                    if(news.getLabelid()==1){
                        resultList.add(news);
                    }
                });
                break;
            case 1:
                newsList.forEach(news->{
                    if(news.getLabelid()==2){
                        resultList.add(news);
                    }
                });
                break;
            case 2:
                newsList.forEach(news->{
                    if(news.getLabelid()==3){
                        resultList.add(news);
                    }
                });
                break;
            case 3:
                newsList.forEach(news->{
                    if(news.getLabelid()==4){
                        resultList.add(news);
                    }
                });
                break;
            case 4:
                newsList.forEach(news->{
                    if(news.getLabelid()==5){
                        resultList.add(news);
                    }
                });
                break;
            case 5:
                newsList.forEach(news->{
                    if(news.getLabelid()==6){
                        resultList.add(news);
                    }
                });
                break;
            case 6:
                newsList.forEach(news->{
                    if(news.getLabelid()==7){
                        resultList.add(news);
                    }
                });
                break;
            case 7:
                newsList.forEach(news->{
                    if(news.getLabelid()==8){
                        resultList.add(news);
                    }
                });
                break;

        }

        return resultList;

    }


    //存储登录用户信息
    public static void storeLoginUser(UserEntity userEntity, Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("id", (int) userEntity.getId());
        editor.putString("username",String.valueOf(userEntity.getUsername()));
        editor.putString("password",String.valueOf(userEntity.getPassword()));
        editor.putString("tel",String.valueOf(userEntity.getTel()));
        editor.putString("email",String.valueOf(userEntity.getEmail()));
//        editor.putString("bodyweight",(String)userMap.get("bodyweight"));
//        editor.putString("bodyheight",(String)userMap.get("bodyheight"));
        editor.commit();
    }

    //读取用户的信息
    public static UserEntity getLoginUser(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(settings.getInt("id",0));
        userEntity.setUsername(settings.getString("username",""));
        userEntity.setPassword(settings.getString("password",""));
        userEntity.setEmail(settings.getString("email",""));
        userEntity.setTel(settings.getString("tel",""));
//        userEntity.setCreatetime(settings.getString("createtime",""));
//        userEntity.setAge(settings.getString("age",""));
//        userEntity.setBodyweight(settings.getString("bodyweight",""));
//        userEntity.setBodyheight(settings.getString("bodyheight",""));
        return userEntity;
    }

    //储存当地概况信息
   /* public static void savetravels(Travels travels, Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        SharedPreferences.Editor editor = settings.edit();
        *//*private Long id;
        private String publishDate;
        private String cover;
        private String name;
        private String address;
        private String opentime;
        private Double budget;
        private String briefDesc;
        private String trafficInfo;
        private String resraurantInfo;
        private String hotelInfo;
        private String createtime;
        private Long creator;
        private String creatorName;
        private String creatorCover;*//*
        editor.putString("name",String.valueOf(travels.getName()));
        editor.putString("address",String.valueOf(travels.getAddress()));
        editor.putString("briefDesc",String.valueOf(travels.getBriefDesc()));
        editor.putString("opentime",String.valueOf(travels.getOpentime()));
        editor.commit();
    }

    //读取当地概况信息
    public static Travels gettravels(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        Travels travels = new Travels();
        travels.setName(settings.getString("name",""));
        travels.setAddress(settings.getString("address",""));
        travels.setBriefDesc(settings.getString("briefDesc",""));
        travels.setOpentime(settings.getString("opentime",""));
        return travels;
    }*/


    public static List<HealthyEntity> transeToHealthyList(String data) {
        Gson gson = new Gson();
        //通过反射得到type对象
        Type listType = new TypeToken<List<HealthyEntity>>() {
        }.getType();

        List<HealthyEntity> healthyEntityList = gson.fromJson(data, listType);
        return healthyEntityList;
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

    public static List<CasesEntity> transeToCasesList(String data) {
        Gson gson = new Gson();
        //通过反射得到type对象
        Type listType = new TypeToken<List<CasesEntity>>() {
        }.getType();

        List<CasesEntity> casesEntities = gson.fromJson(data, listType);
        return casesEntities;
    }

    public static List<Order> transeToYdList(String data) {
        Gson gson = new Gson();
        //通过反射得到type对象
        Type listType = new TypeToken<List<Order>>() {
        }.getType();

        List<Order> orderList = gson.fromJson(data, listType);
        return orderList;
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

    public static List<Qa> transeToQaList(String data) {
        Gson gson = new Gson();
        //通过反射得到type对象
        Type listType = new TypeToken<List<Qa>>() {
        }.getType();

        List<Qa> qaList = gson.fromJson(data, listType);
        return qaList;
    }

    public static List<Hotel> transeToHotelList(String data) {
        Gson gson = new Gson();
        //通过反射得到type对象
        Type listType = new TypeToken<List<Hotel>>() {
        }.getType();

        List<Hotel> hotels = gson.fromJson(data, listType);
        return hotels;
    }
}
