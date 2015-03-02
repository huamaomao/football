package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.util.DateUtil;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.model.RecentGameTeam;
import com.vxfc.shenxin.util.*;
/****
 * 新闻/公告
 *
 */
public class LiveFragment extends BaseFragment{

    private TextView tv_title;
    /** 轮播左方view   */
    private LinearLayout ll_lunbo,ll_lunbo_page,ll_lunbo_center;

    /*** 轮播部分     **/
    private TextView tv_type,tv_benlun,tv_benlun_against0,tv_benlun_against1,
            tv_benlun_date,tv_benlun_score0,tv_benlun_score1;
    private ImageView iv_against_icon0,iv_against_icon1;
    private int flag=0;
    private boolean statusFlag=false;//是否开赛
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_live_s);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (Util.notNull(rootView)){
            doRefresh();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void  initViewData(RecentGameTeam team){
        if (null!=team){
            ll_lunbo.setVisibility(View.VISIBLE);
            ll_lunbo_center.setVisibility(View.VISIBLE);
            application.setGameTeam(team);
            //ui 设置
            StringBuilder builder=new StringBuilder("轮次：");
            builder.append(team.getRound());
            builder.append("");
            tv_benlun.setText(builder.toString());
            tv_benlun_against0.setText(team.getTeamAName());
            tv_benlun_against1.setText(team.getTeamBName());
            statusFlag=true;
            tv_benlun_date.setText(DateUtil.getTime());
            tv_benlun_score0.setText(Util.getScoreAorB(team.getScore(), 0));
            tv_benlun_score1.setText(Util.getScoreAorB(team.getScore(),1));
            iv_against_icon0.setBackgroundResource(Team.getTeamIcon(team.getTeamAId()));
            iv_against_icon1.setBackgroundResource(Team.getTeamIcon(team.getTeamBId()));
        }

    }

    /**********
     * 初始化数据
     */
    protected void requestData(){
        application.execute( RequestUtil.requestRecently(application.getToken().getAccess_token(),application.teamId),
               new HttpModelHandler<String>() {
                   @Override
                   protected void onSuccess(String json, Response res) {
                       RecentGameTeam gameTeam= JSON.parseObject(json,RecentGameTeam.class);
                       initViewData(gameTeam);
                   }

                   @Override
                   protected void onFailure(HttpException e, Response res) {
                       application.networkErrorMessage(e,res);
                   }
               });
    }
    /****
     * 初始化
     * @param view
     * @param inflater
     */
    @Override
	public void initView(final View view, LayoutInflater inflater) {
        tv_title=(TextView)view.findViewById(R.id.tv_title);
        ll_lunbo=(LinearLayout)view.findViewById(R.id.ll_lunbo);
        ll_lunbo_page=(LinearLayout)view.findViewById(R.id.ll_lunbo_page);
        ll_lunbo_center=(LinearLayout)view.findViewById(R.id.ll_lunbo_center);
        tv_type=(TextView)view.findViewById(R.id.tv_type);
        tv_benlun=(TextView)view.findViewById(R.id.tv_benlun);
        tv_benlun_against0=(TextView)view.findViewById(R.id.tv_benlun_against0);
        tv_benlun_against1=(TextView)view.findViewById(R.id.tv_benlun_against1);
        tv_benlun_date=(TextView)view.findViewById(R.id.tv_benlun_date);
        tv_benlun_score0=(TextView)view.findViewById(R.id.tv_benlun_score0);
        tv_benlun_score1=(TextView)view.findViewById(R.id.tv_benlun_score1);
        iv_against_icon0=(ImageView)view.findViewById(R.id.iv_against_icon0);
        iv_against_icon1=(ImageView)view.findViewById(R.id.iv_against_icon1);
        ll_lunbo.setVisibility(View.INVISIBLE);
        ll_lunbo_center.setVisibility(View.INVISIBLE);
        setTitle(R.string.title_first_list);
        Util.turnToFragment(getChildFragmentManager(),NewsFragment.class,null,R.id.fl_news_content);

	}

    public void setNewsTab(Class fragmentClass){
        Util.turnToFragment(getChildFragmentManager(),fragmentClass,null,R.id.fl_news_content);
    }

    /***
     * 设置标题
     * @param resId
     */
    public void setTitle(int resId){
        tv_title.setText(getString(resId));
    }

}
