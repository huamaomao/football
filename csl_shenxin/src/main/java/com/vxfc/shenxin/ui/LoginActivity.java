package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.presenter.LoginPresenter;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.ILoginView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends BaseActivity implements ILoginView{

    @InjectView(R.id.et_tel)  EditText etTel;
    @InjectView(R.id.et_pwd)  EditText etPwd;
    private LoginPresenter presenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       setBackActionBarTilte("登陆");
        presenter=new LoginPresenter(this);
        ButterKnife.inject(this);

    }

    /**********
     * 初始化数据
     */
    @Override
    protected void requestData(){

    }

    @Override
    protected void onHomeClick() {
       Util.openActivity(ChooseActivity.class,this,ActivityModel.ACTIVITY_MODEL_1);
    }

    @Override
    protected void onMenuItemSelected(int id) {
        switch (id){
            case R.id.action_login:
                presenter.login(etTel.getText().toString(),etPwd.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return super.onCreateOptionsMenu(menu);
    }
	@Override
    protected void initView(){
    }

}
