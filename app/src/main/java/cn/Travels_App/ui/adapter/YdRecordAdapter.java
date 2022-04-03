package cn.Travels_App.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.Travels_App.R;
import cn.Travels_App.model.entity.Order;
import cn.Travels_App.persenter.YdRecordPerenter;


/**
 * 描述: SpAdapter
 */
public class YdRecordAdapter extends ArrayAdapter<Order> {
    //资源id
    private int resourceId;

    private List<Order> mYdList;

    private YdRecordPerenter mYdRecordPerenter;



    // 适配器的构造函数，把要适配的数据传入这里
    public YdRecordAdapter(Context context, int textViewResourceId, List<Order> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
        mYdList = objects;
       
    }
    public YdRecordAdapter(Context context, int textViewResourceId, List<Order> objects, YdRecordPerenter ydRecordPerenter){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
        mYdList = objects;
        mYdRecordPerenter = ydRecordPerenter;
    }

    // convertView 参数用于将之前加载好的布局进行缓存
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Order ydEntity=getItem(position); //获取当前项的Words实例

        // 加个判断，以免ListView每次滚动时都要重新加载布局，以提高运行效率
        View view;
        ViewHolder viewHolder;
        if (convertView==null){

            // 避免ListView每次滚动时都要重新加载布局，以提高运行效率
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

            // 避免每次调用getView()时都要重新获取控件实例
            viewHolder=new ViewHolder();
            viewHolder.myyd_spotsnameTv=view.findViewById(R.id.myyd_spotsnameTv);
            viewHolder.myyd_orderNumTv=view.findViewById(R.id.myyd_orderNumTv);
            viewHolder.myyd_totalpriceTv=view.findViewById(R.id.myyd_totalpriceTv);
            viewHolder.myyd_orderStatusTv = view.findViewById(R.id.myyd_orderStatusTv);
            viewHolder.yd_payBtn = view.findViewById(R.id.yd_payBtn);

            view.setTag(viewHolder);
        } else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }


        viewHolder.myyd_spotsnameTv.setText("景区名:"+ydEntity.getSpotsname());
        viewHolder.myyd_orderNumTv.setText("订单号:"+ydEntity.getOrdernum());
        viewHolder.myyd_totalpriceTv.setText("合计:"+ydEntity.getTotalprice()+"元");
        if(ydEntity.getOrderstatus() == 1L){
            viewHolder.myyd_orderStatusTv.setText("状态:1-已付款");
            viewHolder.yd_payBtn.setVisibility(View.GONE);
        }else{
            viewHolder.myyd_orderStatusTv.setText("状态:0-未付款");

        }

        viewHolder.yd_payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 去付款..
                mYdRecordPerenter.payMyOrder(ydEntity.getId());
            }
        });

        return view;
    }

    // 定义一个内部类，用于对控件的实例进行缓存
    class ViewHolder{

        TextView myyd_spotsnameTv,myyd_orderNumTv,myyd_totalpriceTv,myyd_orderStatusTv;
        Button yd_payBtn;
    }
}



