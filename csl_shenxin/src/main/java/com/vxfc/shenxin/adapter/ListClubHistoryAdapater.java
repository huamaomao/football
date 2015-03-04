package com.vxfc.shenxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.domian.PlayerListVo;
import com.vxfc.shenxin.util.Team;
import com.vxfc.shenxin.util.Util;

import java.util.List;

/**
 * Created by Hua on 2014/8/7.
 */
public class ListClubHistoryAdapater extends BaseAdapter {
    private List<PlayerListVo> data;
    private Context context;

    public ListClubHistoryAdapater(Context context,List<PlayerListVo> data){
        this.context=context;
        this.data=data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (null==convertView){
            convertView=LayoutInflater.from(context).inflate(R.layout.list_item_history,null);
            holder=new ViewHolder();
            holder.tv_item_0=(TextView)convertView.findViewById(R.id.tv_item_0);
            holder.tv_item_1=(TextView)convertView.findViewById(R.id.tv_item_1);
            holder.tv_item_2=(TextView)convertView.findViewById(R.id.tv_item_2);
            holder.tv_item_3=(TextView)convertView.findViewById(R.id.tv_item_3);
            holder.tv_item_4=(TextView)convertView.findViewById(R.id.tv_item_4);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        PlayerListVo vo=data.get(position);
        holder.tv_item_0.setText(vo.getNumber());
        holder.tv_item_1.setText(vo.getCname());
        holder.tv_item_2.setText(Team.getPlayerLocation(vo.getPosition()));
        holder.tv_item_3.setText(Util.initTextValue(vo.getSession()));
        holder.tv_item_4.setText(Util.initTextValue(vo.getGoals()));
        return convertView;
    }

   static class ViewHolder{
        TextView tv_item_0,tv_item_1,tv_item_2,tv_item_3,tv_item_4;
    }

}
