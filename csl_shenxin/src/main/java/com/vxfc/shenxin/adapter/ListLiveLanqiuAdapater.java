package com.vxfc.shenxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.entity.TeamDataVo;
import com.vxfc.shenxin.util.Util;

import java.util.List;

/**
 * Created by Hua on 2014/8/7.
 */
public class ListLiveLanqiuAdapater extends BaseAdapter {
    private List<TeamDataVo> data;
    private Context context;

    public ListLiveLanqiuAdapater(Context context, List<TeamDataVo> data){
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
            convertView=LayoutInflater.from(context).inflate(R.layout.list_item_data, null);
            holder=new ViewHolder();
            holder.tv_item_0=(TextView)convertView.findViewById(R.id.tv_item_0);
            holder.tv_item_1=(TextView)convertView.findViewById(R.id.tv_item_1);
            holder.tv_item_2=(TextView)convertView.findViewById(R.id.tv_item_2);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        TeamDataVo vo=data.get(position);
        holder.tv_item_0.setText(Util.initTextValue(vo.getTeamDataA()));
        holder.tv_item_1.setText(Util.initTextValue(vo.getDataName()));
        holder.tv_item_2.setText(Util.initTextValue(vo.getTeamDataB()));
        return convertView;
    }

   static class ViewHolder{
        TextView tv_item_0,tv_item_1,tv_item_2;
    }

}
