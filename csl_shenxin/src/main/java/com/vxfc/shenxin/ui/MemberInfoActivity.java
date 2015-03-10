package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.domain.Version;
import com.vxfc.common.util.CommonUtil;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.QuickAdapter;
import com.vxfc.shenxin.domian.InfoList;
import com.vxfc.shenxin.domian.Member;
import com.vxfc.shenxin.domian.param.MemberParam;
import com.vxfc.shenxin.presenter.ChoosePresenter;
import com.vxfc.shenxin.service.SharedService;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.util.ViewHolderHelp;
import com.vxfc.shenxin.view.IChooseView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MemberInfoActivity extends BaseActivity{


   @InjectView(R.id.btn_chongzhi) Button btnChongzhi;


   @InjectView(R.id.tv_name)  TextView  tv_name;
   @InjectView(R.id.tv_level)  TextView  tv_level;
   @InjectView(R.id.tv_xuanbi)  TextView  tv_xuanbi;
   @InjectView(R.id.ls_data)  ListView lsData;

    private QuickAdapter adapter;
    private List<InfoList> data;
    private SharedService service;
    private String memberId=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info);
        ButterKnife.inject(this);
        setBackActionBarTilte("我的账户");
        service=new SharedService(this);
        initView();
        requestData();

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
            data.set(5,new InfoList("进行登录",">"));
            adapter.notifyDataSetChanged();
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
        data=new ArrayList<InfoList>();
        data.add(new InfoList("今日签到 +10",">"));
        data.add(new InfoList("主场签到",">"));
        data.add(new InfoList("版本更新","已是最新版本"));
        data.add(new InfoList("建议和反馈",">"));
        data.add(new InfoList("关于我们",">"));
        data.add(new InfoList("退出登录",">"));
        Version version=CommonUtil.getVersion(this);
        Log.i(version);
        adapter=new  QuickAdapter<InfoList>(this, R.layout.list_item_info, data) {
            @Override
            protected void convert(ViewHolderHelp helper, InfoList item) {
                helper.setText(R.id.tv_item_0,item.title).setText(R.id.tv_item_1,item.flag);
            }
        };
        lsData.setAdapter(adapter);
        lsData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        showMsg("暂未开放");
                        break;
                    case 1:
                        showMsg("暂未开放");
                        break;
                    case 2:
                        showMsg("暂未开放");
                        break;
                    case 3:
                        showMsg("暂未开放");
                        break;
                    case 4:
                        showMsg("暂未开放");
                        break;
                    case 5:
                        service.putString(Dict.MEMBER_ID,null);
                        Util.openActivity(ChooseActivity.class,MemberInfoActivity.this);
                        finish();
                        break;
                }
            }
        });
    }

}
