package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.util.Util;

/****
 * 新闻/公告
 *
 */
public class TeamMainFragment extends BaseFragment{

    private RadioGroup rgGroupM;
    private TextView title;
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_a_team);
	}
    /****
     * 初始化
     * @param view
     * @param inflater
     */
	protected void initView(View view, LayoutInflater inflater) {
        title= (TextView)view.findViewById(R.id.tv_title);
        this.rgGroupM=(RadioGroup)view.findViewById(R.id.rg_group_m);
        rgGroupM.check(R.id.rbtn_left_menu_0);
        Util.turnToFragment(getChildFragmentManager(), TeamLineupFragment.class, null, R.id.fl_m_content);
        rgGroupM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtn_left_menu_0:
                        Util.turnToFragment(getChildFragmentManager(), TeamLineupFragment.class, null, R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_1:
                        Util.turnToFragment(getChildFragmentManager(), TeamReservesFragment.class, null, R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_2:
                        Util.turnToFragment(getChildFragmentManager(), TeamCoachingFragment.class, null, R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_3:
                        Util.turnToFragment(getChildFragmentManager(), TeamPlayerRankingFragment.class, null, R.id.fl_m_content);
                        break;
                }
            }
        });

	}


}
