package com.vxfc.shenxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.entity.Standings;
import com.vxfc.shenxin.util.Team;

import java.util.List;

/**
 * Created by Hua on 2014/8/7.
 */
public class ListScoreboardAdapater extends BaseAdapter {
    private List<Standings> data;
    private Context context;

    public ListScoreboardAdapater(Context context, List<Standings> data){
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
            convertView=LayoutInflater.from(context).inflate(R.layout.list_item_scoreboard, null);
            holder=new ViewHolder();
            holder.tv_item_0=(TextView)convertView.findViewById(R.id.tv_item_0);
            holder.tv_item_1=(TextView)convertView.findViewById(R.id.tv_item_1);
            holder.tv_item_2=(TextView)convertView.findViewById(R.id.tv_item_2);
            holder.tv_item_3=(TextView)convertView.findViewById(R.id.tv_item_3);
            holder.tv_item_4=(TextView)convertView.findViewById(R.id.tv_item_4);
            holder.tv_item_5=(TextView)convertView.findViewById(R.id.tv_item_5);
            holder.tv_item_6=(TextView)convertView.findViewById(R.id.tv_item_6);
            holder.tv_item_7=(TextView)convertView.findViewById(R.id.tv_item_7);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        Standings standings=data.get(position);

        holder.tv_item_0.setText(standings.getNo());
        if (position<3){
            holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.red));
        }else if (position>=14){
            holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.yellow));
        }else {
            holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.white));
        }
        holder.tv_item_1.setText(standings.getTeamName());
        holder.tv_item_2.setText(standings.getSession());
        holder.tv_item_3.setText(standings.getWin());
        holder.tv_item_4.setText(standings.getDraw());
        holder.tv_item_5.setText(standings.getLose());
        holder.tv_item_6.setText(standings.getJsq());
        holder.tv_item_7.setText(standings.getScore());
        holder.tv_item_1.setBackgroundResource(Team.getTeamIconT(standings.getTeamId()));
        return convertView;
    }

   static class ViewHolder{
        TextView tv_item_0,tv_item_1,tv_item_2,tv_item_3,tv_item_4,tv_item_5,tv_item_6,tv_item_7;
    }

}
