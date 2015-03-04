package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.ListRecentRecordAdapater;
import com.vxfc.shenxin.domian.RecentGameTeam;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;

import java.util.ArrayList;
import java.util.List;

/****
 *近期战绩
 *
 */
public class MRecentRecordFragment extends BaseListFragment {

    private List<RecentGameTeam> data;

    private RadioButton rbtn_title_0,rbtn_title_1;
    private RadioGroup rg_group_m;
    private  int type=0;

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_m_recent_record);
	}


    @Override
    public void requestData(){
        if (Util.isNull(application.getGameTeam()))return;
        data.clear();
        String teamId=null;
       if (type==0){
           teamId=application.getGameTeam().getTeamAId();
       }else {
           teamId=application.getGameTeam().getTeamBId();
       }
        ((ListRecentRecordAdapater)adapter).setTeamId(teamId);
        application.execute(RequestUtil.requestRecentRecord(application.getToken().getAccess_token(),
                teamId),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String json, Response res) {
                List<RecentGameTeam> temp=JSON.parseArray(json,RecentGameTeam.class);
                if (Util.notNull(temp)){
                    data.addAll(temp);
                    adapter.notifyDataSetChanged();
                }
                pullListView.onPullDownRefreshComplete();
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                pullListView.onPullDownRefreshComplete();
                application.networkErrorMessage(e,res);
            }
        });
    }


    /****
     * 初始化
     * @param view
     * @param inflater
     */
    protected void initView(View view, LayoutInflater inflater) {
        pullDownListView(view);
        rg_group_m=(RadioGroup)view.findViewById(R.id.rg_group_m);
        rbtn_title_0=(RadioButton)view.findViewById(R.id.rbtn_title_0);
        rbtn_title_1=(RadioButton)view.findViewById(R.id.rbtn_title_1);
        rg_group_m.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtn_title_0:
                        type=0;
                        break;
                    case R.id.rbtn_title_1:
                        type=1;
                        break;
                }
                doPullRefreshing();
            }
        });
        data=new ArrayList<RecentGameTeam>();
        adapter=new ListRecentRecordAdapater(getActivity(),data);
        listView.setAdapter(adapter);
        if (Util.notNull(application.getGameTeam())){
            StringBuilder builder=new StringBuilder(application.getGameTeam().getTeamAName());
            builder.append("近期战绩");
            rbtn_title_0.setText(builder.toString());
            builder=new StringBuilder(application.getGameTeam().getTeamBName());
            builder.append("近期战绩");
            rbtn_title_1.setText(builder.toString());
        }
	}




}
