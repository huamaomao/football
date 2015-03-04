package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.ListClubHistoryAdapater;
import com.vxfc.shenxin.domian.PlayerListVo;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;

import java.util.ArrayList;
import java.util.List;

/****
 * 历史名单
 *
 */
public class ClubTeamHistoryAFragment extends BaseListFragment {

    private List<PlayerListVo> lsData;
    private ClubTeamHistoryFragment  fragment;
    private int index=0;

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_club_h_menu1);
        fragment=(ClubTeamHistoryFragment) getParentFragment();
	}

    @Override
    public void onResume() {
        super.onResume();
        fragment.setSelectionItem(index);
    }

    /****
     * 初始化
     * @param view
     * @param inflater
     */
	public void initView(View view, LayoutInflater inflater) {
        fragment.listenerMap.put(0, new ClubTeamHistoryFragment.OnItemClickListener() {
            @Override
            public void onItemClick(int index) {
                doPullRefreshing();
                ClubTeamHistoryAFragment.this.index=index;
            }
        });
        initListView(view);
        lsData=new ArrayList<PlayerListVo>();
        adapter=new ListClubHistoryAdapater(getActivity(),lsData);
        listView.setAdapter(adapter);
	}

    public  void  requestData(){
        application.execute(RequestUtil.requestPlayerList(application.getToken().getAccess_token(),application.teamId,
                fragment.getSelectionValue()),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                List<PlayerListVo> temp= JSON.parseArray(data,PlayerListVo.class);
                if(!Util.isNull(temp)){
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
        });
    }

}
