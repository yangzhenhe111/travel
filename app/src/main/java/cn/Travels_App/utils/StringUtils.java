package cn.Travels_App.utils;

public class StringUtils {

    public static boolean isEmpty(String msg){
        if(msg == null || "".equals(msg)){
            return true;
        }else{
            return false;
        }
    }
}
