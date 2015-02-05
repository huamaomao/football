package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.QuickAdapter;
import com.vxfc.shenxin.model.PlayerAttackDataVo;
import com.vxfc.shenxin.model.PlayerDefenseDataVo;
import com.vxfc.shenxin.model.PlayerGeneralDataVo;
import com.vxfc.shenxin.ui.TeamPlayerActivity;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Team;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.util.ViewHolderHelp;
import com.vxfc.shenxin.widget.SpinnerView;

import java.util.List;

public class PlayerGameStatsFragment extends BaseListFragment {
    private RadioGroup rgGroup;
    private TextView  tv_item_0,tv_item_1,tv_item_2,tv_item_3,tv_item_4,tv_item_5,tv_item_6,tv_item_7;
    private SpinnerView tv_spinner;
    private int type=0;
   private TeamPlayerActivity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=(TeamPlayerActivity)getActivity();
        setLayoutId(R.layout.f_player_game_stats);
    }
    @Override
    protected void initView(View view, LayoutInflater inflater) {
        initListView(view);
        tv_item_0=(TextView)view.findViewById(R.id.tv_item_0);
        tv_item_1=(TextView)view.findViewById(R.id.tv_item_1);
        tv_item_2=(TextView)view.findViewById(R.id.tv_item_2);
        tv_item_3=(TextView)view.findViewById(R.id.tv_item_3);
        tv_item_4=(TextView)view.findViewById(R.id.tv_item_4);
        tv_item_5=(TextView)view.findViewById(R.id.tv_item_5);
        tv_item_6=(TextView)view.findViewById(R.id.tv_item_6);
        tv_item_7=(TextView)view.findViewById(R.id.tv_item_7);
        tv_spinner=(SpinnerView)view.findViewById(R.id.spinner_team);
        tv_spinner.setItems(Util.getYearList(2012));
        tv_spinner.setSelection(0);
        tv_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_spinner.setSelection(position);
                doPullRefreshing();
            }
        });

        rgGroup=(RadioGroup)view.findViewById(R.id.rg_group);
        rgGroup.check(R.id.rbtn_left_menu_0);
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtn_left_menu_0:
                        type=0;
                        break;
                    case R.id.rbtn_left_menu_1:
                        type=1;
                        break;
                    case R.id.rbtn_left_menu_2:
                        type=2;
                        break;
                    case R.id.rbtn_left_menu_3:
                        break;
                }
                doPullRefreshing();
            }
        });
    }

    @Override
    protected void requestData() {
        super.requestData();
        String year=tv_spinner.getItemValue();
        switch (type){
            case 0:
                tv_item_0.setText("出场");
                tv_item_1.setText("过人");
                tv_item_2.setText("传中");
                tv_item_3.setText("出场时间");
                application.execute(RequestUtil.requestPlayerGeneralData(application.getToken().getAccess_token(), activity.getPlayerId(), year),
                        new HttpModelHandler<String>() {
                    @Override
                    protected void onSuccess(String json, Response res) {

                        List<PlayerGeneralDataVo> temp= JSON.parseArray(json,PlayerGeneralDataVo.class);
                        if (!Util.isNull(temp)){
                            quickAdapter=new QuickAdapter<PlayerGeneralDataVo>(getActivity(),R.layout.listview_item_player,temp) {
                                @Override
                                protected void convert(ViewHolderHelp helper, PlayerGeneralDataVo item) {
                                   helper.setTextInt(R.id.tv_item_0,item.getRound())
                                         .setTextInt(R.id.tv_item_1, Team.getPlayerType(item.getIsFirst()))
                                         .setTextInt(R.id.tv_item_2,Util.initTextValue(item.getTotalContest()))
                                         .setTextInt(R.id.tv_item_3,Util.initTextValue(item.getTotalCross()))
                                         .setText(R.id.tv_item_4,Util.initTextValue(item.getMinsPlayed()));
                                }
                            };
                            listView.setAdapter(quickAdapter);
                            tv_item_4.setText(String.valueOf(temp.size()));
                            int count1=0,count2=0,count3=0;
                            for (PlayerGeneralDataVo v:temp) {
                                count1+=Util.parseInt(v.getTotalContest());
                                count2+=Util.parseInt(v.getTotalCross());
                                count3+=Util.parseInt(v.getMinsPlayed());
                            }
                            tv_item_5.setText(String.valueOf(count1));
                            tv_item_6.setText(String.valueOf(count2));
                            tv_item_7.setText(String.valueOf(count3));
                        }
                        pullListView.onPullDownRefreshComplete();

                    }

                    @Override
                    protected void onFailure(HttpException e, Response res) {
                        pullListView.onPullDownRefreshComplete();
                        application.networkErrorMessage(e,res);
                    }
                });
                break;
            case 1:
                tv_item_0.setText("射门");
                tv_item_1.setText("越位");
                tv_item_2.setText("进球");
                tv_item_3.setText("助攻");
                application.execute(RequestUtil.requestPlayerAttackData(application.getToken().getAccess_token(), activity.getPlayerId(), year),
                        new HttpModelHandler<String>() {
                            @Override
                            protected void onSuccess(String json, Response res) {
                                List<PlayerAttackDataVo> temp= JSON.parseArray(json,PlayerAttackDataVo.class);
                                if (!Util.isNull(temp)){
                                    quickAdapter=new QuickAdapter<PlayerAttackDataVo>(getActivity(),R.layout.listview_item_player,temp) {
                                        @Override
                                        protected void convert(ViewHolderHelp helper, PlayerAttackDataVo item) {
                                            helper.setText(R.id.tv_item_0,item.getRound())
                                                    .setText(R.id.tv_item_1,Util.initTextValue(item.getTotalScoringAtt()))
                                                    .setText(R.id.tv_item_2,Util.initTextValue(item.getTotalOffside()))
                                                    .setText(R.id.tv_item_3,Util.initTextValue(item.getGoals()))
                                                    .setText(R.id.tv_item_4,Util.initTextValue(item.getGoalAssist()));
                                        }
                                    };
                                    listView.setAdapter(quickAdapter);
                                    int count0=0,count1=0,count2=0,count3=0;
                                    for (PlayerAttackDataVo v:temp) {
                                        count0+=Util.parseInt(v.getTotalScoringAtt());
                                        count1+=Util.parseInt(v.getTotalOffside());
                                        count2+=Util.parseInt(v.getGoals());
                                        count3+=Util.parseInt(v.getGoalAssist());
                                    }
                                    tv_item_4.setText(String.valueOf(count0));
                                    tv_item_5.setText(String.valueOf(count1));
                                    tv_item_6.setText(String.valueOf(count2));
                                    tv_item_7.setText(String.valueOf(count3));
                                }
                                pullListView.onPullDownRefreshComplete();
                            }

                            @Override
                            protected void onFailure(HttpException e, Response res) {
                                pullListView.onPullDownRefreshComplete();
                                application.networkErrorMessage(e,res);
                            }
                        });
                break;
            case 2:
                tv_item_0.setText("护球");
                tv_item_1.setText("抢断");
                tv_item_2.setText("黄牌");
                tv_item_3.setText("红牌");
                application.execute(RequestUtil.requestPlayerDefenseData(application.getToken().getAccess_token(), activity.getPlayerId(), year),
                        new HttpModelHandler<String>() {
                            @Override
                            protected void onSuccess(String json, Response res) {
                                List<PlayerDefenseDataVo> temp= JSON.parseArray(json,PlayerDefenseDataVo.class);
                                if (!Util.isNull(temp)){
                                    quickAdapter=new QuickAdapter<PlayerDefenseDataVo>(getActivity(),R.layout.listview_item_player,temp) {
                                        @Override
                                        protected void convert(ViewHolderHelp helper, PlayerDefenseDataVo item) {
                                            helper.setText(R.id.tv_item_0,item.getRound())
                                                    .setText(R.id.tv_item_1,Util.initTextValue(item.getShieldBallOop()))
                                                    .setText(R.id.tv_item_2, Util.initTextValue(item.getTotalTackle()))
                                                    .setText(R.id.tv_item_3, Util.initTextValue(item.getYellowCard()))
                                                    .setText(R.id.tv_item_4,Util.initTextValue(item.getRedCard()));
                                        }
                                    };
                                    listView.setAdapter(quickAdapter);
                                    int count0=0,count1=0,count2=0,count3=0;
                                    for (PlayerDefenseDataVo v:temp) {
                                        count0+=Util.parseInt(v.getShieldBallOop());
                                        count1+=Util.parseInt(v.getTotalTackle());
                                        count2+=Util.parseInt(v.getYellowCard());
                                        count3+=Util.parseInt(v.getRedCard());
                                    }
                                    tv_item_4.setText(String.valueOf(count0));
                                    tv_item_5.setText(String.valueOf(count1));
                                    tv_item_6.setText(String.valueOf(count2));
                                    tv_item_7.setText(String.valueOf(count3));
                                }
                                pullListView.onPullDownRefreshComplete();
                            }

                            @Override
                            protected void onFailure(HttpException e, Response res) {
                                pullListView.onPullDownRefreshComplete();
                                application.networkErrorMessage(e,res);
                            }
                        });
                break;
        }
    }

}
