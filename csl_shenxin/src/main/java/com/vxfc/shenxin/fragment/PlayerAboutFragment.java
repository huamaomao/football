package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.domian.Player;
import com.vxfc.shenxin.ui.TeamPlayerActivity;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Team;

/**
 * Created by Hua on 2014/8/13.
 */
public class PlayerAboutFragment extends BaseFragment{

    private TextView tv_tv_item_0,tv_tv_item_1,tv_tv_item_2,tv_tv_item_3,tv_tv_item_4,tv_tv_item_5,tv_tv_item_6
            ,tv_tv_item_8,tv_tv_item_9,tv_tv_item_10,tv_tv_item_11;

    private TeamPlayerActivity activity;
    private ImageView iv_photo;
    private DisplayImageOptions options;

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
        iv_photo=(ImageView)view.findViewById(R.id.iv_photo);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.player_defualt)
                .showImageOnFail(R.drawable.player_defualt)
                .showImageForEmptyUri(R.drawable.player_defualt)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        activity=(TeamPlayerActivity)getActivity();
    }

    @Override
    protected void requestData() {
        super.requestData();
        application.execute(RequestUtil.requestPlayerInfo(application.getToken().getAccess_token(),activity.getPlayerId()),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                Player vo= JSON.parseObject(data,Player.class);
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
                    iv_photo.setTag(RequestUtil.getUrl(vo.getPhoto()));
                    ImageLoader.getInstance().displayImage(RequestUtil.getUrl(vo.getPhoto()),iv_photo,options);
                }
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                application.networkErrorMessage(e,res);
            }
        });

    }
}
