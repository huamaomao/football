package com.weixuan.shenxin.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.entity.News;
import com.weixuan.shenxin.util.UrlApi;
import com.weixuan.shenxin.util.Util;
import java.util.List;

/**
 * Created by Hua on 2014/8/7.
 */
public class ListNewsAdapater extends BaseAdapter {
    private List<News> data;
    private Context context;
    private DisplayImageOptions options;
    public ListNewsAdapater(Context context, List<News> data){
        this.context=context;
        this.data=data;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.team_news)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
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
            convertView=LayoutInflater.from(context).inflate(R.layout.list_item_news,null);
            holder=new ViewHolder();
            holder.tv_item_0=(TextView)convertView.findViewById(R.id.tv_item_0);
            holder.tv_item_1=(TextView)convertView.findViewById(R.id.tv_item_1);
            holder.tv_item_2=(TextView)convertView.findViewById(R.id.tv_item_2);
            holder.iv_item_0=(ImageView)convertView.findViewById(R.id.iv_item_0);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        News news=data.get(position);
        holder.tv_item_0.setText(news.getTitle());
        StringBuilder builder=new StringBuilder("来源:");
        builder.append(Util.initTextEmpty(news.getFromLocation()));
        holder.tv_item_1.setText(builder.toString());
        holder.tv_item_2.setText(Util.initTextEmpty(news.getCreateTime()));

        ImageLoader.getInstance().displayImage(UrlApi.imageUrl,news.getThumbnailPath(), holder.iv_item_0,R.drawable.team_news);
       // holder.iv_item_0.setImageResource(R.drawable.team_news);
        return convertView;
    }

   static class ViewHolder{
        TextView tv_item_0,tv_item_1,tv_item_2;
        ImageView iv_item_0;
    }

}
