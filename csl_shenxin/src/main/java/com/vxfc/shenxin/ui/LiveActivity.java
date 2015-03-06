package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vxfc.common.util.DateUtil;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.fragment.ChooseDialogFragment;
import com.vxfc.shenxin.fragment.MachanakysisFragment;
import com.vxfc.shenxin.domian.RecentGameTeam;
import com.vxfc.shenxin.fragment.LiveMainFragment;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.Team;
import com.vxfc.shenxin.util.Util;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LiveActivity extends BaseActivity{
    @InjectView(R.id.tv_benlun) TextView tv_benlun;
    @InjectView(R.id.tv_benlun_against0) TextView tv_benlun_against0;
    @InjectView(R.id.tv_benlun_against1) TextView tv_benlun_against1;
    @InjectView(R.id.tv_benlun_date) TextView tv_benlun_date;
    @InjectView(R.id.tv_benlun_score0) TextView tv_benlun_score0;
    @InjectView(R.id.tv_benlun_score1) TextView tv_benlun_score1;

    @InjectView(R.id.iv_against_icon0) ImageView iv_against_icon0;
    @InjectView(R.id.iv_against_icon1) ImageView iv_against_icon1;

    @InjectView(R.id.btn_choose)ImageButton btnChoose;

    @InjectView(R.id.ll_lunbo_center) LinearLayout ll_lunbo_center;

    public RecentGameTeam team;
    private ChooseDialogFragment fragment;
    private  UpdateTimerTask timeTask=null;
    private  TeamTimerTask teamTimerTask;
    private PlayTimerTask playTimerTask;
    private Timer timer;
    private boolean statusFlag=false;//是否开赛

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
       setContentView(R.layout.f_live_s);
        ButterKnife.inject(this);
        timer=new Timer();
        team=(RecentGameTeam)getIntent().getSerializableExtra(Dict.SERIALIZABLE);
        initViewData(team);
        Bundle bundle=getIntent().getExtras();
        if(Dict.TYPE_LIVE==bundle.getInt(Dict.TYPE)){
            Util.turnToFragment(getSupportFragmentManager(), LiveMainFragment.class, bundle, R.id.fl_news_content);
            setBackActionBarTilte("赛事直播");
        }else{
            Util.turnToFragment(getSupportFragmentManager(), MachanakysisFragment.class, bundle, R.id.fl_news_content);
            setBackActionBarTilte("赛事分析");
        }
        fragment=new ChooseDialogFragment();
        fragment.setOnClickListener(new ChooseDialogFragment.OnClickListener() {
            @Override
            public void onLive() {
                Util.turnToFragment(getSupportFragmentManager(), LiveMainFragment.class, getIntent().getExtras(), R.id.fl_news_content);
                setBackActionBarTilte("赛事直播");
            }

            @Override
            public void onFenxi() {
                Util.turnToFragment(getSupportFragmentManager(), MachanakysisFragment.class, getIntent().getExtras(), R.id.fl_news_content);
                setBackActionBarTilte("赛事分析");
            }

            @Override
            public void onQuiz() {
                application.msgShow("暂未开放...");
               /* Bundle bundle=new Bundle();
                bundle.putSerializable(Dict.SERIALIZABLE,team);
                Util.openActivity(LiveActivity.class,bundle,getActivity(),ActivityModel.ACTIVITY_MODEL_1);*/

            }
        });

    }

    @OnClick(R.id.btn_choose)
    public void onChoose(){
        fragment.show(getSupportFragmentManager(),"choose");
    }

    protected void  initViewData(RecentGameTeam team){
        if (null!=team){
            //ui 设置
            StringBuilder builder=new StringBuilder("第");
            builder.append(team.getRound());
            builder.append("轮");
            tv_benlun.setText(builder.toString());
            tv_benlun_against0.setText(team.getTeamAName());
            tv_benlun_against1.setText(team.getTeamBName());
            tv_benlun_date.setText(team.getDate());
            tv_benlun_score0.setText(Util.getScoreAorB(team.getScore(), 0));
            tv_benlun_score1.setText(Util.getScoreAorB(team.getScore(),1));
            iv_against_icon0.setBackgroundResource(Team.getTeamIcon(team.getTeamAId()));
            iv_against_icon1.setBackgroundResource(Team.getTeamIcon(team.getTeamBId()));
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
        }

    }

    /****
     * 初始化
     */
    @Override
	public void initView() {

	}

    protected Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Dict.TIME_PLAY:
                    tv_benlun_date.setText(DateUtil.getTime());
                    break;
                case Dict.TIME_UPDATE:
                    final  RecentGameTeam gameTeam=application.getGameTeam();
                    if (Util.notNull(gameTeam))
                        tv_benlun_date.setText(DateUtil.timeDifference(gameTeam.getDate(), gameTeam.getTime()));

                    break;
            }

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
          toMainActity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class UpdateTimerTask extends TimerTask {
        @Override
        public void run() {
            handler.sendEmptyMessage(Dict.TIME_UPDATE);
        }
    }

    class  TeamTimerTask extends TimerTask{
        @Override
        public void run() {
            requestData();
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
}
