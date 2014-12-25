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
import com.vxfc.shenxin.entity.RecentGameTeam;
import com.vxfc.shenxin.entity.Token;
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
        fileService=new FileService(getApplicationContext());
        String json=fileService.readFile(USER_JSON);
        if(!Util.isEmpty(json)) {
            Token token = JSON.parseObject(json, Token.class);
            application.setToken(token);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.openActivity(LoginActivity.class,null,this, ActivityModel.ACTIVITY_MODEL_3);
    }

    class CheckLoginTask implements Runnable{
        @Override
        public void run() {
            try {
                String json=fileService.readFile(USER_JSON);
                if(!Util.isEmpty(json)){
                    Token token= JSON.parseObject(json,Token.class);
                    application.setToken(token);
                }else {
                    final  Request request= RequestUtil.requestUserToken();
                    application.execute(request,new HttpModelHandler<String>() {
                        @Override
                        protected void onSuccess(String data, Response res) {
                            try {
                                Token  token= Util.convertToken(request.getUrl().toString());
                                token.setClient_id(Dict.USER_NAME);
                                fileService.writeFile(USER_JSON, JSON.toJSONString(token));
                                if (token!=null){
                                    application.setToken(token);
                                    if (token.getExpires_in()>System.currentTimeMillis()){
                                        application.execute(RequestUtil.requestRefreshTokenTask(token.getClient_id(),token.getRefresh_token()),
                                                new HttpModelHandler<String>() {
                                                    @Override
                                                    protected void onSuccess(String data, Response res) {
                                                        if (!Util.isEmpty(data)){
                                                            Token  token1= JSON.parseObject(data,Token.class);
                                                            application.setToken(token1);
                                                        }
                                                    }
                                                    @Override
                                                    protected void onFailure(HttpException e, Response res) {
                                                        application.networkErrorMessage(e,res);
                                                    }
                                                });
                                    }

                                }
                            }catch (Exception e){
                                application.errorMessage(e);
                            }

                        }

                        @Override
                        protected void onFailure(HttpException e, Response res) {
                            application.networkErrorMessage(e,res);
                        }
                    });
                }
                requestData();
                LibsChecker.checkVitamioLibs(WelcomeActivity.this);
                Thread.sleep(2000);
            }catch (Exception e){
                application.errorMessage(e);
                application.setToken(new Token());
            }finally {
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                startActivity(MainActivity.class);
                finish();
            }
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
                        if (Util.notNull(gameTeam)){
                            application.setGameTeam(gameTeam);
                        }
                    }

                    @Override
                    protected void onFailure(HttpException e, Response res) {
                        application.networkErrorMessage(e,res);
                    }
                });
    }

	@Override
	public void onStop() {
		super.onStop();
	}


    protected void initView(){

    }

}
