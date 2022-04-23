package cn.Travels_App.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import cn.Travels_App.App;
import cn.Travels_App.ui.fragment.TravelsFragment;
import cn.Travels_App.ui.fragment.HomeFragment;
import cn.Travels_App.ui.fragment.MineFragment;
import cn.Travels_App.persenter.MainPersenter;
import cn.Travels_App.R;
import cn.Travels_App.ui.fragment.WriteFragment;
import cn.Travels_App.utils.ToastUtils;
import cn.Travels_App.view.MainView;
import cn.Travels_App.base.BaseActivity;
import io.reactivex.annotations.NonNull;

/**
 * 首页-MainActivity
 */
public class MainActivity extends BaseActivity<MainView, MainPersenter> implements MainView, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.main_radio_group)
    RadioGroup mainRadioGroup;

    @BindView(R.id.home_radio_btn)
    RadioButton homeRadioBtn;

    @BindView(R.id.spots_radio_btn)
    RadioButton spotsRadioBtn;


    @BindView(R.id.wtite_radio_btn)
    RadioButton wtiteRadioBtn;


    @BindView(R.id.mine_radio_btn)
    RadioButton mineRadioBtn;

    @BindView(R.id.main_frame_container)
    FrameLayout mainFrameContainer;

    @BindView(R.id.main_btm_wire)
    View mainBtmWire;

    @BindView(R.id.root_layout)
    ConstraintLayout rootLayout;

    private HomeFragment homeFragment;
    private TravelsFragment travelsFragment;
    private WriteFragment writeFragment;
    private MineFragment mineFragment;

    private String  queryTag;
    public static String write_name = null;
    private static MainActivity mainInstance = null;

    public static MainActivity getInstance() {
        return mainInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.setMainActivity(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_act;
    }

    @Override
    public void initView() {
        mainInstance = this;
        mainRadioGroup.setOnCheckedChangeListener(this);
        //获取参数，是否是搜索景区，跳转到第二个
        Intent intent = getIntent();
        String queryTag = intent.getStringExtra("queryTag");
        String sousuo = intent.getStringExtra("sousuo");


        //如果等于1 表示跳转到景区
        String gotoFragmentTag = intent.getStringExtra("gotoFragmentTag");

        if("1".equals(gotoFragmentTag)){
            spotsRadioBtn.setChecked(true);
            showAsFragment(1);
            return;
        }
        if("2".equals(gotoFragmentTag)){
            wtiteRadioBtn.setChecked(true);
            showAsFragment(2);
            return;
        }
        if("3".equals(gotoFragmentTag)){
            mineRadioBtn.setChecked(true);
            showAsFragment(3);
            return;
        }

        //获取参数是否为2分享游记，跳转到第三个
        String writetag=intent.getStringExtra("tag");
        System.out.println(writetag);
        /* String cover=intent.getStringExtra("write_cover");*/
        String name=intent.getStringExtra("write_name");
        String address=intent.getStringExtra("write_address");
        String opentime=intent.getStringExtra("write_opentime");
        String briefDesc=intent.getStringExtra("write_briefDesc");
        String trafficInfo=intent.getStringExtra("write_trafficInfo");
        String resraurantInfo=intent.getStringExtra("write_resraurantInfo");
        String hotelInfo=intent.getStringExtra("write_hotelInfo");




        if("1".equals(queryTag)){
              spotsRadioBtn.setChecked(true);
                //有搜索条件的参数
                Bundle bundle = new Bundle();
                bundle.putString("queryTag","1");
                bundle.putString("sousuo",sousuo);
                travelsFragment.setArguments(bundle);
            showAsFragment(1);
        }else if("2".equals(writetag)){
            System.out.println("M1");
            wtiteRadioBtn.setChecked(true);
            Bundle bundle = new Bundle();
            bundle.putString("tag","2");
            /*bundle.putString("write_cover",cover);*/
            bundle.putString("write_name",name);
            bundle.putString("write_address",address);
            bundle.putString("write_opentime",opentime);
            bundle.putString("write_briefDesc",briefDesc);
            bundle.putString("write_trafficInfo",trafficInfo);
            bundle.putString("write_resraurantInfo",resraurantInfo);
            bundle.putString("write_hotelInfo",hotelInfo);
            writeFragment.setArguments(bundle);
            showAsFragment(2);
        }else {

            // 默认显示第一个
            showAsFragment(0);
        }
    }

    @Override
    public void initData() {

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @NonNull
    @Override
    public MainPersenter createPresenter() {
        return new MainPersenter(getApp());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.home_radio_btn:
                showAsFragment(0);
                break;
            case R.id.spots_radio_btn:
                showAsFragment(1);
                break;
            case R.id.wtite_radio_btn:
                showAsFragment(2);
                break;
            case R.id.mine_radio_btn:
                showAsFragment(3);
                break;
            default:
                break;
        }
    }

    /**
     * 显示指定的Fragment
     *
     * @param index 指定索引位置
     */
    private void showAsFragment(int index) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        transaction.show(createCurrentFragment(transaction, index)).commitAllowingStateLoss();
    }

    private Fragment createCurrentFragment(FragmentTransaction transaction, int index) {
        Fragment fragment = null;
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance();
                    transaction.add(R.id.main_frame_container, homeFragment);
                }
                fragment = homeFragment;
                break;
            case 1:
                if (travelsFragment == null) {
                    travelsFragment = travelsFragment.newInstance();
                    transaction.add(R.id.main_frame_container, travelsFragment);
                }

                fragment = travelsFragment;
                break;
            case 2:
                if (writeFragment == null) {
                    writeFragment = writeFragment.newInstance();
                    transaction.add(R.id.main_frame_container, writeFragment);
                }
                fragment = writeFragment;
                break;
            case 3:
                if (mineFragment == null) {
                    mineFragment = MineFragment.newInstance();
                    transaction.add(R.id.main_frame_container, mineFragment);
                }
                fragment = mineFragment;
                break;
            default:
                break;
        }
        return fragment;
    }

    /**
     * 隐藏所有Fragment
     *
     * @param transaction transaction
     */
    private void hideAllFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (travelsFragment != null) {
            transaction.hide(travelsFragment);
        }
        if (writeFragment != null) {
            transaction.hide(writeFragment);
        }

        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }



//    @OnClick({R.id.main_zm_img, R.id.float_btn})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.main_zm_img:
//                //mainRadioGroup.check(R.id.zm_radio_btn);
//                showAsFragment(2);
//                break;
//            case R.id.float_btn:
//                RxPermissions rxPermissions = new RxPermissions(Objects.requireNonNull(this));
//                rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION)
//                        .subscribe(new Consumer<Boolean>() {
//                            @Override
//                            public void accept(Boolean aBoolean) throws Exception {
//                                AppTools.startFmActivity(MainActivity.this, Constants.PushIncident);
//                            }
//                        });
//                break;
//            default:
//                break;
//        }
//    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Integer index) {
//        Logger.w("" + index);
        switch (index) {
            case 0:
                homeRadioBtn.setChecked(true);
                break;
            case 1:
                spotsRadioBtn.setChecked(true);
                break;
            case 2:
                wtiteRadioBtn.setChecked(true);
                break;
            case 3:
                mineRadioBtn.setChecked(true);
                break;
        }
        showAsFragment(index);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showToast(this, "再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
                Process.killProcess(Process.myPid());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onFailed(String msg) {

    }
}
