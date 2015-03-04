package com.vxfc.shenxin.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.util.DateUtil;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.QuickAdapter;
import com.vxfc.shenxin.domian.RecentGameTeam;
import com.vxfc.shenxin.ui.LiveActivity;
import com.vxfc.shenxin.ui.MainActivity;
import com.vxfc.shenxin.util.*;
import com.vxfc.shenxin.widget.SpinnerView;

import java.util.*;

/****
 * 赛程
 *
 */
public class ScheduleFragment extends BaseListFragment {

   private SpinnerView spinner_team,spinner_round;
   private List<RecentGameTeam> lsData;
   private String fixtureId=null;
   private Team team=Team.ShenXin;
   public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        lsData=new ArrayList<RecentGameTeam>();
       if (Util.notNull(application.getGameTeam())){
           fixtureId=application.getGameTeam().getId();
       }
        adapter=new QuickAdapter<RecentGameTeam>(getActivity(),R.layout.list_item_schedule,lsData) {
            @Override
            protected void convert(ViewHolderHelp helper, RecentGameTeam item) {
                SpannableStringBuilder builder=new SpannableStringBuilder(item.getRound());
                builder.append(" ");
                int length=builder.length();
                builder.append(Team.getPlayStatus(item.getStatus()));
                if (Dict.STATUS_N.equals(item.getStatus())||Dict.STATUS_P.equals(item.getStatus())){
                    builder.setSpan(new ForegroundColorSpan(Color.GREEN), length, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
                }else if (Dict.STATUS_F.equals(item.getStatus())){
                    builder.setSpan(new ForegroundColorSpan(Color.WHITE), length, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
                }
                else{
                    builder.setSpan(new ForegroundColorSpan(Color.RED), length, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
                }
                helper.setText(R.id.tv_item_0,builder).
                        setText(R.id.tv_item_1, DateUtil.getStartTime(item.getDate(), item.getTime())).
                        setText(R.id.tv_item_2,item.getTeamAName()).
                        setText(R.id.tv_item_3,item.getScore()).
                        setText(R.id.tv_item_4,item.getTeamBName()).
                        setBackgroundRes(R.id.tv_item_2,Team.getTeamIconT(item.getTeamAId())).
                        setBackgroundRes(R.id.tv_item_4,Team.getTeamIconT(item.getTeamBId())).
                        setVisibleI(R.id.view_home, false).
                        setVisibleI(R.id.view_visiting, false);
                if (team.id.equals(item.getTeamAId())){
                    helper.setVisible(R.id.view_home,true)
                            .setBackgroundRes(R.id.view_home, Team.getScore(item.getResult(), true));

                }else if(team.id.equals(item.getTeamBId())){
                    helper.setVisible(R.id.view_visiting,true)
                            .setBackgroundRes(R.id.view_visiting, Team.getScore(item.getResult(), false));
                }
            }
        };
        setLayoutId(R.layout.f_schedule);
	}



    /****
     * 初始化
     * @param view
     * @param inflater
     */
    protected void initView(View view, LayoutInflater inflater) {
        spinner_team=(SpinnerView)view.findViewById(R.id.spinner_team);
        spinner_team.setItems(Team.values());
        spinner_team.setSelection(1);
        spinner_team.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spinner_team.setSelection(position);
                if (position==0){
                    spinner_round.setSelection(Integer.valueOf(application.getGameTeam().getRound()));
                }
                team=(Team)spinner_team.getSelectionItem();
                spinner_team.dismiss();
                doPullRefreshing();
            }
        });
        spinner_round=(SpinnerView)view.findViewById(R.id.spinner_round);
        spinner_round.setItems(R.array.round);
        spinner_round.setSelection(0);
        spinner_round.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spinner_round.setSelection(position);
                if (position==0&&Team.All.id.equals(team.id)){
                    spinner_team.setSelection(1);
                }
                spinner_round.dismiss();
                doPullRefreshing();
            }
        });
        pullDownListView(view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               final  RecentGameTeam team= lsData.get(position);
                Log.d(TAG,team.toString());
                if (Util.notNull(team)){
                    if (Dict.STATUS_N.equals(team.getStatus())){
                        return;
                    }else if (team.getId().equals(fixtureId)){
                      MainActivity activity =(MainActivity)getActivity();
                     // activity.getLeftFragment().setLiveMenu();
                    }else{
                        Bundle bundle=new Bundle();
                        bundle.putSerializable(Dict.SERIALIZABLE,team);
                        Util.openActivity(LiveActivity.class,bundle,getActivity(), ActivityModel.ACTIVITY_MODEL_1);
                    }

                }
            }
        });

	}

    @Override
    protected   void  requestData(){
        final Team team=(Team)spinner_team.getSelectionItem();
        String round=spinner_round.getSelectionIndex()==0?"":String.valueOf(spinner_round.getSelectionIndex());
        application.execute(RequestUtil.requestFixtureList(application.getToken().getAccess_token(),team.id.equals(Team.All.id)?null:team.id,round,"2014"),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                List<RecentGameTeam> temp= JSON.parseArray(data, RecentGameTeam.class);
                if(team!=null){
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
