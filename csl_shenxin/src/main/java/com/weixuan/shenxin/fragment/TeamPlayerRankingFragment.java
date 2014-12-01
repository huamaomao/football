package com.weixuan.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.adapter.ListPlayerRankingAdapater;
import com.weixuan.shenxin.entity.PlayerRankingVo;
import com.weixuan.shenxin.util.RequestUtil;
import com.weixuan.shenxin.util.Util;
import com.weixuan.shenxin.view.SpinnerView;

import java.util.ArrayList;
import java.util.List;

/****
 * 球员排行
 *
 */
public class TeamPlayerRankingFragment extends BaseListFragment{

    private SpinnerView spinnerTeam,spinnerRound;
    private List<PlayerRankingVo> lsData;
    private String requestYear=null;
    private Integer type=0;
    private TextView tv_item_0,tv_item_1,tv_item_2;
    private View iv_item_0,iv_item_1;
    private List<PlayerRankingVo> tempData;

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_team_player_ranking);
	}

    /****
     * 初始化
     * @param view
     * @param inflater
     */
	public void initView(View view, LayoutInflater inflater) {
        pullDownListView(view);
        spinnerTeam=(SpinnerView)view.findViewById(R.id.spinner_team);
        spinnerRound=(SpinnerView)view.findViewById(R.id.spinner_round);
        tv_item_0=(TextView)view.findViewById(R.id.tv_item_0);
        tv_item_1=(TextView)view.findViewById(R.id.tv_item_1);
        tv_item_2=(TextView)view.findViewById(R.id.tv_item_2);
        iv_item_0=view.findViewById(R.id.iv_item_0);
        iv_item_1=view.findViewById(R.id.iv_item_1);

        tv_item_2.setVisibility(View.GONE);
        iv_item_1.setVisibility(View.GONE);
        spinnerTeam.setItems(Util.getYearList(2012));
        spinnerTeam.setSelection(0);
        spinnerTeam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spinnerTeam.setSelection(position);
                spinnerTeam.dismiss();
                doPullRefreshing();
            }
        });
        spinnerRound.setItems(R.array.type);
        spinnerRound.setSelection(0);
        spinnerRound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spinnerRound.setSelection(position);
                spinnerRound.dismiss();
                doPullRefreshing();
            }
        });

        lsData=new ArrayList<PlayerRankingVo>();
        adapter=new ListPlayerRankingAdapater(getActivity(),lsData);
        listView.setAdapter(adapter);

    }

    @Override
    protected void requestData() {
        super.requestData();
        String year=spinnerTeam.getItemValue();
        type=spinnerRound.getSelectionIndex();
        if (!Util.isNull(requestYear)&&(year.equals(requestYear)&&tempData!=null&&tempData.size()>0)){
            typeView(type);
            pullListView.onPullDownRefreshComplete();
            return;
        }
        requestYear=year;
        application.execute(RequestUtil.requestPlayerBankings(application.getToken().getAccess_token(),application.teamId,year),
                new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String json, Response res) {
                List<PlayerRankingVo> temp=JSON.parseArray(json,PlayerRankingVo.class);
                if (!Util.isNull(temp)){
                    tempData=temp;
                    typeView(type);
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

    private void typeView(int type){
        ((ListPlayerRankingAdapater)adapter).setType(type);
        tv_item_2.setVisibility(View.GONE);
        iv_item_1.setVisibility(View.GONE);
        if (type<3){
            tv_item_1.setVisibility(View.VISIBLE);
            iv_item_0.setVisibility(View.VISIBLE);
        }else {
            tv_item_1.setVisibility(View.GONE);
            iv_item_0.setVisibility(View.GONE);
        }
        switch (type){
            case 0:
                tv_item_0.setText("射门数");
                tv_item_1.setText("头球");
                lsData.clear();
                for (PlayerRankingVo vo:tempData){
                    if (Util.not0(vo.getTotalScoringAtt())){
                        lsData.add(vo);
                    }
                }
                Util.sort(lsData,"totalScoringAtt",false);
                break;
            case 1:
                tv_item_2.setVisibility(View.VISIBLE);
                iv_item_1.setVisibility(View.VISIBLE);
                tv_item_0.setText("进球");
                tv_item_1.setText("点球");
                tv_item_2.setText("乌龙球");
                lsData.clear();
                for (PlayerRankingVo vo:tempData){
                    if (Util.not0(vo.getGoals())){
                        lsData.add(vo);
                    }
                }
                Util.sort(lsData,"goals",false);
                break;
            case 2:
                tv_item_0.setText("黄牌数");
                tv_item_1.setText("红牌数");
                lsData.clear();
                for (PlayerRankingVo vo:tempData){
                    if (Util.not0(vo.getYellowCard())){
                        lsData.add(vo);
                    }
                }
                Util.sort(lsData,"yellowCard",false);
                break;
            case 3:
                tv_item_0.setText("传球数");
                lsData.clear();
                for (PlayerRankingVo vo:tempData){
                    if (Util.not0(vo.getTotalPass())){
                        lsData.add(vo);
                    }
                }
                Util.sort(lsData,"totalPass",false);
                break;
            case 4:
                tv_item_0.setText("助攻数");
                lsData.clear();
                for (PlayerRankingVo vo:tempData){
                    if (Util.not0(vo.getGoalAssist())){
                        lsData.add(vo);
                    }
                }
                Util.sort(lsData,"goalAssist",false);
                break;
            case 5:
                tv_item_0.setText("传中数");
                lsData.clear();
                for (PlayerRankingVo vo:tempData){
                    if (Util.not0(vo.getTotalCross())){
                        lsData.add(vo);
                    }
                }
                Util.sort(lsData,"totalCross",false);
                break;
            case 6:
                tv_item_0.setText("抢断数");
                lsData.clear();
                for (PlayerRankingVo vo:tempData){
                    if (Util.not0(vo.getTotalTackle())){
                        lsData.add(vo);
                    }
                }
                Util.sort(lsData,"totalTackle",false);
                break;
            case 7:
                tv_item_0.setText("解围数");
                lsData.clear();
                for (PlayerRankingVo vo:tempData){
                    if (Util.not0(vo.getTotalClearance())){
                        lsData.add(vo);
                    }
                }
                Util.sort(lsData,"totalClearance",false);
                break;
            case 8:
                tv_item_0.setText("犯规数");
                lsData.clear();
                for (PlayerRankingVo vo:tempData){
                    if (Util.not0(vo.getFouls())){
                        lsData.add(vo);
                    }
                }
                Util.sort(lsData,"fouls",false);
                break;
            case 9:
                tv_item_0.setText("被侵犯数");
                lsData.clear();
                for (PlayerRankingVo vo:tempData){
                    if (Util.not0(vo.getWasFouled())){
                        lsData.add(vo);
                    }
                }
                Util.sort(lsData,"wasFouled",false);
                break;
            case 10:
                tv_item_0.setText("扑救数");
                lsData.clear();
                for (PlayerRankingVo vo:tempData){
                    if (Util.not0(vo.getDivingSave())){
                        lsData.add(vo);
                    }
                }
                Util.sort(lsData,"divingSave",false);
                break;
        }
        adapter.notifyDataSetChanged();
    }

}
