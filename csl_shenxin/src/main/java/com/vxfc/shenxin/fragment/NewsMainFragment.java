package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.model.RecentGameTeam;
import com.vxfc.shenxin.util.*;

import java.util.Timer;
import java.util.TimerTask;

/****
 * 新闻/公告
 *
 */
public class NewsMainFragment extends BaseFragment{

    private GestureDetector detector;
    /** 轮播左方view   */
    private LinearLayout ll_lunbo,ll_lunbo_page,ll_lunbo_center;
    /** 轮播页总数   */
    private int pageCount=2;
    /*** 轮播部分     **/
    private TextView tv_type,tv_benlun,tv_benlun_against0,tv_benlun_against1,
            tv_benlun_date,tv_benlun_score0,tv_benlun_score1;
    private ImageView iv_against_icon0,iv_against_icon1;
    private  Animation mAnimationRight;

    private  Timer timer;
    private int  index=1;

    private boolean statusFlag=false;//是否开赛
    private  UpdateTimerTask timeTask=null;
    private  TeamTimerTask teamTimerTask;
    private PlayTimerTask playTimerTask;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timer=new Timer();
        mAnimationRight=AnimationUtils.loadAnimation(getActivity(),R.anim.scale);
        setLayoutId(R.layout.f_news);
        flag=true;
    }


    protected Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Dict.HANDLER_CHENGE_PAGE:
                    setNewsTab(msg.arg1);
                    break;
                case Dict.TIME_PLAY:
                    tv_benlun_date.setText(DateUtil.getTime());
                    break;
                case Dict.TIME_UPDATE:
                    final  RecentGameTeam gameTeam=application.getGameTeam();
                    if (Util.notNull(gameTeam))
                        tv_benlun_date.setText(DateUtil.timeDifference(gameTeam.getDate(),gameTeam.getTime()));

                    break;
            }

        }
    };

    /****
     *  开赛之后  1分钟请求一次
     * @param team
     */
    protected void  initViewData(RecentGameTeam team){
        if (null!=team){
            ll_lunbo.setVisibility(View.VISIBLE);
            ll_lunbo_center.setVisibility(View.VISIBLE);
            application.setGameTeam(team);
            //ui 设置
            StringBuilder builder=new StringBuilder("本轮 (");
            builder.append(team.getRound());
            builder.append(")");
            tv_benlun.setText(builder.toString());
            tv_benlun_against0.setText(team.getTeamAName());
            tv_benlun_against1.setText(team.getTeamBName());
            if (!team.getStatus().equals(Dict.STATUS_N)&&!team.getStatus().equals(Dict.STATUS_P)){
                statusFlag=true;
                tv_benlun_date.setText(DateUtil.getTime());
                tv_benlun_score0.setText(Util.getScoreAorB(team.getScore(), 0));
                tv_benlun_score1.setText(Util.getScoreAorB(team.getScore(),1));
                try {
                    if (Util.isNull(teamTimerTask)){
                        teamTimerTask=new TeamTimerTask();
                        timer.schedule(teamTimerTask,60000,60000);
                    }

                } catch (Exception e) {

                }
            }
            switch (team.getStatus()){
                case Dict.STATUS_N:
                case Dict.STATUS_P:
                    statusFlag=false;
                    //未开赛
                    tv_benlun_score0.setText("V");
                    tv_benlun_score1.setText("S");
                    tv_benlun_date.setText(DateUtil.timeDifference(team.getDate(),team.getTime()));
                    if (Util.notNull(timeTask))
                        timeTask.cancel();
                    timeTask=new UpdateTimerTask();
                    timer.schedule(timeTask, 1000, 1000);
                    if (Util.isNull(teamTimerTask)){
                        teamTimerTask=new TeamTimerTask();
                        timer.schedule(teamTimerTask,DateUtil.getDate(team.getDate(),team.getTime()),60000);
                    }
                    break;
                case Dict.STATUS_F:
                    tv_benlun_date.setText("已完赛");
                    break;
                case Dict.STATUS_1:
                case Dict.STATUS_2:
                case Dict.STATUS_3:
                case Dict.STATUS_4:
                    startTask(team);
                    break;
                case Dict.STATUS_5:
                case Dict.STATUS_6:
                case Dict.STATUS_7:
                    if (Util.notNull(playTimerTask)){
                        try {
                            playTimerTask.cancel();
                            playTimerTask=null;
                        }catch (Exception e){
                            playTimerTask=null;
                        }
                    }
                    StringBuilder builder1=new StringBuilder(team.getGameTime());
                    builder1.append(":");
                    builder1.append("00");
                    tv_benlun_date.setText(builder1.toString());
                    break;

            }
            iv_against_icon0.setBackgroundResource(Team.getTeamIcon(team.getTeamAId()));
            iv_against_icon1.setBackgroundResource(Team.getTeamIcon(team.getTeamBId()));
        }

    }

    private void startTask(RecentGameTeam t){
        if (Util.notNull(playTimerTask)){
            playTimerTask=new PlayTimerTask();
            if(!Util.isEmpty( t.getGameTime())){
                try {
                    playTimerTask.min_s=Integer.valueOf(t.getGameTime());
                } catch (Exception e) {
                }
            }
            timer.schedule(playTimerTask, 1000, 1000);
        }
    }

    /**********
     * 初始化数据
     */
    @Override
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


    @Override
    public void onStop() {
        stopTask();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if(Util.notNull(timer)){
                timer.purge();
                timer.cancel();
                timer=null;
            }
        } catch (Exception e) {

        }
    }

    /****
     * 初始化
     * @param view
     * @param inflater
     */
    @Override
    public void initView(final View view, LayoutInflater inflater) {
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
        detector=new GestureDetector(new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float y=velocityY<0?-velocityY:velocityY;
                float x=velocityX<0?-velocityX:velocityX;
                if (y>x){
                    index++;
                    if (index>=pageCount){
                        index=0;
                    }
                    Message msg=new Message();
                    msg.what=Dict.HANDLER_CHENGE_PAGE;
                    msg.arg1=index;
                    handler.sendMessage(msg);
                }
                return false;
            }
        });
        ll_lunbo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
        Util.turnToFragment(getChildFragmentManager(),NewsFragment.class,null,R.id.fl_news_content);
        tv_type.setText("赛事分析");
        if (Util.notNull(application.getGameTeam())){
            initViewData(application.getGameTeam());
        }
    }

    /***
     * 改变圆点页
     * @param index
     */
    public void setNewsTab(int index){
        if(index==2){
            Util.turnToFragment(getChildFragmentManager(),NewsFragment.class,null,R.id.fl_news_content);
            return;
        }
        for (int i=0;i<2;i++){
            ll_lunbo_page.getChildAt(i).setBackgroundResource(R.drawable.page_no);
        }
        ll_lunbo_page.getChildAt(index).setBackgroundResource(R.drawable.page_select);
        switch (index){
            case 0:
                tv_type.setText("直播中");
                tv_type.setAnimation(mAnimationRight);
                Bundle bundle=new Bundle();
                bundle.putSerializable(Dict.SERIALIZABLE,application.getGameTeam());
                Util.turnToFragment(getChildFragmentManager(),LiveMainFragment.class,bundle,R.id.fl_news_content);
                break;
            case 1:
                tv_type.setText("赛事分析");
                tv_type.setAnimation(mAnimationRight);
                Util.turnToFragment(getChildFragmentManager(), MachanakysisFragment.class,null,R.id.fl_news_content);
                break;


        }
    }
    private void stopTask(){
        try {
            if(Util.notNull(teamTimerTask)){
                teamTimerTask.cancel();
            }
            if(Util.notNull(playTimerTask)){
                playTimerTask.cancel();
            }
            if(Util.notNull(timeTask)){
                timeTask.cancel();
            }

        }catch (Exception e){}

    }


    class  TeamTimerTask extends TimerTask{
        @Override
        public void run() {
            requestData();
        }
    }

    class UpdateTimerTask extends TimerTask{
        @Override
        public void run() {
            handler.sendEmptyMessage(Dict.TIME_UPDATE);
        }
    }

    class PlayTimerTask extends TimerTask{
        int min=0;
        int ss=0;
        int min_s;
        @Override
        public void run() {
            if (min!=min_s){
                min=min_s;
                ss=0;
            }
            ss++;
            if (ss==60){
                ss=0;
                min++;
            }
            handler.sendEmptyMessage(Dict.TIME_PLAY);
        }
    }

}
