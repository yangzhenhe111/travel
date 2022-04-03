package cn.Travels_App.base;

import android.os.Bundle;

import androidx.annotation.ColorInt;

import cn.Travels_App.App;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;

public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        
        initData();
    }

    /**
     * 设置状态栏颜色
     * @param color 颜色
     */
    protected void setStatusBar(@ColorInt int color) {
        StatusBarUtil.setColor(this, color);
    }

    public App getApp() {
        return (App) getApplication();
    }

    protected void showErrorAndQuit(String errorMsg) {
        // TODO: 2021/2/23 错误处理
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();
}
