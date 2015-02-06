package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.vxfc.common.util.ActivityModel;
import com.vxfc.common.util.Util;
import com.vxfc.common.util.ViewUtil;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.presenter.RegisterPresenter;
import com.vxfc.shenxin.view.IRegisterView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RegisterActivity extends BaseActivity implements IRegisterView{

    @InjectView(R.id.rl_first) LinearLayout llTel;
    @InjectView(R.id.rl_next) LinearLayout llCode;
    @InjectView(R.id.et_tel) EditText etTel;
    //@InjectView(R.id.et_pwd)  EditText etPwd;

    private RegisterPresenter presenter;
    /****标志是否第一步***/
    private boolean flag=true;

    @InjectView(R.id.btn_send) Button btnSend;

    private MenuItem nextMenu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        setBackActionBarTilte("注册");
        presenter=new RegisterPresenter(this);
        initView();
    }

    @Override
    protected void onHomeClick() {
        ViewUtil.openActivity(ChooseActivity.class, this, ActivityModel.ACTIVITY_MODEL_1);
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
                    llTel.setVisibility(View.GONE);
                    llCode.setVisibility(View.VISIBLE);
                    flag=true;
                    presenter.doRegisterFirst(etTel.getText().toString());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        nextMenu=menu.findItem(R.id.action_next);
        return true;
    }
	@Override
    protected void initView(){
        //重发验证码
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        etTel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
               if (s.length()==11){
                   if(Util.isMobileNO(s.toString())){
                       nextMenu.setEnabled(true);
                   }else{
                       showMsg("手机格式错误...");
                   }
               }
            }
        });
    }

    @Override
    public void errorRegiter(String msg) {
        showMsg(msg);
    }

    @Override
    public void toMainActivity() {
        ViewUtil.openActivity(MainActivity.class,null,this,ActivityModel.ACTIVITY_MODEL_2,true);
    }
}
