package cn.Travels_App.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.Travels_App.R;
import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.ui.activity.TravelsDetailActivity;


/**
 * 描述: CasesAdapter
 */
public class TravelsAdapter extends ArrayAdapter<Travels> {
    //资源id
    private int resourceId;

    private List<Travels> mSpotsList;



    // 适配器的构造函数，把要适配的数据传入这里
    public TravelsAdapter(Context context, int textViewResourceId, List<Travels> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
        mSpotsList = objects;
       
    }

    // convertView 参数用于将之前加载好的布局进行缓存
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Travels travelEntity=getItem(position); //获取当前项的Words实例
        /*System.out.println(travelEntity.getId());*/
        // 加个判断，以免ListView每次滚动时都要重新加载布局，以提高运行效率
        View view;
        ViewHolder viewHolder;
        if (convertView==null){

            // 避免ListView每次滚动时都要重新加载布局，以提高运行效率
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

            // 避免每次调用getView()时都要重新获取控件实例
            viewHolder=new ViewHolder();
            viewHolder.spotsfmIv=view.findViewById(R.id.spotsfmIv);
            viewHolder.creatorName=view.findViewById(R.id.spotsnameTv);
            viewHolder.spotsnameTv=view.findViewById(R.id.biaoti);
            /*viewHolder.ydBtn=view.findViewById(R.id.ydBtn);

            viewHolder.ydBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(),
                            SpotsDetailActivity.class);
                    intent.putExtra("spotsEntity", spotsEntity);
                    getContext().startActivity(intent);
                }
            });*/
            // 点击进入详情页）
            viewHolder.spotsfmIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(),
                            TravelsDetailActivity.class);
                    intent.putExtra("travelsId", travelEntity.getId());
                    getContext().startActivity(intent);
                }
            });

            viewHolder.creatorName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(),
                            TravelsDetailActivity.class);
                    intent.putExtra("travelsId", travelEntity.getId());
                    getContext().startActivity(intent);
                }
            });

            viewHolder.spotsnameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(),
                            TravelsDetailActivity.class);
                    intent.putExtra("travelsId", travelEntity.getId());
                    getContext().startActivity(intent);
                }
            });

            view.setTag(viewHolder);
        } else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }


        if((isEmpty(travelEntity.getName())==true)){
            viewHolder.spotsnameTv.setText(travelEntity.getName());
        }else{
            viewHolder.spotsnameTv.setText("---");
        }

        /*if((isEmpty(travelEntity.getName())==true)){
            viewHolder.spotsnameTv.setText(travelEntity.getName());
        }else{
            viewHolder.spotsnameTv.setText("---");
        }*/


        viewHolder.spotsnameTv.setText(travelEntity.getName());
        viewHolder.creatorName.setText(travelEntity.getCreatorName());

        Glide.with(getContext())
                .load(travelEntity.getCover())
                .into(viewHolder.spotsfmIv);

        return view;
    }

    // 定义一个内部类，用于对控件的实例进行缓存
    class ViewHolder{
        ImageView spotsfmIv;
        TextView spotsnameTv,creatorName;
    }

    public static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return false;
        }else {
            return true;
        }
    }
}



