package com.vxfc.shenxin.adapter;

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
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.model.News;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.UrlApi;
import com.vxfc.shenxin.util.Util;
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
                .showImageOnFail(R.drawable.team_news)
                .showImageForEmptyUri(R.drawable.team_news)
                .cacheInMemory(true)
                .cacheOnDisk(true)
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
        holder.tv_item_0.setText(news.title);
        StringBuilder builder=new StringBuilder("来源:");
        builder.append(Util.initTextEmpty(news.fromLocation));
        holder.tv_item_1.setText(builder.toString());
        holder.tv_item_2.setText(Util.initTextEmpty(news.time));
        holder.iv_item_0.setTag(RequestUtil.getUrl(news.thumbnailPath));
        ImageLoader.getInstance().displayImage(RequestUtil.getUrl(news.thumbnailPath), holder.iv_item_0,options);
        return convertView;
    }

   static class ViewHolder{
        TextView tv_item_0,tv_item_1,tv_item_2;
        ImageView iv_item_0;
    }

}
