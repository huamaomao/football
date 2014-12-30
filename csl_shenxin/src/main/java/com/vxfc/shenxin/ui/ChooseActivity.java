package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.presenter.ChoosePresenter;
import com.vxfc.shenxin.sina.AuthListener;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Constants;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.IChooseView;

public class ChooseActivity extends BaseActivity implements IChooseView, View.OnClickListener{

    private Button btnLogin,btnSkipLogin,btnRegsiter;

    private ChoosePresenter presenter;
    /***3rd ibtn ****/
    private ImageButton ibtnQQ,ibtnSina,ibtnWeixin;

    /*******sso* sina*****/
    private SsoHandler mSsoHandler;
    private WeiboAuth mWeiboAuth;

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

        // 创建微博实例
        mWeiboAuth = new WeiboAuth(this, Constants.SINA_KEY,Constants.REDIRECT_URL, Constants.SCOPE);

    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_login:
                toLoginActivity();
                break;
            case R.id.btn_skip_login:
                Util.openActivity(MainActivity.class,null,this,ActivityModel.ACTIVITY_MODEL_2,true);
                break;
            case R.id.btn_register:
                toRegisterActivity();
                break;
            case R.id.ib_1:/**qq***/
                break;
            case R.id.ib_2:/**weixin***/
                break;
            case R.id.ib_3:/**sina***/
                mSsoHandler = new SsoHandler(ChooseActivity.this, mWeiboAuth);
                mSsoHandler.authorize(new AuthListener(this));
                break;

            default:
                break;
        }
    }

    @Override
    public void toLoginActivity() {
        Util.openActivity(LoginActivity.class,null,this,ActivityModel.ACTIVITY_MODEL_2,true);
    }

    @Override
    public void toRegisterActivity() {
        Util.openActivity(RegisterActivity.class,null,this,ActivityModel.ACTIVITY_MODEL_2,true);
    }
}
