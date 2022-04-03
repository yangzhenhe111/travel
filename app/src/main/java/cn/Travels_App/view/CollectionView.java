package cn.Travels_App.view;


import java.util.List;

import cn.Travels_App.base.BaseView;
import cn.Travels_App.model.entity.DoctorEntity;

public interface CollectionView extends BaseView {
    void loadData(List<DoctorEntity> newsList);
}
