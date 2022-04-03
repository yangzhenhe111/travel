package cn.Travels_App.utils;

import android.content.Context;
import android.content.Intent;

public class AppTools {

    public static void startActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }


}
