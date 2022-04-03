package cn.Travels_App.view;


import java.util.List;
import java.util.Map;

import cn.Travels_App.base.BaseView;
import cn.Travels_App.model.entity.CasesEntity;
import cn.Travels_App.model.entity.Hotel;

public interface YdView extends BaseView {

    void onSuccessData(Map resultMap);

    public void loadData(List<CasesEntity> casesEntityList);

   /* void loadHotelData(List<Hotel> hotels);*/
}
