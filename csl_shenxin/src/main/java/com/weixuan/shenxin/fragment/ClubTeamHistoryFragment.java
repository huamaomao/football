package com.weixuan.shenxin.fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.*;
import android.widget.RadioGroup;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.adapter.SpinnerAdapter;
import com.weixuan.shenxin.ui.MainActivity;
import com.weixuan.shenxin.util.Util;

import java.util.HashMap;
import java.util.Map;

/****
 * 新闻/公告
 *
 */
public class ClubTeamHistoryFragment extends BaseFragment implements ActionBar.OnNavigationListener{
    private RadioGroup rgGroup;
    private ClubFragment  clubFragment;
    private SpinnerAdapter spinnerAdapter;
    /***当前下拉菜单指向  tab 列表***/
    int selection=0;
    Map<Integer,OnItemClickListener> listenerMap;
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_club_team_history);
        clubFragment=(ClubFragment)getParentFragment();
        listenerMap=new HashMap<Integer,OnItemClickListener>();
	}

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onResume() {
        super.onResume();
        actionSpinnerClub();
    }

    @Override
    public void onStop() {
        super.onStop();
        MainActivity activity=(MainActivity)getActivity();
        activity.restoreActionBar();
    }

    public void actionSpinnerClub(){
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        spinnerAdapter=new SpinnerAdapter(Util.getYearList(2012),getActivity().getLayoutInflater());
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);//导航模式必须设为NAVIGATION_MODE_LIST
        actionBar.setListNavigationCallbacks(spinnerAdapter,this);
    }

    @Override
    public boolean onNavigationItemSelected(int i, long l){
        if (listenerMap.get(selection)!=null){
            listenerMap.get(selection).onItemClick(i);
        }
        return false;
    }

    protected void initView(View view, LayoutInflater inflater) {
        this.rgGroup=(RadioGroup)view.findViewById(R.id.rg_group);
        rgGroup.check(R.id.rbtn_left_menu_0);
        MainActivity activity=(MainActivity)getActivity();
        activity.mNavigationDrawerFragment.setHasOptionsMenu(true);
       Util.turnToFragment(getChildFragmentManager(), ClubTeamHistoryAFragment.class, null, R.id.fl_m_content);
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtn_left_menu_0:
                        Util.turnToFragment(getChildFragmentManager(),ClubTeamHistoryAFragment.class, null, R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_1:
                        selection=1;
                        Util.turnToFragment(getChildFragmentManager(), ClubTeamHistoryBFragment.class, null, R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_2:
                       selection=2;
                        Util.turnToFragment(getChildFragmentManager(), ClubTeamHistoryCFragment.class, null, R.id.fl_m_content);
                        break;
                }
            }
        });
	}

    public interface OnItemClickListener{
        public void onItemClick(int index);
    }
    /**
     * 实现 ActionBar.OnNavigationListener接口
     */
    class DropDownListenser implements ActionBar.OnNavigationListener
    {
        /* 当选择下拉菜单项的时候，将Activity中的内容置换为对应的Fragment */
        public boolean onNavigationItemSelected(int itemPosition, long itemId)
        {
            return true;
        }
    }

    public void setSelectionItem(int index){
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setSelectedNavigationItem(index);
    }

    public String getSelectionValue(){
        ActionBar actionBar = getActivity().getActionBar();
       return spinnerAdapter.getItem(actionBar.getSelectedNavigationIndex()).toString();
    }
}
