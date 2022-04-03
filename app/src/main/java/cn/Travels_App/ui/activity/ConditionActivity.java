package cn.Travels_App.ui.activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Map;

import butterknife.BindView;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.common.FlowAutoRadioGroup;
import cn.Travels_App.model.entity.City;
import cn.Travels_App.model.entity.Level;
import cn.Travels_App.model.entity.PriceArea;
import cn.Travels_App.model.entity.Theme;
import cn.Travels_App.persenter.ConditionPersenter;
import cn.Travels_App.view.Conditionview;

public class ConditionActivity extends BaseActivity<Conditionview, ConditionPersenter> implements Conditionview  {


    public Conditionview conditionview;

    @BindView(R.id.city_arg)
    public FlowAutoRadioGroup cityGroup;
    @BindView(R.id.cd_rd)
    public RadioButton cd_rd;
    @BindView(R.id.zg_rd)
    public RadioButton zg_rd;
    @BindView(R.id.pzh_rd)
    public RadioButton pzh_rd;
    @BindView(R.id.lz_rd)
    public RadioButton lz_rd;
    @BindView(R.id.dy_rd)
    public RadioButton dy_rd;
    @BindView(R.id.my_rd)
    public RadioButton my_rd;
    @BindView(R.id.gy_rd)
    public RadioButton gy_rd;
    @BindView(R.id.sn_rd)
    public RadioButton sn_rd;
    @BindView(R.id.nj_rd)
    public RadioButton nj_rd;
    @BindView(R.id.ls_rd)
    public RadioButton ls_rd;
    @BindView(R.id.nc_rd)
    public RadioButton nc_rd;
    @BindView(R.id.yb_rd)
    public RadioButton yb_rd;
    @BindView(R.id.ga_rd)
    public RadioButton ga_rd;
    @BindView(R.id.dz_rd)
    public RadioButton dz_rd;
    @BindView(R.id.ms_rd)
    public RadioButton ms_rd;
    @BindView(R.id.ya_rd)
    public RadioButton ya_rd;
    @BindView(R.id.bz_rd)
    public RadioButton bz_rd;
    @BindView(R.id.zy_rd)
    public RadioButton zy_rd;
    @BindView(R.id.abz_rd)
    public RadioButton abz_rd;
    @BindView(R.id.gzz_rd)
    public RadioButton gzz_rd;
    @BindView(R.id.lsz_rd)
    public RadioButton lsz_rd;

    @BindView(R.id.level_arg)
    public RadioGroup levelGroup;
    @BindView(R.id.yxj_levelrd)
    public RadioButton yxj_levelrd;
    @BindView(R.id.exj_levelrd)
    public RadioButton exj_levelrd;
    @BindView(R.id.sxj_levelrd)
    public RadioButton sxj_levelrd;
    @BindView(R.id.sixj_levelrd)
    public RadioButton sixj_levelrd;
    @BindView(R.id.waj_levelrd)
    public RadioButton waj_levelrd;


    @BindView(R.id.theme_arg)
    public RadioGroup themeGroup;
    @BindView(R.id.wqy_themerd)
    public RadioButton wqy_themerd;
    @BindView(R.id.psy_themerd)
    public RadioButton psy_themerd;
    @BindView(R.id.zrfg_themerd)
    public RadioButton zrfg_themerd;
    @BindView(R.id.pmy_themerd)
    public RadioButton pmy_themerd;
    @BindView(R.id.wsxx_themerd)
    public RadioButton wsxx_themerd;
    @BindView(R.id.hxy_themerd)
    public RadioButton hxy_themerd;



    @BindView(R.id.price_arg)
    public RadioGroup pricefwGroup;
    @BindView(R.id.price1rd)
    public RadioButton price1rd;
    @BindView(R.id.price2rd)
    public RadioButton price2rd;
    @BindView(R.id.price3rd)
    public RadioButton price3rd;
    @BindView(R.id.price4rd)
    public RadioButton price4rd;
    @BindView(R.id.price5rd)
    public RadioButton price5rd;
    @BindView(R.id.price6rd)
    public RadioButton price6rd;

    public Long cityid = 0L;
    public Long themeid = 0L;
    public Long levelid = 0L;
    public Long pricefwid = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @NonNull
    @Override
    public ConditionPersenter createPresenter() {
        conditionview = getMvpView();
        return new ConditionPersenter(getApp(),conditionview);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_condition;
    }

    @Override
    public void initView() {
        //城市
        cityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==cd_rd.getId()){
                    //成都市
                    cityid = City.getCityIdByName("成都市");
                }else if(checkedId==zg_rd.getId()){
                    //自贡市
                    cityid = City.getCityIdByName("自贡市");
                }else if(checkedId==pzh_rd.getId()){
                    //攀枝花
                    cityid = City.getCityIdByName("攀枝花");
                }else if(checkedId==lz_rd.getId()){
                    //泸州市
                    cityid = City.getCityIdByName("泸州市");
                }else if(checkedId==dy_rd.getId()){
                    //德阳市
                    cityid = City.getCityIdByName("德阳市");
                }else if(checkedId==my_rd.getId()){
                    //绵阳市
                    cityid = City.getCityIdByName("绵阳市");
                }else if(checkedId==gy_rd.getId()){
                    //广元市
                    cityid = City.getCityIdByName("广元市");
                }else if(checkedId==sn_rd.getId()){
                    //遂宁市
                    cityid = City.getCityIdByName("遂宁市");
                }else if(checkedId==nj_rd.getId()){
                    //内江市
                    cityid = City.getCityIdByName("内江市");
                }else if(checkedId==ls_rd.getId()){
                    //乐山市
                    cityid = City.getCityIdByName("乐山市");
                }else if(checkedId==nc_rd.getId()){
                    //南充市
                    cityid = City.getCityIdByName("南充市");
                }else if(checkedId==yb_rd.getId()){
                    //宜宾市
                    cityid = City.getCityIdByName("宜宾市");
                }else if(checkedId==ga_rd.getId()){
                    //广安市
                    cityid = City.getCityIdByName("广安市");
                }else if(checkedId==dz_rd.getId()){
                    //达州市
                    cityid = City.getCityIdByName("达州市");
                }else if(checkedId==ms_rd.getId()){
                    //眉山市
                    cityid = City.getCityIdByName("眉山市");
                }else if(checkedId==ya_rd.getId()){
                    //雅安市
                    cityid = City.getCityIdByName("雅安市");
                }else if(checkedId==bz_rd.getId()){
                    //巴中市
                    cityid = City.getCityIdByName("巴中市");
                }else if(checkedId==zy_rd.getId()){
                    //资阳市
                    cityid = City.getCityIdByName("资阳市");
                }else if(checkedId==abz_rd.getId()){
                    //阿坝州
                    cityid = City.getCityIdByName("阿坝州");
                }else if(checkedId==gzz_rd.getId()){
                    //甘孜州
                    cityid = City.getCityIdByName("甘孜州");
                }else if(checkedId==lsz_rd.getId()){
                    //凉山州
                    cityid = City.getCityIdByName("凉山州");
                }
            }
        });


        //主题
        themeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==wqy_themerd.getId()){
                    //温泉游
                    themeid = new Theme().getThemeidByName("温泉游");
                }else if(checkedId==psy_themerd.getId()){
                    //爬山游
                    themeid = new Theme().getThemeidByName("爬山游");
                }else if(checkedId==zrfg_themerd.getId()){
                    //爬山游
                    themeid = new Theme().getThemeidByName("自然风光");
                }else if(checkedId==pmy_themerd.getId()){
                    //爬山游
                    themeid = new Theme().getThemeidByName("拜佛游");
                }else if(checkedId==wsxx_themerd.getId()){
                    //爬山游
                    themeid = new Theme().getThemeidByName("玩水嬉戏");
                }else if(checkedId==hxy_themerd.getId()){
                    //爬山游
                    themeid = new Theme().getThemeidByName("划雪游");
                }
            }
        });

        //pricefwGroup价格区间
        pricefwGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==price1rd.getId()){
                    //0-99
                    pricefwid = new PriceArea().getPricefwidByName("0-99");
                }else if(checkedId==price2rd.getId()){
                    //100-199
                    pricefwid = new PriceArea().getPricefwidByName("100-199");
                }else if(checkedId==price3rd.getId()){
                    //200-299
                    pricefwid = new PriceArea().getPricefwidByName("200-299");
                }else if(checkedId==price4rd.getId()){
                    //300-399
                    pricefwid = new PriceArea().getPricefwidByName("300-399");
                }else if(checkedId==price5rd.getId()){
                    //400-499
                    pricefwid = new PriceArea().getPricefwidByName("400-499");
                }else if(checkedId==price6rd.getId()){
                    //500以上
                    pricefwid = new PriceArea().getPricefwidByName("500以上");
                }
            }
        });

        //levelGroup等级
        levelGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==yxj_levelrd.getId()){
                    //A
                    levelid = Level.getLevelIdByName("A");
                }else if(checkedId==exj_levelrd.getId()){
                    //AA
                    levelid = Level.getLevelIdByName("AA");
                }else if(checkedId==sxj_levelrd.getId()){
                    //AAA
                    levelid = Level.getLevelIdByName("AAA");
                }else if(checkedId==sixj_levelrd.getId()){
                    //AAAA
                    levelid = Level.getLevelIdByName("AAAA");
                }else if(checkedId==waj_levelrd.getId()){
                    //AAAAA
                    levelid = Level.getLevelIdByName("AAAAA");
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    //确认提交

    public void confirmSubimt(View view){

        System.out.println("cityid:"+cityid);
        Intent intent = new Intent(ConditionActivity.this,
                MainActivity.class);
        intent.putExtra("queryTag","1");
        intent.putExtra("cityid",cityid);
        intent.putExtra("levelid",levelid);
        intent.putExtra("themeid",themeid);
        intent.putExtra("pricefwid",pricefwid);
        startActivity(intent);
    }

    //返回功能
    public  void backBtnFun(View view){
        Intent intent = new Intent(ConditionActivity.this,
                MainActivity.class);
        intent.putExtra("queryTag","1");
        intent.putExtra("cityid",0L);
        intent.putExtra("levelid",0L);
        intent.putExtra("themeid",0L);
        intent.putExtra("pricefwid",0L);
        startActivity(intent);
    }

    @Override
    public void onSuccessData(Map resultMap) {

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
}