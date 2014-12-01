package com.weixuan.shenxin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.weixuan.shenxin.R;

/**
 * Created by Hua on 2014/8/1.
 */
public class SpinnerAdapter extends BaseAdapter {

    private Object[] data;
    private LayoutInflater inflater;
    private boolean flag;
    private String model=null;
    public SpinnerAdapter(Object[] data,LayoutInflater inflater){
        this.data=data;
        this.inflater=inflater;
    }
    public SpinnerAdapter(Object[] data,LayoutInflater inflater,String model){
        this.data=data;
        this.inflater=inflater;
        this.model=model;
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
        if (null!=model){
            String str=data[position].toString();
           if (position==0){
               ((TextView)convertView).setText(str);
           }else {
               ((TextView)convertView).setText(String.format(model,str));
           }

        }else {
            ((TextView)convertView).setText(data[position].toString());
        }
        return convertView;
    }
}
