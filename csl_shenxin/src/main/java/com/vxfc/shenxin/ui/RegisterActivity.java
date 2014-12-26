package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.presenter.LoginPresenter;
import com.vxfc.shenxin.presenter.RegisterPresenter;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.ILoginView;
import com.vxfc.shenxin.view.IRegisterView;

public class RegisterActivity extends BaseActivity implements IRegisterView{

    private Button btnLogin,btnSkipLogin;
    private EditText etTel,etPwd;
    private RegisterPresenter presenter;
    /***3rd ibtn ****/
    private ImageButton ibtnQQ,ibtnSina,ibtnWeixin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setBackActionBarTilte("登陆");
        presenter=new RegisterPresenter(this);
    }


    /**********
     * 初始化数据
     */
    @Override
    protected void requestData(){

    }

    @Override
    protected void onMenuItemSelected(int id) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return super.onCreateOptionsMenu(menu);
    }
	@Override
    protected void initView(){
        etTel=(EditText)findViewById(R.id.et_tel);
    }


    @Override
    public void errorRegiter(String msg) {
        application.msgShow(msg);
    }

    @Override
    public void toMainActivity() {
        Util.openActivity(MainActivity.class,null,this,ActivityModel.ACTIVITY_MODEL_2,true);
    }
}
