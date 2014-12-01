package com.weixuan.shenxin.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.entity.FistPlayerVo;
import com.weixuan.shenxin.util.Dict;
import com.weixuan.shenxin.util.Team;

import java.util.List;

/**
 * Created by Hua on 2014/8/7.
 */
public class ListLiveStartingAdapater extends BaseAdapter {
    private List<FistPlayerVo> data;
    private Context context;
    private LayoutInflater inflater;
    public ListLiveStartingAdapater(Context context, List<FistPlayerVo> data){
        this.context=context;
        this.data=data;
        this.inflater=LayoutInflater.from(context);
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
            convertView=inflater.inflate(R.layout.list_item_live_starting, null);
            holder=new ViewHolder();
            holder.tv_item_0=(TextView)convertView.findViewById(R.id.tv_item_0);
            holder.tv_item_1=(TextView)convertView.findViewById(R.id.tv_item_1);
            holder.tv_item_2=(TextView)convertView.findViewById(R.id.tv_item_2);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        FistPlayerVo vo=data.get(position);
        holder.tv_item_0.setText(vo.getPlayerNo());
        holder.tv_item_1.setText(Team.getPlayerLocation(vo.getBloc()));
        StringBuilder builder=new StringBuilder(vo.getPlayerName());
        holder.tv_item_2.setText(builder.toString());
        if (Dict.FISRT_Y.equals(vo.getIsFirst())){
            convertView.setBackgroundResource(R.drawable.shen_bg_1);
            holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.white));
            holder.tv_item_1.setTextColor(context.getResources().getColor(R.color.white));
            holder.tv_item_2.setTextColor(context.getResources().getColor(R.color.white));
        }else {
            convertView.setBackgroundResource(R.drawable.shen_bg_0);
            holder.tv_item_0.setTextColor(context.getResources().getColor(R.color.white_0));
            holder.tv_item_1.setTextColor(context.getResources().getColor(R.color.white_0));
            holder.tv_item_2.setTextColor(context.getResources().getColor(R.color.white_0));
        }

        LinearLayout layout= (LinearLayout) convertView;
        int count=layout.getChildCount();
        if(count>=3){
            layout.removeViews(3,count-3);
        }
        if (vo.getItems()!=null){
            for (FistPlayerVo.Item item:vo.getItems()) {
                layout.addView(creatGoalsView(item.getType()));
                layout.addView(creatTimeView(item.getTime()));
            }
        }
        return convertView;
    }

   static class ViewHolder{
        TextView tv_item_0,tv_item_1,tv_item_2;
    }

    /*****
     * 1黄 2红 3进球 4上场球员 5下场球员
     * @param type
     * @return
     */
    private View creatGoalsView(String type){
        final View view=new View(context);
        LinearLayout.LayoutParams params=null;
        switch (type){
            case "1":
                view.setBackgroundResource(R.drawable.icon_football);
                view.setBackgroundColor(context.getResources().getColor(R.color.yellow));
                params=new LinearLayout.LayoutParams(25,40);
                break;
            case "2":
                view.setBackgroundColor(context.getResources().getColor(R.color.red));
                params=new LinearLayout.LayoutParams(25,40);
                break;
            case "3":
                view.setBackgroundResource(R.drawable.icon_football);
                params=new LinearLayout.LayoutParams(40,40);
                break;
            case "4":
                view.setBackgroundResource(R.drawable.icon_up_arrow);
                params=new LinearLayout.LayoutParams(10,40);
                break;
            case "5":
                view.setBackgroundResource(R.drawable.icon_down_arrow);
                params=new LinearLayout.LayoutParams(10,40);
                break;
        }
        params.gravity= Gravity.CENTER_VERTICAL;
        view.setLayoutParams(params);
        return view;
    }

    private TextView creatTimeView(String time){
        final TextView view=new TextView(context);
        view.setText(time);
        view.setTextColor(Color.WHITE);
        view.setTextSize(10);
        LinearLayout.LayoutParams  params=new LinearLayout.LayoutParams(40,40);
        params.gravity= Gravity.TOP;
        params.leftMargin=10;
        params.rightMargin=10;
        view.setLayoutParams(params);
        return view;
    }

}
