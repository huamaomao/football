package com.weixuan.shenxin.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.entity.RecentGameTeam;
import com.weixuan.shenxin.fragment.LiveMainFragment;
import com.weixuan.shenxin.util.Dict;
import com.weixuan.shenxin.util.Team;
import com.weixuan.shenxin.util.Util;

public class LiveActivity extends BaseActivity{


    /** 轮播左方view   */
    private LinearLayout ll_lunbo,ll_lunbo_page,ll_lunbo_center;
    /*** 轮播部分     **/
    private TextView tv_type,tv_benlun,tv_benlun_against0,tv_benlun_against1,
            tv_benlun_date,tv_benlun_score0,tv_benlun_score1;
    private ImageView iv_against_icon0,iv_against_icon1;
    public RecentGameTeam team;
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
       setContentView(R.layout.f_live_s);
        initView();
        team=(RecentGameTeam)getIntent().getSerializableExtra(Dict.SERIALIZABLE);
        initViewData(team);
        Bundle bundle=new Bundle();
        bundle.putSerializable(Dict.SERIALIZABLE, team);
        Util.turnToFragment(getSupportFragmentManager(), LiveMainFragment.class, bundle, R.id.fl_news_content);
        setBackActionBarTilte("赛事回顾");
    }


    protected void  initViewData(RecentGameTeam team){
        if (null!=team){
            ll_lunbo.setVisibility(View.VISIBLE);
            ll_lunbo_center.setVisibility(View.VISIBLE);
            //ui 设置
            StringBuilder builder=new StringBuilder("轮次：");
            builder.append(team.getRound());
            builder.append("");
            tv_benlun.setText(builder.toString());
            tv_benlun_against0.setText(team.getTeamAName());
            tv_benlun_against1.setText(team.getTeamBName());
            tv_benlun_date.setText(team.getDate());
            tv_benlun_score0.setText(Util.getScoreAorB(team.getScore(), 0));
            tv_benlun_score1.setText(Util.getScoreAorB(team.getScore(),1));
            iv_against_icon0.setBackgroundResource(Team.getTeamIcon(team.getTeamAId()));
            iv_against_icon1.setBackgroundResource(Team.getTeamIcon(team.getTeamBId()));
            switch (team.getStatus()){
                case Dict.STATUS_F:
                    tv_type.setText("已结束");
                    break;
                case Dict.STATUS_P:
                    break;
                case Dict.STATUS_1:
                case Dict.STATUS_2:
                case Dict.STATUS_3:
                case Dict.STATUS_4:
                case Dict.STATUS_5:
                case Dict.STATUS_6:
                case Dict.STATUS_7:
                    tv_type.setText("直播中");
                    setBackActionBarTilte("赛事直播");
                    break;
            }
        }

    }

    /****
     * 初始化
     */
    @Override
	public void initView() {

        ll_lunbo=(LinearLayout)findViewById(R.id.ll_lunbo);
        ll_lunbo_page=(LinearLayout) findViewById(R.id.ll_lunbo_page);
        ll_lunbo_center=(LinearLayout) findViewById(R.id.ll_lunbo_center);
        tv_type=(TextView) findViewById(R.id.tv_type);
        tv_benlun=(TextView) findViewById(R.id.tv_benlun);
        tv_benlun_against0=(TextView) findViewById(R.id.tv_benlun_against0);
        tv_benlun_against1=(TextView) findViewById(R.id.tv_benlun_against1);
        tv_benlun_date=(TextView) findViewById(R.id.tv_benlun_date);
        tv_benlun_score0=(TextView) findViewById(R.id.tv_benlun_score0);
        tv_benlun_score1=(TextView) findViewById(R.id.tv_benlun_score1);
        iv_against_icon0=(ImageView) findViewById(R.id.iv_against_icon0);
        iv_against_icon1=(ImageView) findViewById(R.id.iv_against_icon1);
        ll_lunbo.setVisibility(View.INVISIBLE);
        ll_lunbo_center.setVisibility(View.INVISIBLE);

	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
          openMainActity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
