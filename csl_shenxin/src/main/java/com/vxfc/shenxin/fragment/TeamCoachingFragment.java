package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.util.CommonUtil;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.TeamFancyCoverFlowAdapter;
import com.vxfc.shenxin.domian.Player;
import com.vxfc.shenxin.domian.ResponseModel;
import com.vxfc.shenxin.domian.param.PublicParam;
import com.vxfc.shenxin.ui.BaseActivity;
import com.vxfc.shenxin.ui.TeamPlayerActivity;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;

import java.util.ArrayList;
import java.util.List;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import butterknife.InjectView;

/****
 * 新闻/公告
 *
 */
public class TeamCoachingFragment extends BaseFragment{

    private FancyCoverFlow fancyCoverFlow;
    private List<Player> playerList;
    @InjectView(R.id.tv_player)TextView tvPlayer;
    @InjectView(R.id.tv_number)  TextView tvNumber;


    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_team_reserves);
        setLayoutId(R.layout.f_team_reserves);
        playerList=new ArrayList<Player>();
	}

    /****
     * 初始化
     * @param view
     * @param inflater
     */
	protected void initView(View view, LayoutInflater inflater) {
        fancyCoverFlow=(FancyCoverFlow)view.findViewById(R.id.fancyCoverFlow);
        fancyCoverFlow.setSpacing(-70);
        fancyCoverFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Player player=playerList.get(position);
                if (CommonUtil.notNull(player)){
                    tvNumber.setText(player.getNumber());
                    tvPlayer.setText(player.getCname());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fancyCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Player player=playerList.get(i);
                if (CommonUtil.notNull(player)){
                    Bundle bundle=new Bundle();
                    bundle.putString(Dict.ID,player.getId());
                    bundle.putString(Dict.PLAYER_NAME,player.getCname());
                    Util.openActivity(TeamPlayerActivity.class, bundle, getActivity(), ActivityModel.ACTIVITY_MODEL_1);
                }

            }
        });
	}

    @Override
    protected void requestData() {
        final PublicParam param=new PublicParam();
        param.teamId=application.teamId;
        execute(RequestUtil.requestCoachTeamList(param), new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                if (!CommonUtil.isEmpty(data)) {
                    playerList = JSON.parseArray(data, Player.class);
                    fancyCoverFlow.setAdapter(new TeamFancyCoverFlowAdapter(playerList));
                    fancyCoverFlow.setSelection(playerList.size() / 2);
                }
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                ResponseModel model = Util.handleServerException(e, (BaseActivity) getActivity());
                Log.i(model);
            }
        });
    }
}
