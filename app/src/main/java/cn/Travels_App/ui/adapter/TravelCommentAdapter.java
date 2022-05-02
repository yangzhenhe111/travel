package cn.Travels_App.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;

import cn.Travels_App.R;
import cn.Travels_App.common.Constants;
import cn.Travels_App.model.dto.CommentRespDTO;
import cn.Travels_App.model.entity.Comment;
import cn.Travels_App.model.entity.ReplyDetailBean;
import cn.Travels_App.network.HttpResult;
import cn.Travels_App.ui.activity.TravelsDetailActivity;
import cn.Travels_App.utils.CommonUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TravelCommentAdapter extends BaseAdapter {


    Context myContext ;
    List<CommentRespDTO> sources ;
    int linearLayout;

    public TravelCommentAdapter(Context myContext, List<CommentRespDTO> sources, int linearLayout) {
        this.myContext = myContext;
        this.sources = sources;
        this.linearLayout = linearLayout;
    }

    @Override
    public int getCount() {
        return sources.size();
    }

    @Override
    public Object getItem(int position) {
        return sources.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.e("postion:",position+"");
        CommentRespDTO commentResp  = sources.get(position);
        convertView = LayoutInflater.from(myContext).inflate(linearLayout,parent,false);
        ImageView logo = convertView.findViewById(R.id.comment_item_logo);
        Glide.with(myContext)
                .load(commentResp.getHeadImg())
                .into(logo);

        TextView name = convertView.findViewById(R.id.comment_item_userName);
        name.setText(commentResp.getUsername());
        TextView date = convertView.findViewById(R.id.comment_item_time);
        date.setText(commentResp.getCreateTime());
        TextView content = convertView.findViewById(R.id.comment_item_content);
        content.setText(commentResp.getContent());
//        content.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showReplyDialog(position);
//            }
//        });
        CommentRespDTO parentComment = commentResp.getParentComment();
        LinearLayout parentCommentLayout = convertView.findViewById(R.id.parent_comment);
        TextView parentName = convertView.findViewById(R.id.reply_item_user);

        TextView parentContent = convertView.findViewById(R.id.reply_item_content);
        if (Objects.nonNull(parentComment)) {
            parentName.setText(parentComment.getUsername());
            parentContent.setText(parentComment.getContent());
        }else {
            parentCommentLayout.setVisibility(View.GONE);
        }

        return convertView;
    }


    /**
     * by moos on 2018/04/20
     * func:弹出回复框
     */
    private void showReplyDialog(final int position){
        BottomSheetDialog dialog = new BottomSheetDialog(myContext);
        View commentView = LayoutInflater.from(myContext).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint("回复 " + sources.get(position).getUsername() + " 的评论:");
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(replyContent)){

                    //提交回复信息
                    Comment commitInfo = new Comment();
                    commitInfo.setContent(replyContent);
                    commitInfo.setTravelsId(sources.get(position).getTravelsId());
                    commitInfo.setParentId(sources.get(position).getId());

                    if (submitData(commitInfo)){
                        dialog.dismiss();
                        Toast.makeText(myContext,"回复成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(myContext,"提交失败，请重试！",Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(myContext,"回复内容不能为空",Toast.LENGTH_SHORT).show();
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
                .addHeader("header-user", new Gson().toJson(CommonUtils.getLoginUser(myContext)))
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
}
