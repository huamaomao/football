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
import com.vxfc.shenxin.model.AgainstVo;
import com.vxfc.shenxin.model.Formation;
import com.vxfc.shenxin.model.RecentGameTeam;
import com.vxfc.shenxin.ui.TeamPlayerActivity;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Team;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.widget.FormationView;

import java.util.HashMap;
import java.util.Map;

/**
 * *
 * 首发阵容
 */
public class LiveLineupFragment extends BaseFragment {
    private FormationView fl_view;
    private RecentGameTeam team;
    private TextView tv_item_0,tv_item_1,tv_item_2;
    private AgainstVo aAgainstVo,bAgainstVo;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LiveMainFragment  fragment=(LiveMainFragment) getParentFragment();
        team=fragment.team;
        setLayoutId(R.layout.f_live_lineup);
    }

    protected void initView(View view, LayoutInflater inflater) {
        fl_view = (FormationView) view.findViewById(R.id.fl_view);
        tv_item_0=(TextView)view.findViewById(R.id.tv_item_0);
        tv_item_1=(TextView)view.findViewById(R.id.tv_item_1);
        tv_item_2=(TextView)view.findViewById(R.id.tv_item_2);

        fl_view.setOnItemClickListener(new FormationView.OnItemClickListener() {
            @Override
            public void onItemClick( AgainstVo.Item item, boolean flag) {
                if (Util.isNull(item)||(Util.isEmpty(item.getFormationPlace()))){

                    return;
                }
                openActivity(TeamPlayerActivity.class,item.getPlayerId(),item.getPlayerName());
            }

            @Override
            public void onTeamFormation(boolean flag) {
                if (flag&&Util.notNull(aAgainstVo)){
                    tv_item_0.setText(aAgainstVo.getEntraineur());
                    tv_item_1.setText(aAgainstVo.getArbitre());
                    tv_item_2.setText(aAgainstVo.getFormationUsed());
                    fl_view.resIcon= Team.getTeamIconT(team.getTeamAId());
                    fl_view.setTeamData(aAgainstVo.team, new Formation(aAgainstVo.getFormationUsed()));
                }else if (Util.notNull(bAgainstVo)){
                    tv_item_0.setText(bAgainstVo.getEntraineur());
                    tv_item_1.setText(bAgainstVo.getArbitre());
                    tv_item_2.setText(bAgainstVo.getFormationUsed());
                    fl_view.resIcon= Team.getTeamIconT(team.getTeamBId());
                    fl_view.setTeamData(bAgainstVo.team,new Formation(bAgainstVo.getFormationUsed()));
                }
            }
        });


    }

    @Override
    protected void requestData() {
        if (Util.isNull(team))return;
        fl_view.resIcon= Team.getTeamIconT(team.getTeamAId());
        super.requestData();
        application.execute(RequestUtil.requestAgainst(application.getToken().getAccess_token(),team.getId(),team.getTeamAId()),new HttpModelHandler<String>() {
                @Override
                protected void onSuccess(String data, Response res) {
                    AgainstVo vo= JSON.parseObject(data,AgainstVo.class);
                    if (Util.notNull(vo)){
                        tv_item_0.setText(vo.getEntraineur());
                        tv_item_1.setText(vo.getArbitre());
                        tv_item_2.setText(vo.getFormationUsed());
                        Map<Integer,AgainstVo.Item> map=new HashMap<Integer, AgainstVo.Item>();
                       if (Util.notNull(vo.getItems())){
                           for(AgainstVo.Item item:vo.getItems()){
                               map.put(Util.parseInt(item.getFormationPlace()),item);
                           }
                       }
                        vo.team=map;
                        fl_view.setTeamData(map,new Formation(vo.getFormationUsed()));
                        aAgainstVo=vo;
                    }
                }

                @Override
                protected void onFailure(HttpException e, Response res) {
                    application.networkErrorMessage(e,res);
                }
        });
        application.execute(RequestUtil.requestAgainst(application.getToken().getAccess_token(),team.getId(),team.getTeamBId()),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                AgainstVo vo= JSON.parseObject(data,AgainstVo.class);
                if (Util.notNull(vo)){
                    Map<Integer,AgainstVo.Item> map=new HashMap<Integer, AgainstVo.Item>();
                    if (Util.notNull(vo.getItems())){
                        for(AgainstVo.Item item:vo.getItems()){
                            map.put(Util.parseInt(item.getFormationPlace()),item);
                        }
                    }
                    vo.team=map;
                    bAgainstVo=vo;
                }
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                application.networkErrorMessage(e,res);
            }
        });
    }


}

