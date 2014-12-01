package com.weixuan.shenxin.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.util.Util;
import com.weixuan.shenxin.view.SpinnerView;
/****
 * 新闻/公告
 *
 */
public class ClubFragment extends BaseFragment{
    private RadioGroup rgGroupM;
    SpinnerView spinner_round;

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_club);
	}

    /****
     * 初始化
     * @param view
     * @param inflater
     */
	protected void initView(View view, LayoutInflater inflater) {
        this.rgGroupM=(RadioGroup)view.findViewById(R.id.rg_group_m);
        rgGroupM.check(R.id.rbtn_left_menu_0);
        Util.turnToFragment(getChildFragmentManager(), ClubIntroductionFragment.class, null, R.id.fl_m_content);
        rgGroupM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtn_left_menu_0:
                        Util.turnToFragment(getChildFragmentManager(),ClubIntroductionFragment.class,null,R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_1:
                        Util.turnToFragment(getChildFragmentManager(),ClubTeamHistoryFragment.class,null,R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_2:
                        Util.turnToFragment(getChildFragmentManager(),ClubTeamCultureFragment.class,null,R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_3:
                        Util.turnToFragment(getChildFragmentManager(),ClubSocerbaseFragment.class,null,R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_4:
                        Util.turnToFragment(getChildFragmentManager(),ClubBusinessFragment.class,null,R.id.fl_m_content);
                        break;
                }
            }
        });
    }

}
