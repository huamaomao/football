package com.vxfc.shenxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.domian.PlayerRankingVo;
import com.vxfc.shenxin.util.Util;

import java.util.List;

/**
 * Created by Hua on 2014/8/7.
 */
public class ListPlayerRankingAdapater extends BaseAdapter {
    private List<PlayerRankingVo> data;
    private Context context;
    private int type=0;
    public ListPlayerRankingAdapater(Context context, List<PlayerRankingVo> data){
        this.context=context;
        this.data=data;
    }

    public void setType(int type) {
        this.type = type;
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
            convertView=LayoutInflater.from(context).inflate(R.layout.list_item_player_ranking,null);
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
        if (type<3){
            holder.tv_item_3.setVisibility(View.VISIBLE);
        }else {
            holder.tv_item_3.setVisibility(View.GONE);
        }
        PlayerRankingVo vo=data.get(position);
        holder.tv_item_0.setText(String.valueOf(position+1));
        holder.tv_item_1.setText(vo.getPlayerName());
        holder.tv_item_4.setVisibility(View.GONE);
        switch (type){
            case 0:
                holder.tv_item_2.setText(vo.getTotalScoringAtt());
                holder.tv_item_3.setText(Util.initTextValue(vo.getAttHdTotal()));
                break;
            case 1:
                holder.tv_item_2.setText(vo.getGoals());
                holder.tv_item_3.setText(Util.initTextValue(vo.getAttPenGoal()));
                holder.tv_item_4.setVisibility(View.VISIBLE);
                holder.tv_item_4.setText(Util.initTextValue(vo.getOwnGoals()));
                break;
            case 2:
                holder.tv_item_2.setText(vo.getYellowCard());
                holder.tv_item_3.setText(Util.initTextValue(vo.getRedCard()));
                break;
            case 3:
                holder.tv_item_2.setText(vo.getTotalPass());
                break;
            case 4:
                holder.tv_item_2.setText(vo.getGoalAssist());
                break;
            case 5:
                holder.tv_item_2.setText(vo.getTotalCross());
                break;
            case 6:
                holder.tv_item_2.setText(vo.getTotalTackle());
                break;
            case 7:
                holder.tv_item_2.setText(vo.getTotalClearance());
                break;
            case 8:
                holder.tv_item_2.setText(vo.getFouls());
                break;
            case 9:
                holder.tv_item_2.setText(vo.getWasFouled());
                break;
            case 10:
                holder.tv_item_2.setText(vo.getDivingSave());
                break;
        }
        return convertView;
    }

   static class ViewHolder{
        TextView tv_item_0,tv_item_1,tv_item_2,tv_item_3,tv_item_4;
    }

}
