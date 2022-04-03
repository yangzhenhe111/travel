package cn.Travels_App.view;


import java.util.List;
import java.util.Map;

import cn.Travels_App.base.BaseView;
import cn.Travels_App.model.entity.Order;

public interface YdRecordView extends BaseView {
    void onSuccessData(Map resultMap);
    public void loadData(List<Order> orderList);

    void onDoPayOrderResponse();
}
