/*
 * Created by Storm Zhang, Feb 11, 2014.
 */

package com.vxfc.shenxin.ui;

import android.os.Bundle;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.Request;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.model.RecentGameTeam;
import com.vxfc.shenxin.model.Token;
import com.vxfc.shenxin.presenter.WelcomePresenter;
import com.vxfc.shenxin.service.FileService;
import com.vxfc.shenxin.service.SharedService;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.IWelcome;

import java.util.Timer;
import java.util.TimerTask;

import io.vov.vitamio.LibsChecker;

public class WelcomeActivity extends BaseActivity implements IWelcome{

    private WelcomePresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        presenter=new WelcomePresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.activityWelcome();
    }

}
