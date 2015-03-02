package com.vxfc.shenxin.presenter;

import android.app.Activity;

import com.vxfc.shenxin.service.SharedService;
import com.vxfc.shenxin.ui.ChooseActivity;
import com.vxfc.shenxin.ui.MainActivity;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.IWelcome;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Hua_ on 2015/2/27.
 */
public class WelcomePresenter {
    private IWelcome welcome;
    private SharedService service;
    public WelcomePresenter(IWelcome view){
        this.welcome=view;
        service=new SharedService((Activity) view);
    }

    public void activityWelcome(){
        Timer timer=new Timer();
        TimerTask task=new TimerTask(){
            public void run(){
                String userId=service.getValue(Dict.MEMBER_ID);
                if (Util.isEmpty(userId)){
                    Util.openActivity(ChooseActivity.class, null,(Activity) welcome, ActivityModel.ACTIVITY_MODEL_0);
                }else {
                    Util.openActivity(MainActivity.class, null,(Activity) welcome, ActivityModel.ACTIVITY_MODEL_0);
                }
                ((Activity) welcome).finish();
            }
        };
        timer.schedule(task, 2500);

    }
}
