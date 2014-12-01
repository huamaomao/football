package com.weixuan.shenxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.entity.FistPlayerVo;
import com.weixuan.shenxin.util.Team;

import java.util.List;

/**
 * Created by Hua on 2014/8/7.
 */
public class ListPlayerADataAdapater extends BaseAdapter {
    private List<FistPlayerVo> data;
    private Context context;
    private LayoutInflater inflater;
    public ListPlayerADataAdapater(Context context, List<FistPlayerVo> data){
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
        builder.append(Team.getPlayerSubstitute(vo.getIsFirst()));
        holder.tv_item_2.setText(builder.toString());

        return convertView;
    }

   static class ViewHolder{
        TextView tv_item_0,tv_item_1,tv_item_2,tv_item_3;
        View iv_item_0,iv_item_1,iv_item_2;
    }

}
