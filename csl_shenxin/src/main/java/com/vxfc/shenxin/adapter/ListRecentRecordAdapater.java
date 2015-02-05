package com.vxfc.shenxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.model.RecentGameTeam;
import com.vxfc.shenxin.util.Team;

import java.util.List;

/**
 * Created by Hua on 2014/8/7.
 */
public class ListRecentRecordAdapater extends BaseAdapter {
    private List<RecentGameTeam> data;
    private Context context;
    private String teamId;

    public ListRecentRecordAdapater(Context context, List<RecentGameTeam> data){
        this.context=context;
        this.data=data;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
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
            convertView=LayoutInflater.from(context).inflate(R.layout.list_item_vs,null);
            holder=new ViewHolder();
            holder.tv_item_0=(TextView)convertView.findViewById(R.id.tv_item_0);
            holder.tv_item_1=(TextView)convertView.findViewById(R.id.tv_item_1);
            holder.tv_item_2=(TextView)convertView.findViewById(R.id.tv_item_2);
            holder.tv_item_3=(TextView)convertView.findViewById(R.id.tv_item_3);
            holder.tv_item_4=(TextView)convertView.findViewById(R.id.tv_item_4);
            holder.tv_item_5=(TextView)convertView.findViewById(R.id.tv_item_5);
            holder.tv_item_6=(TextView)convertView.findViewById(R.id.tv_item_6);
            holder.view_home=convertView.findViewById(R.id.view_home);
            holder.view_visiting=convertView.findViewById(R.id.view_visiting);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.view_home.setVisibility(View.INVISIBLE);
        holder.view_visiting.setVisibility(View.INVISIBLE);
        RecentGameTeam gameTeam=data.get(position);

        holder.tv_item_0.setText(gameTeam.getEvents());
        holder.tv_item_1.setText(gameTeam.getSeason().toString());
        holder.tv_item_2.setText(gameTeam.getRound().toString());
        holder.tv_item_3.setText(gameTeam.getDate());
        holder.tv_item_4.setText(gameTeam.getTeamAName());
        holder.tv_item_5.setText(gameTeam.getScore());
        holder.tv_item_6.setText(gameTeam.getTeamBName());
        holder.tv_item_4.setBackgroundResource(Team.getTeamIconT(gameTeam.getTeamAId()));
        holder.tv_item_6.setBackgroundResource(Team.getTeamIconT(gameTeam.getTeamBId()));

        if (teamId.equals(gameTeam.getTeamAId())){
            holder.view_home.setVisibility(View.VISIBLE);
            if ("A".equals(gameTeam.getResult())){
                holder.view_home.setBackgroundResource(R.drawable.shape_round_red);
            }else if("B".equals(gameTeam.getResult())){
                holder.view_home.setBackgroundResource(R.drawable.shape_round_yellow);
            }else {
                holder.view_home.setBackgroundResource(R.drawable.shape_round_green);
            }
            if ("A".equals(gameTeam.getResult())){
                holder.tv_item_0.setText("胜");
                holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.red));
            }else if("B".equals(gameTeam.getResult())){
                holder.tv_item_0.setText("负");
                holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.yellow));
            }else {
                holder.tv_item_0.setText("平");
                holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.green));
            }
        }else {
            holder.view_visiting.setVisibility(View.VISIBLE);
            if ("A".equals(gameTeam.getResult())){
                holder.view_visiting.setBackgroundResource(R.drawable.shape_round_yellow);
            }else if("B".equals(gameTeam.getResult())){
                holder.view_visiting.setBackgroundResource(R.drawable.shape_round_red);
            }else {
                holder.view_visiting.setBackgroundResource(R.drawable.shape_round_green);
            }
            if ("A".equals(gameTeam.getResult())){
                holder.tv_item_0.setText("负");
                holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.yellow));
            }else if("B".equals(gameTeam.getResult())){
                holder.tv_item_0.setText("胜");
                holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.red));
            }else {
                holder.tv_item_0.setText("平");
                holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.green));
            }
        }

        return convertView;
    }

   static class ViewHolder{
        TextView tv_item_0,tv_item_1,tv_item_2,tv_item_3,tv_item_4,tv_item_5,tv_item_6;
        View view_visiting,view_home;
    }

}
