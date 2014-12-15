package com.weixuan.shenxin.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.adapter.ItemImageAdapter;

/****
 * 商务
 *
 */
public class ClubBusinessFragment extends BaseFragment{
    private GridView gridview;

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_club_business);
	}

    /****
     * 初始化
     * @param view
     * @param inflater
     */
    protected void initView(View view, LayoutInflater inflater) {
        gridview=(GridView)view.findViewById(R.id.grid_view);
        int[] array={R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3,R.drawable.icon_4,
                R.drawable.icon_5,R.drawable.icon_6,R.drawable.icon_7,
                R.drawable.icon_8,R.drawable.icon_9,R.drawable.icon_10,
                R.drawable.icon_11,R.drawable.icon_12};
        ItemImageAdapter adapter=new ItemImageAdapter(getActivity(),array);
        gridview.setAdapter(adapter);

	}


}
