package com.vxfc.shenxin.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
public class ViewPagerAdapter extends PagerAdapter {
    private List<View> viewList;
    private List<String> titleList;
    public ViewPagerAdapter(List<View> viewList, List<String> titleList){
        this.viewList=viewList;
        this.titleList=titleList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)   {
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(viewList.get(position), 0);
        return viewList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==o;
    }
}
