package cn.Travels_App.view;


import java.util.Map;

import cn.Travels_App.base.BaseView;
import cn.Travels_App.model.entity.UserEntity;

public interface RegView extends BaseView {
    //登录成功处理数据
    void onSuccessData(UserEntity userEntity);
}
