package cn.Travels_App.view;

import java.util.List;

import cn.Travels_App.base.BaseView;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.model.entity.UserEntity;

public interface PersonalView extends BaseView {
    void loadData(UserEntity userEntity);
}
