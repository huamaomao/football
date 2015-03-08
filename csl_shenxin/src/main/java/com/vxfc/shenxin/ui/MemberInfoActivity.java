package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.util.CommonUtil;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.domian.Member;
import com.vxfc.shenxin.domian.param.MemberParam;
import com.vxfc.shenxin.presenter.ChoosePresenter;
import com.vxfc.shenxin.service.SharedService;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.IChooseView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MemberInfoActivity extends BaseActivity{

   @InjectView(R.id.btn_sign)  Button btnSign;
   @InjectView(R.id.btn_chongzhi) Button btnChongzhi;
   @InjectView(R.id.btn_loginout) Button btnLoginout;
   @InjectView(R.id.tv_name)  TextView  tv_name;
    @InjectView(R.id.tv_level)  TextView  tv_level;
    @InjectView(R.id.tv_xuanbi)  TextView  tv_xuanbi;

    private SharedService service;
    private String memberId=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info);
        ButterKnife.inject(this);
        setBackActionBarTilte("我的账户");
        service=new SharedService(this);
        requestData();
        initView();
    }
    @OnClick(R.id.btn_sign)
    public void sign(){
        showMsg("暂未开放");
    }
    @OnClick(R.id.btn_loginout)
    public void loginout(){
        service.putString(Dict.MEMBER_ID,null);
        Util.openActivity(ChooseActivity.class,this);
        finish();
    }
    @OnClick(R.id.btn_chongzhi)
    public void chongzhi(){
        showMsg("暂未开放");
    }

    @Override
    protected void requestData() {
        super.requestData();
        MemberParam param=new MemberParam();
        memberId=service.getValue(Dict.MEMBER_ID);
        if(CommonUtil.isEmpty(memberId)){
            btnLoginout.setText("登录");
        }
        if (CommonUtil.isEmpty(memberId)) return;
        param.setUserId(memberId);
        request(RequestUtil.requestMemberInfo(param), new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                Log.i(data);
                Member member= JSON.parseObject(data,Member.class);
                if (CommonUtil.notNull(member)){
                    tv_name.setText(member.getNike());
                    tv_level.setText(member.getLevel());
                    tv_xuanbi.setText(Util.initTextValue(member.getGoldCoins()));

                }
            }

            @Override
            protected void onFailure(HttpException e, Response res) {

            }
        });
    }

    @Override
    protected void initView(){

    }

}
