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
import com.weixuan.shenxin.entity.PlayerVo;
import com.weixuan.shenxin.ui.TeamPlayerActivity;
import com.weixuan.shenxin.util.RequestUtil;
import com.weixuan.shenxin.util.Team;

/**
 * Created by Hua on 2014/8/13.
 */
public class PlayerAboutFragment extends BaseFragment{

    private TextView tv_tv_item_0,tv_tv_item_1,tv_tv_item_2,tv_tv_item_3,tv_tv_item_4,tv_tv_item_5,tv_tv_item_6
            ,tv_tv_item_8,tv_tv_item_9,tv_tv_item_10,tv_tv_item_11;

    private TeamPlayerActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_player_about);
    }

    @Override
    protected void initView(View view, LayoutInflater inflater) {
        tv_tv_item_0=(TextView)view.findViewById(R.id.tv_item_0);
        tv_tv_item_1=(TextView)view.findViewById(R.id.tv_item_1);
        tv_tv_item_2=(TextView)view.findViewById(R.id.tv_item_2);
        tv_tv_item_3=(TextView)view.findViewById(R.id.tv_item_3);
        tv_tv_item_4=(TextView)view.findViewById(R.id.tv_item_4);
        tv_tv_item_5=(TextView)view.findViewById(R.id.tv_item_5);
        tv_tv_item_6=(TextView)view.findViewById(R.id.tv_item_6);
        tv_tv_item_8=(TextView)view.findViewById(R.id.tv_item_8);
        tv_tv_item_9=(TextView)view.findViewById(R.id.tv_item_9);
        tv_tv_item_10=(TextView)view.findViewById(R.id.tv_item_10);
        tv_tv_item_11=(TextView)view.findViewById(R.id.tv_item_11);
        activity=(TeamPlayerActivity)getActivity();
    }

    @Override
    protected void requestData() {
        super.requestData();
        application.execute(RequestUtil.requestPlayerInfo(application.getToken().getAccess_token(),activity.getPlayerId()),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                PlayerVo vo= JSON.parseObject(data,PlayerVo.class);
                if (vo!=null){
                    tv_tv_item_0.setText(vo.getEname());
                    tv_tv_item_1.setText(vo.getCountry());
                    tv_tv_item_2.setText(vo.getBirthplace());
                    tv_tv_item_3.setText(vo.getHeight()+"cm");
                    tv_tv_item_4.setText(vo.getWeight()+"kg");
                    tv_tv_item_5.setText(vo.getBirthday());
                    tv_tv_item_6.setText(vo.getAge()+"岁");
                    tv_tv_item_8.setText(Team.getPlayerLocation(vo.getPosition()));
                    tv_tv_item_9.setText(vo.getFoot());
                    tv_tv_item_10.setText(vo.getNumber());
                    tv_tv_item_11.setText(vo.getDescription());
                }
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                application.networkErrorMessage(e,res);
            }
        });

    }
}
