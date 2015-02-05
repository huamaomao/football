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
import com.vxfc.shenxin.service.FileService;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;
import io.vov.vitamio.LibsChecker;

public class WelcomeActivity extends BaseActivity{

    private FileService fileService;
    private final String USER_JSON="userToken.json";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.openActivity(ChooseActivity.class,null,this, ActivityModel.ACTIVITY_MODEL_0);
    }

}
