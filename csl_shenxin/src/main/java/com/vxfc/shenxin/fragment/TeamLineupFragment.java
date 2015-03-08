package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.TextView;
import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import butterknife.InjectView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 *
 *
 */
public class TeamLineupFragment extends BaseFragment{
    @InjectView(R.id.rg_group) RadioGroup rgGroup;
    private FancyCoverFlow fancyCoverFlow;
    private List<Player> playerList;
    private Map<String,List<Player>> playerMap;
    @InjectView(R.id.tv_player)  TextView tvPlayer;
    @InjectView(R.id.tv_number)  TextView tvNumber;
    private String type=Dict.POSITION_STRIKER;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_team_a);
        playerList=new ArrayList<Player>();
        playerMap=new HashMap<String,List<Player>>();
    }

    @Override
    protected void requestData() {
        final PublicParam param=new PublicParam();
        param.teamId=application.teamId;
        param.type="0";
        execute(RequestUtil.requestFirstTeamPlayer(param),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                Log.i(data);
                if (!CommonUtil.isEmpty(data)){
                    playerList= JSON.parseArray(data,Player.class);
                    initPlayerTypeList();
                    fancyCoverFlow.setAdapter(new TeamFancyCoverFlowAdapter(playerMap.get(type)));
                    fancyCoverFlow.setSelection(playerMap.get(type).size()/2);
                }
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                ResponseModel model= Util.handleServerException(e,(BaseActivity)getActivity());
                Log.i(model);
            }
        });
    }

    /****
     * 初始化
     * @param view
     * @param inflater
     */
	protected void initView(View view, LayoutInflater inflater) {
        rgGroup.check(R.id.rbtn_left_menu_1);
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtn_left_menu_1:
                      type=Dict.POSITION_STRIKER;
                      if (playerList.size()==0){
                          doRefresh();
                          return;
                      }
                     fancyCoverFlow.setAdapter(new TeamFancyCoverFlowAdapter(playerMap.get(type)));
                     fancyCoverFlow.setSelection(playerMap.get(type).size()/2);
                    break;
                    case R.id.rbtn_left_menu_2:
                        type=Dict.POSITION_MIDFIELDER;
                        if (playerList.size()==0){
                            doRefresh();
                            return;
                        }
                        fancyCoverFlow.setAdapter(new TeamFancyCoverFlowAdapter(playerMap.get(type)));
                        fancyCoverFlow.setSelection(playerMap.get(type).size()/2);
                    break;
                    case R.id.rbtn_left_menu_3:
                        type=Dict.POSITION_DEFENDER;
                        if (playerList.size()==0){
                            doRefresh();
                            return;
                        }
                        fancyCoverFlow.setAdapter(new TeamFancyCoverFlowAdapter(playerMap.get(type)));
                        fancyCoverFlow.setSelection(playerMap.get(type).size()/2);
                    break;
                    case R.id.rbtn_left_menu_4:
                        type=Dict.POSITION_GOALKEEPER;
                        if (playerList.size()==0){
                            doRefresh();
                            return;
                        }
                        fancyCoverFlow.setAdapter(new TeamFancyCoverFlowAdapter(playerMap.get(type)));
                        fancyCoverFlow.setSelection(playerMap.get(type).size()/2);
                    break;
                }
            }
        });

        fancyCoverFlow=(FancyCoverFlow)view.findViewById(R.id.fancyCoverFlow);
        fancyCoverFlow.setSpacing(-70);
        fancyCoverFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Player player=playerMap.get(type).get(position);
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
                Player player=playerMap.get(type).get(i);
                if (CommonUtil.notNull(player)){
                    Bundle bundle=new Bundle();
                    bundle.putString(Dict.ID,player.getId());
                    bundle.putString(Dict.PLAYER_NAME,player.getCname());
                    Util.openActivity(TeamPlayerActivity.class, bundle, getActivity(), ActivityModel.ACTIVITY_MODEL_1);
                }

            }
        });

	}


    public void initPlayerTypeList(){
        List<Player> temp1=new ArrayList<Player>();
        List<Player> temp2=new ArrayList<Player>();
        List<Player> temp3=new ArrayList<Player>();
        List<Player> temp4=new ArrayList<Player>();
        for (Player p:playerList){
            if (p.getPosition().equals(Dict.POSITION_STRIKER)){
                temp1.add(p);
            }else if(p.getPosition().equals(Dict.POSITION_MIDFIELDER)){
                temp2.add(p);
            }else if(p.getPosition().equals(Dict.POSITION_DEFENDER)){
                temp3.add(p);
            }else if(p.getPosition().equals(Dict.POSITION_GOALKEEPER)){
                temp4.add(p);
            }
        }
        playerMap.put(Dict.POSITION_STRIKER,temp1);
        playerMap.put(Dict.POSITION_MIDFIELDER,temp2);
        playerMap.put(Dict.POSITION_DEFENDER,temp3);
        playerMap.put(Dict.POSITION_GOALKEEPER,temp4);

    }


}
