package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.domian.RecentGameTeam;

/****
 *文字直播
 *
 */
public class LiveDataFragment extends BaseFragment{
    private RecentGameTeam team;
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        LiveMainFragment  fragment=(LiveMainFragment) getParentFragment();
        team=fragment.team;
        setLayoutId(R.layout.f_live_data);
	}
	protected void initView(View view, LayoutInflater inflater) {


	}


}
