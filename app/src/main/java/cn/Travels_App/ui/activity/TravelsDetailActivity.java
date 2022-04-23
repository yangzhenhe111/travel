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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.Travels_App.R;
import cn.Travels_App.base.BaseActivity;
import cn.Travels_App.model.entity.Comment;
import cn.Travels_App.model.entity.CommentBean;
import cn.Travels_App.model.entity.CommentDetailBean;
import cn.Travels_App.model.entity.ReplyDetailBean;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.persenter.TravelsDetailPersenter;
import cn.Travels_App.ui.adapter.CommentExpandAdapter;
import cn.Travels_App.utils.CommentExpandableListView;
import cn.Travels_App.view.TravelsDetailView;

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
    private String testJson = "{\n" +
            "\t\"code\": 1000,\n" +
            "\t\"message\": \"查看评论成功\",\n" +
            "\t\"data\": {\n" +
            "\t\t\"total\": 3,\n" +
            "\t\t\"list\": [{\n" +
            "\t\t\t\t\"id\": 42,\n" +
            "\t\t\t\t\"nickName\": \"程序猿\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"content\": \"时间是一切财富中最宝贵的财富。\",\n" +
            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\t\"replyTotal\": 1,\n" +
            "\t\t\t\t\"createDate\": \"三分钟前\",\n" +
            "\t\t\t\t\"replyList\": [{\n" +
            "\t\t\t\t\t\"nickName\": \"沐風\",\n" +
            "\t\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\t\"id\": 40,\n" +
            "\t\t\t\t\t\"commentId\": \"42\",\n" +
            "\t\t\t\t\t\"content\": \"时间总是在不经意中擦肩而过,不留一点痕迹.\",\n" +
            "\t\t\t\t\t\"status\": \"01\",\n" +
            "\t\t\t\t\t\"createDate\": \"一个小时前\"\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"id\": 41,\n" +
            "\t\t\t\t\"nickName\": \"设计狗\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"content\": \"这世界要是没有爱情，它在我们心中还会有什么意义！这就如一盏没有亮光的走马灯。\",\n" +
            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\t\"replyTotal\": 1,\n" +
            "\t\t\t\t\"createDate\": \"一天前\",\n" +
            "\t\t\t\t\"replyList\": [{\n" +
            "\t\t\t\t\t\"nickName\": \"沐風\",\n" +
            "\t\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\t\"commentId\": \"41\",\n" +
            "\t\t\t\t\t\"content\": \"时间总是在不经意中擦肩而过,不留一点痕迹.\",\n" +
            "\t\t\t\t\t\"status\": \"01\",\n" +
            "\t\t\t\t\t\"createDate\": \"三小时前\"\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"id\": 40,\n" +
            "\t\t\t\t\"nickName\": \"产品喵\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"content\": \"笨蛋自以为聪明，聪明人才知道自己是笨蛋。\",\n" +
            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\t\"replyTotal\": 0,\n" +
            "\t\t\t\t\"createDate\": \"三天前\",\n" +
            "\t\t\t\t\"replyList\": []\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}\n" +
            "}";




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
        expandableListView = (CommentExpandableListView) findViewById(R.id.detail_page_comment_container);
        commentsList = generateTestData();
        initExpandableListView(commentsList);
    }

    private void initExpandableListView(final List<CommentDetailBean> commentList){
        expandableListView.setGroupIndicator(null);
        //默认展开所有回复
        adapter = new CommentExpandAdapter(TravelsDetailActivity.this, commentList);
        expandableListView.setAdapter(adapter);
        for(int i = 0; i<commentList.size(); i++){
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);
                Log.e(TAG, "onGroupClick: 当前的评论id>>>"+commentList.get(groupPosition).getId());
//                if(isExpanded){
//                    expandableListView.collapseGroup(groupPosition);
//                }else {
//                    expandableListView.expandGroup(groupPosition, true);
//                }
                showReplyDialog(groupPosition);
                return true;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Toast.makeText(TravelsDetailActivity.this,"点击了回复",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //toast("展开第"+groupPosition+"个分组");

            }
        });

    }

    /**
     * by moos on 2018/04/20
     * func:生成测试数据
     * @return 评论数据
     */
    private List<CommentDetailBean> generateTestData(){
        Gson gson = new Gson();
        commentBean = gson.fromJson(testJson, CommentBean.class);
        List<CommentDetailBean> commentList = commentBean.getData().getList();
        return commentList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick
    public void onClick(View view) {
        if(view.getId() == R.id.detail_page_do_comment){

            showCommentDialog();
        }
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
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){

                    //commentOnWork(commentContent);
                    dialog.dismiss();
                    CommentDetailBean detailBean = new CommentDetailBean("小明", commentContent,"刚刚");
                    adapter.addTheCommentData(detailBean);
                    Toast.makeText(TravelsDetailActivity.this,"评论成功",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(TravelsDetailActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
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

    /**
     * by moos on 2018/04/20
     * func:弹出回复框
     */
    private void showReplyDialog(final int position){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint("回复 " + commentsList.get(position).getNickName() + " 的评论:");
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(replyContent)){

                    dialog.dismiss();
                    ReplyDetailBean detailBean = new ReplyDetailBean("小红",replyContent);
                    adapter.addTheReplyData(detailBean, position);
                    expandableListView.expandGroup(position);
                    Toast.makeText(TravelsDetailActivity.this,"回复成功",Toast.LENGTH_SHORT).show();
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






}