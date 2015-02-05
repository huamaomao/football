package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.ListClubHistoryCAdapater;
import com.vxfc.shenxin.model.HistoricalRankingsVo;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;

import java.util.ArrayList;
import java.util.List;

/****
 * 新闻/公告
 *
 */
public class ClubTeamHistoryCFragment extends BaseListFragment {
    private TextView tv_type;
    private List<HistoricalRankingsVo> lsData;
    private ClubTeamHistoryFragment  fragment;
    private int index=0;

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_club_h_menu3);
        fragment=(ClubTeamHistoryFragment) getParentFragment();
        fragment.listenerMap.put(2, new ClubTeamHistoryFragment.OnItemClickListener() {
            @Override
            public void onItemClick(int index) {
                doPullRefreshing();
                ClubTeamHistoryCFragment.this.index=index;
            }
        });
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
	public  void initView(View view, LayoutInflater inflater) {
        tv_type=(TextView)view.findViewById(R.id.tv_type);
        initListView(view);
        lsData=new ArrayList<HistoricalRankingsVo>();
        adapter=new ListClubHistoryCAdapater(getActivity(),lsData);
        listView.setAdapter(adapter);
	}

    @Override
    protected void requestData() {
        application.execute(RequestUtil.requestHistoricalRankings(application.getToken().getAccess_token(), application.teamId,fragment.getSelectionValue()),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                List<HistoricalRankingsVo> temp= JSON.parseArray(data, HistoricalRankingsVo.class);
                if(!Util.isNull(temp)){
                    initList(temp);
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

    /***
     * 去除进球0
     */
    private  void  initList(List<HistoricalRankingsVo> temp){
        lsData.clear();
        for (HistoricalRankingsVo vo:temp){
            if (!Util.isEmpty(vo.getGoals())){
                lsData.add(vo);
            }
        }
    }

}
