package com.weixuan.shenxin.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.entity.TeamVo;
import com.weixuan.shenxin.util.RequestUtil;
import com.weixuan.shenxin.util.Util;

/****
 * 俱乐部简介
 *
 */
public class ClubIntroductionAFragment extends BaseFragment{
    private TextView tv_item_0,tv_item_1,tv_item_3,tv_item_4,tv_item_5,tv_item_6,tv_item_7,tv_item_8,tv_content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_club_i_menu1);
    }


    /****
     * 初始化
     * @param view
     * @param inflater
     */
    @Override
	public void initView(View view, LayoutInflater inflater) {
        tv_item_0=(TextView)view.findViewById(R.id.tv_item_0);
        tv_item_1=(TextView)view.findViewById(R.id.tv_item_1);
        tv_item_3=(TextView)view.findViewById(R.id.tv_item_3);
        tv_item_4=(TextView)view.findViewById(R.id.tv_item_4);
        tv_item_5=(TextView)view.findViewById(R.id.tv_item_5);
        tv_item_6=(TextView)view.findViewById(R.id.tv_item_6);
        tv_item_7=(TextView)view.findViewById(R.id.tv_item_7);
        tv_item_8=(TextView)view.findViewById(R.id.tv_item_8);
        tv_content=(TextView)view.findViewById(R.id.tv_content);
        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());
	}

    public void requestData(){
        application.execute(RequestUtil.requestTeamProfile(application.getToken().getAccess_token(),application.teamId),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                TeamVo vo= JSON.parseObject(data, TeamVo.class);
                if (null!=vo){
                    tv_item_0.setText(Util.initTextEmpty(vo.getCname()));
                    tv_item_1.setText(Util.initTextEmpty(vo.getEname()));
                    tv_item_3.setText(Util.initTextEmpty(vo.getRegion()));
                    tv_item_4.setText(Util.initTextEmpty(vo.getRegTime()));
                    tv_item_5.setText(Util.initTextEmpty(vo.getStadium()));
                    tv_item_6.setText(Util.initTextValue(vo.getGalleryful()));
                    tv_item_7.setText(Util.initTextEmpty(vo.getOwner()));
                    tv_item_8.setText(Util.initTextEmpty(vo.getCoach()));
                    tv_content.setText(Html.fromHtml(getString(R.string.club_jianjie)));
                }
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                application.networkErrorMessage(e,res);
            }
        });
    }

}
