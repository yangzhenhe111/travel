package cn.Travels_App.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import cn.Travels_App.App;
import cn.Travels_App.utils.ScreenUtils;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.jaeger.library.StatusBarUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;


public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends MvpFragment<V, P> {

    public static final String FRAG_ARG_DEPRECATED = "frag_arg_deprecated";

    protected Context context;

    protected View rootView;

    private Unbinder unbinder;

    protected Map<String, String> map = new HashMap<>();



    // 设置刷新、加载的初始值
    protected int page = 1;
    protected boolean load_more = false;
    // 是否是第一次加载数据
    protected boolean first_init_data = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, rootView);
        context = getActivity();
        getLocalUserData();
        initView();
        return rootView;
    }

    /**
     * 获得本地用户数据
     */
    protected void getLocalUserData() {


    }

    /**
     * 设置状态栏颜色跟随顶部View的颜色
     *
     * @param tbLayout 顶部View
     */
    protected void setStatusBarToView(View tbLayout) {
        StatusBarUtil.setTransparentForImageView(getActivity(), tbLayout);
    }

    /**
     * 适配顶部状态栏方法
     *
     * @param title_bar title bar layout
     */
    protected void adapterStatus(View title_bar) {
        int status_height = ScreenUtils.getStatusHeight(getContext());
        ViewGroup.LayoutParams params = title_bar.getLayoutParams();
        params.height = params.height + status_height;
        title_bar.setLayoutParams(params);
        title_bar.setPadding(0, status_height, 0, 0);
    }


    /**
     * 判断当前系统版本大于21以上才针对状态栏设置marginTop间距
     *
     * @return 是否成立
     */
    protected boolean api21Greater() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * 设置状态栏字体颜色黑暗模式（黑）
     */
    protected void setLightMode() {
        if (getActivity() != null)
            StatusBarUtil.setLightMode(getActivity());
    }

    /**
     * 设置状态栏字体颜色为明亮模式（白）
     * 注：系统状态栏默认即使明亮模式
     */
    protected void setDarkMode() {
        if (getActivity() != null)
            StatusBarUtil.setDarkMode(getActivity());
    }

    /**
     * 获取上级页面传过来的bundle数据
     */
    protected Bundle getBundle() {
        Intent intent = requireActivity().getIntent();
        if (intent.getExtras() != null)
            return intent.getExtras();
        return null;
    }

    // 初始化Map中的基础参数
    protected void initMap() {


    }

    /**
     * 判断数据是否为空
     */
    protected boolean dataIsEmpty(List<?> data) {
        return data == null || data.size() == 0;
    }

    /**
     * 判断数据是否为空
     */
    protected boolean dataIsEmpty(Object data) {
        return data == null;
    }


    @Override
    public void onResume() {
        super.onResume();
        getLocalUserData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map = null;
        if (unbinder != null)
            unbinder.unbind();
    }

    public View getRootView() {
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    public App getApp() {
        return (App) getActivity().getApplication();
    }

    public <T> void toSetList(List<T> list, List<T> newList, boolean isMore) {

        if (list != null && newList != null) {
            synchronized (BaseFragment.class) {
                if (!isMore) {
                    list.clear();
                }
                list.addAll(newList);
            }
        }
    }



    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(context, cls));
    }

    protected void finish() {
        getActivity().finish();
    }



    /**
     * 隐藏软键盘
     *
     * @param et
     */
    protected void hideInputMethod(EditText et) {
        InputMethodManager imm = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        et.clearFocus();
    }

    /**
     * 显示软键盘
     *
     * @param et
     */
    public void showInputMethod(final EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();
}
