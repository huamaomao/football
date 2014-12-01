package com.weixuan.shenxin.fragment;
import android.os.Bundle;
import android.widget.RadioGroup;

/****
 * 新闻/公告
 *
 */
public class LeftMenuFragment{
    public RadioGroup rgGroup;
	public void onCreate(Bundle savedInstanceState) {

	}

	/*protected void initView(View view, LayoutInflater inflater) {
        this.rgGroup=(RadioGroup)view.findViewById(R.id.rg_group);
        final  MainActivity activity =(MainActivity)getActivity();
        activity.turnToFragment(getFragmentManager(), NewsMainFragment.class, null);
        rgGroup.check(R.id.rbtn_left_menu_1);
        rgGroup.findViewById(R.id.rbtn_left_menu_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsMainFragment fragment1= (NewsMainFragment)activity.turnToFragment(getFragmentManager(), NewsMainFragment.class, null);
                fragment1.setNewsTab(2);
                fragment1.setTitle(R.string.title_shouye);
            }
        });
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtn_left_menu_0:
                        NewsMainFragment fragment= (NewsMainFragment)activity.turnToFragment(getFragmentManager(), NewsMainFragment.class, null);
                        fragment.setNewsTab(1);
                        fragment.setTitle(R.string.title_matchanalysis);
                        break;
                    case R.id.rbtn_left_menu_1:
                        NewsMainFragment fragment1= (NewsMainFragment)activity.turnToFragment(getFragmentManager(), NewsMainFragment.class, null);
                        fragment1.setNewsTab(2);
                        fragment1.setTitle(R.string.title_shouye);
                        break;
                    case R.id.rbtn_left_menu_2:
                        activity.turnToFragment(getFragmentManager(),ScheduleFragment.class,null);
                        break;
                    case R.id.rbtn_left_menu_3:
                        activity.turnToFragment(getFragmentManager(),ClubFragment.class,null);
                        break;
                    case R.id.rbtn_left_menu_4:
                        activity.turnToFragment(getFragmentManager(),TeamMainFragment.class,null);
                        break;
                }
            }
        });
	}

    public void  setLiveMenu(){
        rgGroup.check(R.id.rbtn_left_menu_0);
        final   MainActivity activity =(MainActivity)getActivity();
        Bundle bundle=new Bundle();
        bundle.putSerializable(Dict.SERIALIZABLE,application.getGameTeam());
        NewsMainFragment fragment= (NewsMainFragment)activity.turnToFragment(getFragmentManager(), NewsMainFragment.class, bundle);
        fragment.setNewsTab(0);
        fragment.setTitle(R.string.title_first_list);
}*/



}
