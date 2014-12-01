package com.weixuan.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.adapter.ListClubHistoryBAdapater;
import com.weixuan.shenxin.entity.HistoricalRankingsVo;
import com.weixuan.shenxin.util.RequestUtil;
import com.weixuan.shenxin.util.Util;

import java.util.ArrayList;
import java.util.List;

/****
 * 新闻/公告
 *
 */
public class ClubTeamHistoryBFragment extends BaseListFragment {
    private TextView tv_type;
    private  List<HistoricalRankingsVo> lsData;
    private ClubTeamHistoryFragment  fragment;
    private int index=0;

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_club_h_menu2);
        fragment=(ClubTeamHistoryFragment) getParentFragment();
        fragment.listenerMap.put(1, new ClubTeamHistoryFragment.OnItemClickListener() {
            @Override
            public void onItemClick(int index) {
                doPullRefreshing();
                ClubTeamHistoryBFragment.this.index=index;
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
	public void initView(View view, LayoutInflater inflater) {
        tv_type=(TextView)view.findViewById(R.id.tv_type);
        initListView(view);
        lsData=new ArrayList<HistoricalRankingsVo>();
        adapter=new ListClubHistoryBAdapater(getActivity(),lsData);
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
            if (!Util.isEmpty(vo.getNumber())){
                lsData.add(vo);
            }
        }
        Util.sort(lsData,"number",false);
    }
}
