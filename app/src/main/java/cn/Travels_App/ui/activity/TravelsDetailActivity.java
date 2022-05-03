package cn.Travels_App.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.tools.ScreenUtils;

import java.io.IOException;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.common.Constants;
import cn.Travels_App.model.dto.CommentRespDTO;
import cn.Travels_App.model.dto.PageBean;
import cn.Travels_App.model.dto.PageRequest;
import cn.Travels_App.model.entity.Comment;
import cn.Travels_App.model.entity.CommentBean;
import cn.Travels_App.model.entity.CommentDetailBean;
import cn.Travels_App.model.entity.ReplyDetailBean;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.network.APIService;
import cn.Travels_App.network.BaseObserver;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.persenter.TravelsDetailPersenter;
import cn.Travels_App.ui.adapter.CommentExpandAdapter;
import cn.Travels_App.ui.adapter.TravelCommentAdapter;
import cn.Travels_App.utils.CommentExpandableListView;
import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.view.TravelsDetailView;
import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TravelsDetailActivity extends BaseActivity<TravelsDetailView, TravelsDetailPersenter> implements TravelsDetailView {

    Travels mSpots;

    @BindView(R.id.spotsdetail_nameTv)
    TextView nameTv;
    @BindView(R.id.spotsdetail_opentimeTv)
    TextView opentimeTv;
    @BindView(R.id.spotsdetail_briefDesc)
    TextView briefDesc;
    @BindView(R.id.spotsdetail_addressTv)
    TextView addressTv;
    @BindView(R.id.spotsdetail_fmUrlTv)
    ImageView fmUrlTv;
    @BindView(R.id.faburenxingming)
    TextView fbname;
    @BindView(R.id.fabushijian)
    TextView fbtime;
    @BindView(R.id.spotsdetail_jiaotong)
    TextView jiaotong;
    @BindView(R.id.spotsdetail_zhusu)
    TextView zhusu;
    @BindView(R.id.spotsdetail_meishi)
    TextView meishi;


    TravelsDetailView mTravelsDetailView;

    TravelsDetailPersenter travelsDetailPersenter;


    private CommentExpandAdapter adapter;
    private static final String TAG = "TravelsDetail";
    private CommentExpandableListView expandableListView;
    private CommentBean commentBean;
    private List<CommentDetailBean> commentsList;
    private BottomSheetDialog dialog;

    /*
    评论
     */
    private Comment comment;
    /*
    评论数据源
     */
    private PageBean<CommentRespDTO> commentSource;
    /*
    评论列表
     */
    private ListView commentListView;
    /*
    评论Adapter
     */
    private TravelCommentAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @OnClick(R.id.detail_top_back)
    void backPage(View view){
        /*Intent intent=new Intent();
        intent.setClass(this, MainActivity.class);
        //启动
        startActivity(intent);*/
        finish();
    }


    @NonNull
    @Override
    public TravelsDetailPersenter createPresenter() {
        mTravelsDetailView = getMvpView();
        if(travelsDetailPersenter == null) {
            travelsDetailPersenter = new TravelsDetailPersenter(getApp(), mTravelsDetailView);
        }
        return travelsDetailPersenter;

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_travels_detail;
    }

    @Override
    public void initView() {
        System.out.println("T1");

//        loadingComment();
//        expandableListView = (CommentExpandableListView) findViewById(R.id.detail_page_comment_container);
//        commentsList = generateTestData();
//        initExpandableListView(commentsList);
    }

    private void loadingComment() {

        commentListView = findViewById(R.id.ll_comment);
        List<CommentRespDTO> source = commentSource.getData();
        if (Objects.nonNull(source) && source.size() > 0){
            myAdapter = new TravelCommentAdapter(this,source,R.layout.comment_liem_layout);
            commentListView.setAdapter(myAdapter);
            setListViewHeight(commentListView);

            commentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showReplyDialog(position);
                }
            });
        }

    }


    /**
     * by moos on 2018/04/20
     * func:弹出回复框
     */
    private void showReplyDialog(final int position){
        BottomSheetDialog dialog = new BottomSheetDialog(TravelsDetailActivity.this);
        View commentView = LayoutInflater.from(TravelsDetailActivity.this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint("回复 " + commentSource.getData().get(position).getUsername() + " 的评论:");
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(replyContent)){

                    //提交回复信息
                    Comment commitInfo = new Comment();
                    commitInfo.setContent(replyContent);
                    commitInfo.setTravelsId(commentSource.getData().get(position).getTravelsId());
                    commitInfo.setParentId(commentSource.getData().get(position).getId());

                    if (submitData(commitInfo)){
                        dialog.dismiss();
                        PageRequest<Comment> request = new PageRequest<>();
                        Comment comment1 = new Comment();
                        comment1.setTravelsId(mSpots.getId());
                        request.setData(comment1);
                        request.setPageNum(1);
                        request.setPageSize(10);
                        //获取数据
                        commentSource = getCommentList(request);
                        loadingComment();
                        Toast.makeText(TravelsDetailActivity.this,"回复成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(TravelsDetailActivity.this,"提交失败，请重试！",Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(TravelsDetailActivity.this,"回复内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    private boolean submitData(Comment comment) {
        OkHttpClient client = new OkHttpClient();

        //json为String类型的json数据
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(comment));
        //创建一个请求对象
//                        String format = String.format(KeyPath.Path.head + KeyPath.Path.waybillinfosensor, username, key, current_timestamp);
        Request request = new Request.Builder()
                .url(Constants.BASE_URL+"front/comment/save")
                .addHeader("header-user", new Gson().toJson(CommonUtils.getLoginUser(TravelsDetailActivity.this)))
                .post(requestBody)
                .build();

        try {
            Response execute = client.newCall(request).execute();
            HttpResult<Comment> result = new Gson().fromJson(execute.body().string(),HttpResult.class);
            return result.isSuccess();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;


    }

    private PageBean<CommentRespDTO> getCommentList(PageRequest<Comment> condition) {
        final PageBean<CommentRespDTO>[] result = new PageBean[]{new PageBean<>()};

        getApp().getAppComponent().getAPIService().queryCommentList(condition)
                .subscribe(new BaseObserver<HttpResult<PageBean<CommentRespDTO>>>() {
                    @Override
                    public void onSuccess(HttpResult<PageBean<CommentRespDTO>> pageBeanHttpResult) {
                        result[0] = pageBeanHttpResult.getData();
//                        loadingComment();
                    }
                    @Override
                    public void onFailure(Throwable e) {
                        Log.e("error",e.toString());
                    }
                });
        return result[0];
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.detail_page_do_comment)
    public void onClick() {
            showCommentDialog();
    }

    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(replyContent)){

                    //提交回复信息
                    Comment commitInfo = new Comment();
                    commitInfo.setContent(replyContent);
                    commitInfo.setTravelsId(mSpots.getId());

                    if (submitData(commitInfo)){
                        dialog.dismiss();
                        PageRequest<Comment> request = new PageRequest<>();
                        Comment comment1 = new Comment();
                        comment1.setTravelsId(mSpots.getId());
                        request.setData(comment1);
                        request.setPageNum(1);
                        request.setPageSize(10);
                        //获取数据
                        commentSource = getCommentList(request);
                        loadingComment();
                        Toast.makeText(TravelsDetailActivity.this,"回复成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(TravelsDetailActivity.this,"提交失败，请重试！",Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(TravelsDetailActivity.this,"回复内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
//        commentText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
//                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
//                }else {
//                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
        dialog.show();
    }

//    /**
//     * by moos on 2018/04/20
//     * func:弹出回复框
//     */
//    private void showReplyDialog(final int position){
//        dialog = new BottomSheetDialog(this);
//        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
//        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
//        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
//        commentText.setHint("回复 " + commentsList.get(position).getNickName() + " 的评论:");
//        dialog.setContentView(commentView);
//        bt_comment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String replyContent = commentText.getText().toString().trim();
//                if(!TextUtils.isEmpty(replyContent)){
//
//                    dialog.dismiss();
//                    ReplyDetailBean detailBean = new ReplyDetailBean("小红",replyContent);
//                    adapter.addTheReplyData(detailBean, position);
//                    expandableListView.expandGroup(position);
//                    Toast.makeText(TravelsDetailActivity.this,"回复成功",Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(TravelsDetailActivity.this,"回复内容不能为空",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        commentText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
//                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
//                }else {
//                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//        dialog.show();
//    }


    @Override
    public void initData() {
        mSpots = (Travels) getIntent().getExtras().get("travelEntity");

        if((isEmpty(mSpots.getCreatorName())==true)){
            fbname.setText(mSpots.getCreatorName());
        }else{
            fbname.setText("————");
        }

        if((isEmpty(mSpots.getCreatetime())==true)){
            String[] str=mSpots.getCreatetime().split("[T]");
            fbtime.setText("发布于 "+str[0]);
        }else{
            fbtime.setText("发布于 "+"---");
        }

        if((isEmpty(mSpots.getTrafficInfo())==true)){
            jiaotong.setText(mSpots.getTrafficInfo());
        }else{
            jiaotong.setText("");
        }

        if((isEmpty(mSpots.getHotelInfo())==true)){
            zhusu.setText(mSpots.getHotelInfo());
        }else{
            zhusu.setText("");
        }

        if((isEmpty(mSpots.getResraurantInfo())==true)){
            meishi.setText(mSpots.getResraurantInfo());
        }else{
            meishi.setText("");
        }

        if((isEmpty(mSpots.getName())==true)){
            nameTv.setText(mSpots.getName());
        }else{
            nameTv.setText("————");
        }

        if((isEmpty(mSpots.getAddress())==true)){
            addressTv.setText(mSpots.getAddress());
        }else{
            addressTv.setText("————");
        }

        if((isEmpty(mSpots.getOpentime())==true)){
            opentimeTv.setText(mSpots.getOpentime());
        }else{
            opentimeTv.setText("------");
        }

        if((isEmpty(mSpots.getBriefDesc())==true)){
            briefDesc.setText(mSpots.getBriefDesc());
        }else{
            briefDesc.setText("");
        }

        if((isEmpty(mSpots.getCover())==true)){
            Glide.with(getApplicationContext())
                    .load(mSpots.getCover())
                    .into(fmUrlTv);
        }else{
            Glide.with(getApplicationContext())
                    .load(R.drawable.login1)
                    .into(fmUrlTv);
        }

        PageRequest<Comment> request = new PageRequest<>();
        Comment comment1 = new Comment();
        comment1.setTravelsId(mSpots.getId());
        request.setData(comment1);
        request.setPageNum(1);
        request.setPageSize(10);
        //获取数据
        commentSource = getCommentList(request);
        //加载页面
        loadingComment();
    }

    private TravelsDetailActivity.LoadWebListener mWebListener;

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
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }


    /**
     * 页面加载的回调
     */
    public interface LoadWebListener {
        void onLoadFinished();
    }

    /** 计算高度 */
    private void setListViewAutoHeight(ListView lv) {
        if (lv == null || lv.getCount() <= 0) return;
        int totalHeight = 0;
        for (int i = 0; i < lv.getCount(); i++) {
            View view = lv.getAdapter().getView(i, null, lv);
            view.measure(0, 0);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = totalHeight + lv.getDividerHeight() * (lv.getCount() - 1) + lv.getPaddingTop() + lv.getPaddingBottom();
        lv.setLayoutParams(params);
    }

    /*@OnClick(R.id.ydSpotsBtn)
    void goYdSpotsForm(View view){

        Intent intent=new Intent(this, YdActivity.class);
        intent.putExtra("spotsEntity", mSpots);
        startActivity(intent);
    }*/

    public static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return false;
        }else {
            return true;
        }
    }

    //为listview动态设置高度（有多少条目就显示多少条目）
    public void setListViewHeight(ListView listView) {
        //获取listView的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        //listAdapter.getCount()返回数据项的数目
        for (int i = 0,len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() *  (listAdapter .getCount() - 1))+108;
//        if(params.height > ScreenUtils.dip2px(this,2500)) {
//            params.height = ScreenUtils.dip2px(this,2500);
//        }
        listView.setLayoutParams(params);
    }





}