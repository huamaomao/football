package com.weixuan.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.adapter.ListScoreboardAdapater;
import com.weixuan.shenxin.entity.Standings;
import com.weixuan.shenxin.util.RequestUtil;
import com.weixuan.shenxin.util.Util;

import java.util.ArrayList;
import java.util.List;

/****
 * 积分榜
 *
 */
public class MScoreboardFragment extends BaseListFragment {
    private List<Standings> data;

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_m_scoreboard);
	}

    @Override
    protected void initView(View view, LayoutInflater inflater) {
        pullDownListView(view);
        data=new ArrayList<Standings>();
        adapter=new ListScoreboardAdapater(getActivity(),data);
        listView.setAdapter(adapter);
    }
    @Override
    public void requestData(){
        if (Util.isNull(application.getGameTeam())) return;
        application.execute(RequestUtil.requestList(application.getToken().getAccess_token()),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String json, Response res) {
               List<Standings> temp=JSON.parseArray(json,Standings.class);
                if (!Util.isNull(temp)){
                    data.clear();
                    data.addAll(temp);
                    adapter.notifyDataSetInvalidated();
                }
                pullListView.onPullDownRefreshComplete();
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                application.networkErrorMessage(e,res);
                pullListView.onPullDownRefreshComplete();
            }
        });

    }

}
