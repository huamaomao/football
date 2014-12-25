package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.Request;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.entity.RecentGameTeam;
import com.vxfc.shenxin.entity.Token;
import com.vxfc.shenxin.presenter.LoginPresenter;
import com.vxfc.shenxin.service.FileService;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.view.ILoginView;
import io.vov.vitamio.LibsChecker;

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener{

    private Button btnLogin,btnSkipLogin;
    private EditText etTel,etPwd;
    private LoginPresenter presenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setBackActionBarTilte("登陆");
        presenter=new LoginPresenter(this);
    }

    /**********
     * 初始化数据
     */
    @Override
    protected void requestData(){

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return super.onCreateOptionsMenu(menu);
    }
	@Override
    protected void initView(){
        btnLogin=(Button)findViewById(R.id.btn_login);
        btnSkipLogin=(Button)findViewById(R.id.btn_skiplogin);
        etPwd=(EditText)findViewById(R.id.et_pwd);
        etTel=(EditText)findViewById(R.id.et_tel);

        btnSkipLogin.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void toRegisterActivity() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_login:
                break;

            case R.id.btn_skiplogin:
                break;

            default:
                break;
        }
    }
}
