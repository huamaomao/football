package com.weixuan.shenxin.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.adapter.ListLiveLanqiuAdapater;
import com.weixuan.shenxin.entity.RecentGameTeam;
import com.weixuan.shenxin.entity.TeamDataVo;
import com.weixuan.shenxin.util.Dict;
import com.weixuan.shenxin.util.RequestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * *
 * 实况数据
 */
public class LiveLanqiuFragment extends BaseListFragment {
    private RadioGroup rg_group_m;
    private List<TeamDataVo> lsData;
    private RecentGameTeam team;
    private int type = 0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LiveMainFragment  fragment=(LiveMainFragment) getParentFragment();
        team=fragment.team;
        setLayoutId(R.layout.f_live_lanqiu);
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Dict.data_change:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * *
     * 初始化
     *
     * @param view
     * @param inflater
     */
    protected void initView(View view, LayoutInflater inflater) {
        rg_group_m=(RadioGroup)view.findViewById(R.id.rg_group_m);
        pullDownLoadListView(view);
        lsData=new ArrayList<TeamDataVo>();
        adapter=new ListLiveLanqiuAdapater(getActivity(),lsData);
        listView.setAdapter(adapter);
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
                    case R.id.rbtn_title_2:
                        type=2;
                        break;
                }
                doPullRefreshing();
            }
        });

    }



    private HttpModelHandler<String> modelHandler=new HttpModelHandler<String>() {
        @Override
        protected void onSuccess(String data, Response res) {
             List<TeamDataVo> temp=JSON.parseArray(data,TeamDataVo.class);
            if (temp!=null){
                lsData.clear();
                lsData.addAll(temp);
                adapter.notifyDataSetChanged();
            }
            pullListView.onPullDownRefreshComplete();
        }

        @Override
        protected void onFailure(HttpException e, Response res) {
              application.networkErrorMessage(e,res);
            pullListView.onPullDownRefreshComplete();
        }
    };

    @Override
    protected void requestData() {
        super.requestData();
        switch (type){
            case  0:
                 application.execute(RequestUtil.requestTeamLiveAttackData(application.getToken().getAccess_token()
                            , team.getTeamAId(), team.getTeamBId(),team.getId()),modelHandler);
                break;
            case 1:
                application.execute(RequestUtil.requestTeamLiveDefenseData(application.getToken().getAccess_token()
                            , team.getTeamAId(),team.getTeamBId(),team.getId()),modelHandler);
                break;
            case 2:
                application.execute(RequestUtil.requestTeamLiveGeneralData(application.getToken().getAccess_token()
                            , team.getTeamAId(), team.getTeamBId(),team.getId()),modelHandler);
                break;
        }

    }
}
