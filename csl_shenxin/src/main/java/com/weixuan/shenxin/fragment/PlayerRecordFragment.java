package com.weixuan.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.adapter.QuickAdapter;
import com.weixuan.shenxin.entity.PlayerRecordVo;
import com.weixuan.shenxin.ui.TeamPlayerActivity;
import com.weixuan.shenxin.util.RequestUtil;
import com.weixuan.shenxin.util.Util;
import com.weixuan.shenxin.util.ViewHolderHelp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hua on 2014/8/13.
 */
public class PlayerRecordFragment extends BaseListFragment {
    private TextView tv_item_0, tv_item_1, tv_item_2, tv_item_3, tv_item_4, tv_item_5;
    private TeamPlayerActivity activity;
    private int type = 0;
    public RadioGroup rgGroup;
    private List<PlayerRecordVo> lsData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (TeamPlayerActivity) getActivity();
        lsData = new ArrayList<PlayerRecordVo>();
        setLayoutId(R.layout.f_player_record);
    }

    @Override
    protected void initView(View view, LayoutInflater inflater) {
        rgGroup=(RadioGroup)view.findViewById(R.id.rg_group);
        tv_item_0 = (TextView) view.findViewById(R.id.tv_item_0);
        tv_item_1 = (TextView) view.findViewById(R.id.tv_item_1);
        tv_item_2 = (TextView) view.findViewById(R.id.tv_item_2);
        tv_item_3 = (TextView) view.findViewById(R.id.tv_item_3);
        tv_item_4 = (TextView) view.findViewById(R.id.tv_item_4);
        rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
        rgGroup.check(R.id.rbtn_left_menu_0);
        setTitleView();
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_left_menu_0:
                        type = 0;
                        break;
                    case R.id.rbtn_left_menu_1:
                        type = 1;
                        break;
                    case R.id.rbtn_left_menu_2:
                        type = 2;
                        break;
                    case R.id.rbtn_left_menu_3:
                        break;
                }
                setTitleView();
                doPullRefreshing();
            }
        });
        initListView(view);

    }



    @Override
    protected void requestData() {
        if (lsData.size()>0){
            setData(null);
            pullListView.onPullDownRefreshComplete();
            return;
        }
        setTitleView();
        application.execute(RequestUtil.requestPlayerRecord(application.getToken().getAccess_token(), activity.getPlayerId()), new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                List<PlayerRecordVo> temp = JSON.parseArray(data, PlayerRecordVo.class);
                setData(temp);
                pullListView.onPullDownRefreshComplete();
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                application.networkErrorMessage(e, res);
                pullListView.onPullDownRefreshComplete();
            }
        });
    }

    private void setTitleView(){
        switch (type) {
            case 0:
                tv_item_0.setText("出场");
                tv_item_1.setText("时间");
                tv_item_2.setText("助攻");
                tv_item_3.setText("触球");
                tv_item_4.setText("传中");
                break;
            case 1:
                tv_item_0.setText("射门");
                tv_item_1.setText("进球");
                tv_item_2.setText("越位");
                tv_item_3.setText("过人");
                tv_item_4.setText("角球");
                break;
            case 2:
                tv_item_0.setText("黄牌");
                tv_item_1.setText("红牌");
                tv_item_2.setText("犯规");
                tv_item_3.setText("护球");
                tv_item_4.setText("解围");
                break;
        }
    }

    private void setData(List<PlayerRecordVo> temp) {
        if (Util.notNull(temp)) {
            lsData.clear();
            lsData.addAll(temp);
        }
        switch (type) {
            case 0:
                adapter = new QuickAdapter<PlayerRecordVo>(getActivity(), R.layout.list_item_player_record, lsData) {
                    @Override
                    protected void convert(ViewHolderHelp helper, PlayerRecordVo item) {

                        helper.setText(R.id.tv_item_1, item.getNumber())
                                .setTextInt(R.id.tv_item_2, item.getMinsPlayed())
                                .setTextInt(R.id.tv_item_3, item.getGoalAssist())
                                .setTextInt(R.id.tv_item_4, item.getTouches())
                                .setTextInt(R.id.tv_item_5, item.getTotalCross())
                                .setText(R.id.tv_item_0,item.getYear());

                    }
                };

                break;
            case 1:
                adapter = new QuickAdapter<PlayerRecordVo>(getActivity(), R.layout.list_item_player_record, lsData) {
                    @Override
                    protected void convert(ViewHolderHelp helper, PlayerRecordVo item) {
                        helper.setText(R.id.tv_item_1, item.getTotalScoringAtt())
                                .setTextInt(R.id.tv_item_2, item.getGoals())
                                .setTextInt(R.id.tv_item_3, item.getTotalOffside())
                                .setTextInt(R.id.tv_item_4, item.getTotalContest())
                                .setTextInt(R.id.tv_item_5, item.getCornerTaken())
                                .setText(R.id.tv_item_0,item.getYear());

                    }
                };
                break;
            case 2:
                adapter = new QuickAdapter<PlayerRecordVo>(getActivity(), R.layout.list_item_player_record, lsData) {
                    @Override
                    protected void convert(ViewHolderHelp helper, PlayerRecordVo item) {
                        helper.setTextInt(R.id.tv_item_1, item.getYellowCard())
                                .setTextInt(R.id.tv_item_2, item.getShieldBallOop())
                                .setTextInt(R.id.tv_item_3, item.getTotalClearance())
                                .setTextInt(R.id.tv_item_4, item.getFouls())
                                .setTextInt(R.id.tv_item_5, item.getTotalTackle())
                                .setText(R.id.tv_item_0,item.getYear());

                    }
                };
                break;
        }
        listView.setAdapter(adapter);
    }
}
