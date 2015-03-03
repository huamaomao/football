package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.ListLiveStartingAdapater;
import com.vxfc.shenxin.model.FistPlayerVo;
import com.vxfc.shenxin.model.RecentGameTeam;
import com.vxfc.shenxin.model.param.CslParam;
import com.vxfc.shenxin.ui.TeamPlayerActivity;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.widget.PullToRefreshBase;
import com.vxfc.shenxin.widget.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/****
 *直播-->首发
 *
 */
public class LiveStartingFragment extends BaseListFragment{
    private List<FistPlayerVo> aData;
    private List<FistPlayerVo> bData;
    private ListView teamList;
    private ListLiveStartingAdapater liveAdapater;
    private ListLiveStartingAdapater adapter;
    private PullToRefreshListView refreshListView;
    private RecentGameTeam team;
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_live_starting);
        LiveMainFragment  fragment=(LiveMainFragment) getParentFragment();
        team=fragment.team;
	}

    protected void initView(View view, LayoutInflater inflater) {
        refreshListView=(PullToRefreshListView)view.findViewById(R.id.ls_team);
        teamList=refreshListView.getRefreshableView();
        teamList.setDivider(getResources().getDrawable(R.drawable.ls_divider));
        teamList.setDividerHeight(2);
        refreshListView.setPullLoadEnabled(false);
        refreshListView.setScrollLoadEnabled(false);
        refreshListView.doPullRefreshing(true,0);
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                CslParam param=new CslParam();
                param.team_id=team.getTeamBId();
                param.fixture_id=team.getId();
                application.execute(RequestUtil.requestFistList(param),
                        new HttpModelHandler<String>() {
                            @Override
                            protected void onSuccess(String data, Response res) {
                                List<FistPlayerVo> temp = JSON.parseArray(data, FistPlayerVo.class);
                                if (temp != null) {
                                    bData.clear();
                                    bData.addAll(temp);
                                    liveAdapater.notifyDataSetChanged();
                                }
                                refreshListView.onPullDownRefreshComplete();

                            }

                            @Override
                            protected void onFailure(HttpException e, Response res) {
                                application.networkErrorMessage(e,res);
                                refreshListView.onPullDownRefreshComplete();
                            }
                        });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        pullDownListView(view);
        aData = new ArrayList<FistPlayerVo>();
        adapter = new ListLiveStartingAdapater(getActivity(), aData);
        listView.setAdapter(adapter);
        bData = new ArrayList<FistPlayerVo>();
        liveAdapater=new  ListLiveStartingAdapater(getActivity(), bData);
        teamList.setAdapter(liveAdapater);
                teamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        FistPlayerVo vo=bData.get(position);
                        Bundle bundle=new Bundle();
                        bundle.putString(Dict.ID,vo.getPlayerId());
                        bundle.putString(Dict.PLAYER_NAME,vo.getPlayerName());
                        Util.openActivity(TeamPlayerActivity.class, bundle, getActivity(), ActivityModel.ACTIVITY_MODEL_1);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FistPlayerVo vo=aData.get(position);
                Bundle bundle=new Bundle();
                bundle.putString(Dict.ID,vo.getPlayerId());
                bundle.putString(Dict.PLAYER_NAME,vo.getPlayerName());
                Util.openActivity(TeamPlayerActivity.class, bundle, getActivity(), ActivityModel.ACTIVITY_MODEL_1);
            }
        });

	}

    @Override
    protected void requestData() {
        CslParam param=new CslParam();
        param.team_id=team.getTeamBId();
        param.fixture_id=team.getId();
        application.execute(RequestUtil.requestFistList(param),
                new HttpModelHandler<String>() {
                    @Override
                    protected void onSuccess(String data, Response res) {
                        List<FistPlayerVo> temp = JSON.parseArray(data, FistPlayerVo.class);
                        if (temp != null) {
                            aData.clear();
                            aData.addAll(temp);
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
