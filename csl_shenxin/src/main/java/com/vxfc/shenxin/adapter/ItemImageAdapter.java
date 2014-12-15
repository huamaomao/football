package com.vxfc.shenxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.vxfc.shenxin.R;

/**
 * @function GalleryFlow适配器
 */
public class ItemImageAdapter extends BaseAdapter
{
    private Context mContext;

    //图片数组
    private int[] mImageIds ;

    public ItemImageAdapter(Context context, int[] imageIds)
    {
        mContext = context;
        mImageIds = imageIds;

    }
    


    @Override
    public int getCount()
    {
        return mImageIds.length;
    }
    
    @Override
    public Object getItem(int position)
    {
        return mImageIds[position];
    }
    
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_image,null);
        }
        ImageView imageView=(ImageView)convertView;
        imageView.setImageResource(mImageIds[position]);
        return imageView;
    }
}