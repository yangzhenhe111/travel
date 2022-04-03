package cn.Travels_App;

import android.app.Application;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.StrictMode;

import androidx.multidex.MultiDex;


import cn.Travels_App.ui.activity.MainActivity;
import cn.Travels_App.utils.CrashHandler;
import cn.Travels_App.utils.DynamicTimeFormat;
import cn.Travels_App.utils.ToastUtils;
import cn.Travels_App.common.Constants;
import cn.Travels_App.di.componet.AppComponent;
import cn.Travels_App.di.componet.DaggerAppComponent;
import cn.Travels_App.di.module.AppModule;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import io.reactivex.annotations.NonNull;

public class App extends Application {
    private static App INSTANCE;

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    private AppComponent mAppComponent;

    public static MainActivity mainActivity;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        App.mainActivity = mainActivity;
    }

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
        INSTANCE = this;
    }

    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
        ToastUtils.showToast(getInstance().mAppComponent.getContext(), "已复制到剪切板");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        setStrictMode();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this, Constants.BASE_URL)).build();

    }

    /**
     * 严谨模式
     */
    protected void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                // 最大显示下拉高度/Header标准高度
                layout.setHeaderMaxDragRate(1.6f);
                // 最大显示上拉高度/Footer标准高度
                layout.setFooterMaxDragRate(1f);
                //全局设置（优先级最低）
                layout.setEnableAutoLoadMore(true);
                // //是否启用越界拖动（仿苹果效果）1.0.4
                layout.setEnableOverScrollDrag(false);
                layout.setEnableOverScrollBounce(true);
                layout.setEnableLoadMoreWhenContentNotFull(true);
                layout.setEnableScrollContentWhenRefreshed(true);
                layout.setPrimaryColorsId(R.color.gray, android.R.color.black);
            }
        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
                layout.setEnableHeaderTranslationContent(true);
                return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
            }
        });
    }
}

