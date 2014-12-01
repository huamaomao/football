package com.weixuan.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.adapter.ListLiveLanqiuAdapater;
import com.weixuan.shenxin.entity.TeamDataVo;
import com.weixuan.shenxin.util.RequestUtil;
import com.weixuan.shenxin.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * *
 * 赛事分析->实力对比
 */
public class MPowerFragment extends BaseListFragment {
    private RadioGroup rg_group_m;

    private List<TeamDataVo> dataVos;
    private int type = 0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_m_power);
    }

    protected void initView(View view, LayoutInflater inflater) {
            rg_group_m = (RadioGroup) view.findViewById(R.id.rg_group_m);
            pullDownListView(view);
            dataVos = new ArrayList<>();
            adapter = new ListLiveLanqiuAdapater(getActivity(), dataVos);
            listView.setAdapter(adapter);
            rg_group_m.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    dataVos.clear();
                    switch (checkedId) {
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
        @Override
        public void requestData(){
            if (Util.isNull(application.getGameTeam())) return;
            switch (type) {
                case 0:
                     application.execute(RequestUtil.requestTeamAttackData(application.getToken().getAccess_token()
                                , application.getGameTeam().getTeamAId(), application.getGameTeam().getTeamBId()), modelHandler);

                case 1:
                      application.execute(RequestUtil.requestTeamDefenseData(application.getToken().getAccess_token()
                                , application.getGameTeam().getTeamAId(), application.getGameTeam().getTeamBId()), modelHandler);
                    break;
                case 2:
                       application.execute(RequestUtil.requestTeamGeneralData(application.getToken().getAccess_token()
                                , application.getGameTeam().getTeamAId(), application.getGameTeam().getTeamBId()), modelHandler);
                    break;
            }
        }

        private HttpModelHandler<String> modelHandler = new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                List<TeamDataVo> temp = JSON.parseArray(data, TeamDataVo.class);
                if (temp != null) {
                    dataVos.clear();
                    dataVos.addAll(temp);
                    adapter.notifyDataSetChanged();
                    pullListView.onPullDownRefreshComplete();
                }
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                application.networkErrorMessage(e, res);
                pullListView.onPullDownRefreshComplete();
            }
        };

 }
