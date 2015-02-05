package com.vxfc.shenxin.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.ListVsAdapater;
import com.vxfc.shenxin.model.AgainstHistorical;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;

/****
 * 交战历史
 *
 */
public class MVsFragment extends BaseListFragment {

    private TextView title_vs;
    private AgainstHistorical againstHistorical;

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_m_vs);
	}


    @Override
    protected void requestData(){
        if (Util.isNull(application.getGameTeam())) return;
        application.execute(RequestUtil.requestWarHistory(application.getToken().getAccess_token(),
                application.getGameTeam().getTeamAId(),application.getGameTeam().getTeamBId()),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String json, Response res) {
                   againstHistorical =JSON.parseObject(json, AgainstHistorical.class);;
                   adapter=new ListVsAdapater(getActivity(),againstHistorical.getItems());
                    listView.setAdapter(adapter);
                    SpannableStringBuilder builder=new SpannableStringBuilder("双方历史共交战");
                    builder.append(againstHistorical.getNum());
                    builder.append("次，");
                    builder.append(application.getGameTeam().getTeamAName());
                    int length_0=builder.toString().length();
                    builder.append("胜");
                    builder.append(againstHistorical.getWin());
                    builder.append("次");
                    int length_1=builder.toString().length();
                    builder.append(" ");
                    int length_2=builder.toString().length();
                    builder.append("平");
                    builder.append(againstHistorical.getPing());
                    builder.append("次");
                    int length_3=builder.toString().length();
                    builder.append(" ");
                    int length_4=builder.toString().length();
                    builder.append("负");
                    builder.append(againstHistorical.getFu());
                    builder.append("次");
                    int length_5=builder.toString().length();
                    builder.setSpan(new ForegroundColorSpan(Color.RED), length_0, length_1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
                    builder.setSpan(new ForegroundColorSpan(Color.GREEN), length_2, length_3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
                    builder.setSpan(new ForegroundColorSpan(Color.YELLOW), length_4, length_5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
                    title_vs.setText(builder);
                    pullListView.onPullDownRefreshComplete();
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                application.networkErrorMessage(e,res);
                pullListView.onPullDownRefreshComplete();
            }
        });
    }

    /****
     * 初始化
     * @param view
     * @param inflater
     */
    protected void initView(View view, LayoutInflater inflater) {
        title_vs=(TextView)view.findViewById(R.id.title_vs);
        pullDownListView(view);
	}


}
