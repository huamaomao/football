package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.litesuits.http.request.Request;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.util.ActivityModel;
import com.vxfc.common.util.CommonUtil;
import com.vxfc.common.util.ViewUtil;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.presenter.RegisterPresenter;
import com.vxfc.shenxin.view.IRegisterView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements IRegisterView{

    @InjectView(R.id.rl_first) LinearLayout llTel;
    @InjectView(R.id.gl_next) GridLayout glNext;
    @InjectView(R.id.et_tel) EditText etTel;
    @InjectView(R.id.et_pwd)  EditText etPwd;
    @InjectView(R.id.et_name)  EditText etNickName;
    @InjectView(R.id.et_code)  EditText etCode;

    @InjectView(R.id.tv_number)TextView  tvNumber;
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
        if (!flag){
            llTel.setVisibility(View.VISIBLE);
            glNext.setVisibility(View.GONE);
            etCode.getText().clear();
            etPwd.getText().clear();
            etNickName.getText().clear();
            etPwd.getText().clear();
            tvNumber.setText("");
        }else {
            ViewUtil.openActivity(ChooseActivity.class, this, ActivityModel.ACTIVITY_MODEL_1);
            finish();
        }
    }


    @Override
    protected void onMenuItemSelected(int id) {
        switch (id){
            case R.id.action_next:
                if (flag){
                    presenter.doRegisterFirst(etTel.getText().toString());
                }else {
                   presenter.doRegister(etTel.getText().toString(),etNickName.getText().toString().trim(),etCode.getText().toString(),
                           etPwd.getText().toString());
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

    /****
     * 重发验证码
     */
    @OnClick(R.id.btn_send)
    public void repeatSms(){
        presenter.repeatSms(etTel.getText().toString());
    }

	@Override
    protected void initView(){
        //重发验证码
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.repeatSms(etTel.getText().toString());
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
                   if(CommonUtil.isMobileNO(s.toString())){
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

    @Override
    public void setTelRemarks(String remarks) {
        tvNumber.setText(remarks);
    }

    @Override
    public void setRegisterStatus() {
        flag=false;
    }
}
