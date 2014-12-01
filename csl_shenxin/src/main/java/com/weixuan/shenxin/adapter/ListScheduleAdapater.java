package com.weixuan.shenxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.entity.RecentGameTeam;
import com.weixuan.shenxin.util.Team;

import java.util.List;

/**
 * Created by Hua on 2014/8/7.
 */
public class ListScheduleAdapater extends BaseAdapter {
    private List<RecentGameTeam> data;
    private Context context;

    public ListScheduleAdapater(Context context,List<RecentGameTeam> data){
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
            convertView=LayoutInflater.from(context).inflate(R.layout.list_item_schedule,null);
            holder=new ViewHolder();
            holder.tv_item_0=(TextView)convertView.findViewById(R.id.tv_item_0);
            holder.tv_item_1=(TextView)convertView.findViewById(R.id.tv_item_1);
            holder.tv_item_2=(TextView)convertView.findViewById(R.id.tv_item_2);
            holder.tv_item_3=(TextView)convertView.findViewById(R.id.tv_item_3);
            holder.tv_item_4=(TextView)convertView.findViewById(R.id.tv_item_4);
            holder.view_home=convertView.findViewById(R.id.view_home);
            holder.view_visiting=convertView.findViewById(R.id.view_visiting);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        RecentGameTeam gameTeam=data.get(position);
        StringBuilder builder=new StringBuilder();
        builder.append(gameTeam.getRound());
        builder.append(" ");
        builder.append(Team.getPlayStatus(gameTeam.getStatus()));
        holder.tv_item_0.setText(builder.toString());
        holder.tv_item_1.setText(gameTeam.getDate());
        holder.tv_item_2.setText(gameTeam.getTeamAName());
        holder.tv_item_3.setText(gameTeam.getScore());
        holder.tv_item_4.setText(gameTeam.getTeamBName());
        //球队icon
        //holder.tv_item_2.setBackgroundResource(Team.getTeamIconT(gameTeam.getTeamAId()));
        //holder.tv_item_4.setBackgroundResource(Team.getTeamIconT(gameTeam.getTeamBId()));
        //holder.view_home.setVisibility(View.GONE);
        //holder.view_visiting.setVisibility(View.GONE);
        /*convertView.setBackgroundColor(context.getResources().getColor(R.color.shenbg));
        holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.white));
        holder.tv_item_1.setTextColor(context.getResources().getColor(R.color.white));
        holder.tv_item_2.setTextColor(context.getResources().getColor(R.color.white));
        holder.tv_item_3.setTextColor(context.getResources().getColor(R.color.white));
        holder.tv_item_4.setTextColor(context.getResources().getColor(R.color.white));
        if (Dict.STATUS_N.equals(gameTeam.getStatus())){

        }else if (!Dict.STATUS_N.equals(gameTeam.getStatus())||!Dict.STATUS_P.equals(gameTeam.getStatus())||!Dict.STATUS_F.equals(gameTeam.getStatus())){
            convertView.setBackgroundColor(context.getResources().getColor(R.color.team_title_fc));
        }else {
            holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.white_0));
            holder.tv_item_1.setTextColor(context.getResources().getColor(R.color.white_0));
            holder.tv_item_2.setTextColor(context.getResources().getColor(R.color.white_0));
            holder.tv_item_3.setTextColor(context.getResources().getColor(R.color.white_0));
            holder.tv_item_4.setTextColor(context.getResources().getColor(R.color.white_0));
        }*/


        return convertView;
    }

   static class ViewHolder{
        TextView tv_item_0,tv_item_1,tv_item_2,tv_item_3,tv_item_4;
        View view_visiting,view_home;
    }

}
