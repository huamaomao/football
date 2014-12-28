package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.vxfc.shenxin.R;
import com.vxfc.shenxin.presenter.LoginPresenter;
import com.vxfc.shenxin.presenter.RegisterPresenter;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.ILoginView;
import com.vxfc.shenxin.view.IRegisterView;

public class RegisterActivity extends BaseActivity implements IRegisterView{

    private RelativeLayout rlTel,rlCode;
    private EditText etTel,etPwd;
    private RegisterPresenter presenter;
    /****标志是否第一步***/
    private boolean flag=true;
    /***3rd ibtn ****/
    private Button btnSend;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setBackActionBarTilte("注册");
        presenter=new RegisterPresenter(this);
        initView();
    }

    @Override
    protected void onHomeClick() {
        Util.openActivity(ChooseActivity.class,this,ActivityModel.ACTIVITY_MODEL_1);
    }

    /**********
     * 初始化数据
     */
    @Override
    protected void requestData(){

    }

    @Override
    protected void onMenuItemSelected(int id) {
        switch (id){
            case R.id.action_next:
                if (flag){
                    rlTel.setVisibility(View.GONE);
                    rlCode.setVisibility(View.VISIBLE);
                    flag=true;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }
	@Override
    protected void initView(){
        etTel=(EditText)findViewById(R.id.et_tel);
        rlTel=(RelativeLayout)findViewById(R.id.rl_first);
        rlCode=(RelativeLayout)findViewById(R.id.rl_next);
        btnSend=(Button)findViewById(R.id.btn_send);
        //重发验证码
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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
