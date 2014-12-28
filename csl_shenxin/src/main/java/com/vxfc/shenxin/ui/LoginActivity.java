package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.presenter.LoginPresenter;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.ILoginView;

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener{

    private Button btnLogin,btnSkipLogin;
    private EditText etTel,etPwd;
    private LoginPresenter presenter;
    /***3rd ibtn ****/
    private ImageButton ibtnQQ,ibtnSina,ibtnWeixin;
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
    protected void onHomeClick() {
       Util.openActivity(ChooseActivity.class,this,ActivityModel.ACTIVITY_MODEL_1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return super.onCreateOptionsMenu(menu);
    }
	@Override
    protected void initView(){
        btnLogin=(Button)findViewById(R.id.btn_login);
        btnSkipLogin=(Button)findViewById(R.id.btn_skip_login);
        etPwd=(EditText)findViewById(R.id.et_pwd);
        etTel=(EditText)findViewById(R.id.et_tel);
        ibtnQQ=(ImageButton)findViewById(R.id.ib_1);
        ibtnSina=(ImageButton)findViewById(R.id.ib_2);
        ibtnWeixin=(ImageButton)findViewById(R.id.ib_3);

        btnSkipLogin.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        ibtnQQ.setOnClickListener(this);
        ibtnSina.setOnClickListener(this);
        ibtnWeixin.setOnClickListener(this);
    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void toRegisterActivity() {
        Util.openActivity(null,null,this, ActivityModel.ACTIVITY_MODEL_2);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toMainActivity() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_login:
                presenter.login(etTel.getText().toString(),etPwd.getText().toString());
                break;
            case R.id.btn_skip_login:
                break;

            case R.id.ib_1:
                break;
            case R.id.ib_2:
                break;
            case R.id.ib_3:
                break;

            default:
                break;
        }
    }
}
