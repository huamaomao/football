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

package com.vxfc.shenxin.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.domian.Player;
import com.vxfc.shenxin.util.RequestUtil;

import java.util.List;

public class TeamFancyCoverFlowAdapter extends BaseAdapter {
    private List<Player> data;
    private DisplayImageOptions options;
    public TeamFancyCoverFlowAdapter(List<Player> data) {
        this.data=data;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.zhenrong_moren)
                .showImageOnFail(R.drawable.zhenrong_moren)
                .showImageForEmptyUri(R.drawable.zhenrong_moren)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
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
           Player player=data.get(i);
           imageView.setTag(RequestUtil.getUrl(player.getBigPhoto()));
           ImageLoader.getInstance().displayImage(RequestUtil.getUrl(RequestUtil.getUrl(player.getBigPhoto())),imageView, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageView;
    }

}
