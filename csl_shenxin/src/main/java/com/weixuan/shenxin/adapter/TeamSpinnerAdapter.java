package com.weixuan.shenxin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.util.Team;

/**
 * Created by Hua on 2014/8/1.
 */
public class TeamSpinnerAdapter extends BaseAdapter {

    private Team[] data=Team.values();
    private LayoutInflater inflater;
    private boolean flag;
    private String model=null;
    public TeamSpinnerAdapter(LayoutInflater inflater){
        this.inflater=inflater;
    }
    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null==convertView){
            View  view=inflater.inflate(R.layout.spinner_item,null);
            convertView=view;
        }
       ((TextView)convertView).setText(data[position].name);
       return convertView;
    }
}
