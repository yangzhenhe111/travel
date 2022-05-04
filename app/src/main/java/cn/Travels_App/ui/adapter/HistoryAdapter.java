package cn.Travels_App.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.Travels_App.R;
import cn.Travels_App.model.dto.TravelsHistoryDTO;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.ui.activity.TravelsDetailActivity;

public class HistoryAdapter extends ArrayAdapter<TravelsHistoryDTO> {
    //资源id
    private int resourceId;

    private List<TravelsHistoryDTO> mSpotsList;


    // 适配器的构造函数，把要适配的数据传入这里
    public HistoryAdapter(Context context, int textViewResourceId, List<TravelsHistoryDTO> objects){
        super(context,textViewResourceId,objects);
        System.out.println("30");
        resourceId=textViewResourceId;
        mSpotsList = objects;

    }

    // convertView 参数用于将之前加载好的布局进行缓存
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TravelsHistoryDTO spotsItem=getItem(position); //获取当前项的Words实例

        // 加个判断，以免ListView每次滚动时都要重新加载布局，以提高运行效率
        View view;
        HistoryAdapter.ViewHolder viewHolder;
        if (convertView==null){

            // 避免ListView每次滚动时都要重新加载布局，以提高运行效率
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

            // 避免每次调用getView()时都要重新获取控件实例
            viewHolder=new HistoryAdapter.ViewHolder();
            viewHolder.tjspots_image=view.findViewById(R.id.tjspots_image);
            viewHolder.tjspots_nameTv=view.findViewById(R.id.tjspots_name);
            viewHolder.tjspots_createTimeTv=view.findViewById(R.id.tjspots_createTime);


            //进行景区详情
            viewHolder.tjspots_nameTv.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(),
                            TravelsDetailActivity.class);
                    intent.putExtra("travelId", spotsItem.getTravelsId());
                    getContext().startActivity(intent);
                }
            });
            viewHolder.tjspots_image.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(),
                            TravelsDetailActivity.class);
                    intent.putExtra("travelId", spotsItem.getTravelsId());
                    getContext().startActivity(intent);
                }
            });


            // 点击进入详情页）
            viewHolder.tjspots_createTimeTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(),
                            TravelsDetailActivity.class);
                    intent.putExtra("travelId", spotsItem.getTravelsId());
                    getContext().startActivity(intent);

                }
            });

            view.setTag(viewHolder);
        } else{
            view=convertView;
            viewHolder=(HistoryAdapter.ViewHolder) view.getTag();
        }


        if((isEmpty(spotsItem.getName())==true)){
            viewHolder.tjspots_nameTv.setText(spotsItem.getName());
        }else{
            viewHolder.tjspots_nameTv.setText("---");
        }
        if((isEmpty(spotsItem.getPublishDate())==true)){
            String[] str=spotsItem.getPublishDate().split("[T]");
            viewHolder.tjspots_createTimeTv.setText(str[0]);
        }else{
            viewHolder.tjspots_createTimeTv.setText("------");
        }

        if((isEmpty(spotsItem.getCover())==true)){
            Glide.with(getContext())
                    .load(spotsItem.getCover())
                    .into(viewHolder.tjspots_image);
        }else{
            Glide.with(getContext())
                    .load(R.drawable.touxaing)
                    .into(viewHolder.tjspots_image);
        }
        return view;
    }

    // 定义一个内部类，用于对控件的实例进行缓存
    class ViewHolder{
        ImageView tjspots_image;
        TextView tjspots_nameTv;
        TextView tjspots_createTimeTv;

    }
    public static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return false;
        }else {
            return true;
        }
    }
}
