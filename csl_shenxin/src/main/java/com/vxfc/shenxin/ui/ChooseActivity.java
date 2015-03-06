package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.tencent.tauth.Tencent;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.presenter.ChoosePresenter;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.IChooseView;

public class ChooseActivity extends BaseActivity implements IChooseView, View.OnClickListener{

    private Button btnLogin,btnSkipLogin,btnRegsiter;

    private ChoosePresenter presenter;
    /***3rd ibtn ****/
    private ImageButton ibtnQQ,ibtnSina,ibtnWeixin;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        setBackActionBarTilte("返回");
        presenter=new ChoosePresenter(this);
        initView();
    }

	@Override
    protected void initView(){
        btnLogin=(Button)findViewById(R.id.btn_login);
        btnSkipLogin=(Button)findViewById(R.id.btn_skip_login);
        btnRegsiter=(Button)findViewById(R.id.btn_register);
        ibtnQQ=(ImageButton)findViewById(R.id.ib_1);
        ibtnSina=(ImageButton)findViewById(R.id.ib_3);
        ibtnWeixin=(ImageButton)findViewById(R.id.ib_2);

        btnSkipLogin.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        ibtnQQ.setOnClickListener(this);
        ibtnSina.setOnClickListener(this);
        ibtnWeixin.setOnClickListener(this);
        btnRegsiter.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_login:
                toLoginActivity();
                break;
            case R.id.btn_skip_login:
                Util.openActivity(MainActivity.class,null,this,ActivityModel.ACTIVITY_MODEL_1,true);
                break;
            case R.id.btn_register:
                toRegisterActivity();
                break;
            case R.id.ib_1:/**qq***/
               /* application.execute(RequestUtil.postRegister(),new HttpModelHandler<String>() {
                    @Override
                    protected void onSuccess(String data, Response res) {
                        Log.i(data);
                    }

                    @Override
                    protected void onFailure(HttpException e, Response res) {
                        Log.i(e);
                    }
                });*/
                presenter.login(ChoosePresenter.QQ);
                break;
            case R.id.ib_2:/**weixin***/
               presenter.login(ChoosePresenter.WEIXIN);
                break;
            case R.id.ib_3:/**sina***/
                presenter.login(ChoosePresenter.SINA);
                break;

            default:
                break;
        }
    }

    @Override
    public void toLoginActivity() {
        Util.openActivity(LoginActivity.class,null,this,ActivityModel.ACTIVITY_MODEL_1,true);
    }

    @Override
    public void toRegisterActivity() {
        Util.openActivity(RegisterActivity.class,null,this,ActivityModel.ACTIVITY_MODEL_1,true);
    }

    @Override
    public void msgShow(String msg) {
        showMsg(msg);
    }

    @Override
    public void msgLongShow(String msg) {
        msgLongShow(msg);
    }
}
