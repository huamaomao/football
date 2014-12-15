package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.TeamFancyCoverFlowAdapter;
import com.vxfc.shenxin.view.FormationView;

import java.util.List;
/****
 *
 *
 */
public class TeamLineupFragment extends BaseFragment{
    private RadioGroup rgGroup;
    private FancyCoverFlow fancyCoverFlow;
    private FormationView fl_view;
    private boolean flag=false;
   private PagerTabStrip  pts_title;
    private ViewPager viewPager;
    private List<View> pageList;
    private List<String> titleList;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_team_a);
    }

    /****
     * 初始化
     * @param view
     * @param inflater
     */
	protected void initView(View view, LayoutInflater inflater) {
        rgGroup=(RadioGroup)view.findViewById(R.id.rg_group);
        rgGroup.check(R.id.rbtn_left_menu_1);
       /* pts_title=(PagerTabStrip)view.findViewById(R.id.pts_title);
        pts_title.setTabIndicatorColorResource(R.color.blue_);
        viewPager=(ViewPager)view.findViewById(R.id.viewpage);
        pts_title.setDrawFullUnderline(false);
        pageList=new ArrayList<View>(4);
        pageList.add(inflater.inflate(R.layout.pager_team,null));
        pageList.add(inflater.inflate(R.layout.pager_team,null));
        pageList.add(inflater.inflate(R.layout.pager_team,null));
        pageList.add(inflater.inflate(R.layout.pager_team,null));
        titleList=new ArrayList<String>(4);
        titleList.add("前锋");
        titleList.add("中场");
        titleList.add("后卫");
        titleList.add("门将");
        ViewPagerAdapter adapter=new ViewPagerAdapter(pageList,titleList);
        viewPager.setAdapter(adapter);*/
        fancyCoverFlow=(FancyCoverFlow)view.findViewById(R.id.fancyCoverFlow);
        fancyCoverFlow.setSpacing(-70);
        int[] images = {R.drawable.for_11, R.drawable.for_9, R.drawable.for_11,R.drawable.for_14,
                R.drawable.for_11,R.drawable.for_14, R.drawable.for_11,R.drawable.for_14, R.drawable.for_11,R.drawable.for_14};
        fancyCoverFlow.setAdapter(new TeamFancyCoverFlowAdapter(images));
        fancyCoverFlow.setSelection(images.length/2);
        fancyCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 application.msgShow("setOnItemClickListener 球员"+i);
            }
        });
	}


}
