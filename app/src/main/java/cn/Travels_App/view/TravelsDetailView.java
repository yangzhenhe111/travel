package cn.Travels_App.view;


import java.util.List;

import cn.Travels_App.base.BaseView;
import cn.Travels_App.model.entity.Comment;
import cn.Travels_App.model.entity.CommentResp;

public interface TravelsDetailView extends BaseView {

    void onSuccess(String msg);
    /*void CommentData(List<CommentResp> commentsList);*/

}
