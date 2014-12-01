/*
 * Copyright 2013 David Schreiber
 *           2013 John Paul Nalog
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.weixuan.shenxin.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class TeamFancyCoverFlowAdapter extends BaseAdapter {
    private int[] images;

    public TeamFancyCoverFlowAdapter(int[] images) {
        this.images = images;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Integer getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = null;
        if (view != null) {
            imageView = (ImageView) view;
        } else {
            imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new Gallery.LayoutParams(240, 720));
        }
        try {
            imageView.setImageResource(this.getItem(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageView;
    }


    public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
        ImageView imageView = null;
        if (reuseableView != null) {
            imageView = (ImageView) reuseableView;
        } else {
            imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new Gallery.LayoutParams(240, 680));
        }
        try {
            imageView.setImageResource(this.getItem(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageView;
    }
}
