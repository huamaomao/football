package com.vxfc.shenxin.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.util.Util;

/****
 * 赛事分析
 *
 */
public class MachanakysisFragment extends BaseFragment{

    private RadioGroup rgGroupM;
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_matchanalysis);
	}

	protected void initView(View view, LayoutInflater inflater) {
       this.rgGroupM=(RadioGroup)view.findViewById(R.id.rg_group_m);
        rgGroupM.check(R.id.rbtn_left_menu_0);
        Util.turnToFragment(getChildFragmentManager(),MVsFragment.class,null,R.id.fl_m_content);
        rgGroupM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtn_left_menu_0:
                        Util.turnToFragment(getChildFragmentManager(),MVsFragment.class,null,R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_1:
                        Util.turnToFragment(getChildFragmentManager(),MPowerFragment.class,null,R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_2:
                        Util.turnToFragment(getChildFragmentManager(),MRecentRecordFragment.class,null,R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_3:
                        Util.turnToFragment(getChildFragmentManager(),MScoreboardFragment.class,null,R.id.fl_m_content);
                        break;

                }
            }
        });

	}


}
