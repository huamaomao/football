package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.model.RecentGameTeam;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.Util;

/****
 * 赛事直播
 *
 */
public class LiveMainFragment extends BaseFragment{

    private RadioGroup rgGroupM;
    public RecentGameTeam team;
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_live_main);
        team=(RecentGameTeam)getArguments().getSerializable(Dict.SERIALIZABLE);

    }

    protected void initView(View view, LayoutInflater inflater) {
        this.rgGroupM = (RadioGroup) view.findViewById(R.id.rg_group_m);
        rgGroupM.check(R.id.rbtn_left_menu_0);
        Util.turnToFragment(getChildFragmentManager(), LiveStartingFragment.class, null, R.id.fl_m_content);
        rgGroupM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_left_menu_0:
                        Util.turnToFragment(getChildFragmentManager(), LiveStartingFragment.class, null, R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_1:
                        Util.turnToFragment(getChildFragmentManager(), LiveLanqiuFragment.class, null, R.id.fl_m_content);
                    break;
                    case R.id.rbtn_left_menu_2:
                        Util.turnToFragment(getChildFragmentManager(), LiveLineupFragment.class, null, R.id.fl_m_content);
                        break;
                }
            }
        });

    }

}
