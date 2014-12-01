package com.weixuan.shenxin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.adapter.QuickAdapter;
import com.weixuan.shenxin.ui.FootballApplication;
import com.weixuan.shenxin.util.Dict;
import com.weixuan.shenxin.util.Util;

import java.io.Serializable;

public abstract class BaseFragment extends Fragment {
    protected final String TAG="test";
    protected QuickAdapter quickAdapter=null;
    protected FootballApplication application;
    protected View rootView=null;
    protected int layoutId;
    protected boolean flag=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application=(FootballApplication)getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (Util.isNull(rootView)){
            rootView=inflater.inflate(layoutId,container,false);
            initView(rootView,inflater);
            doRefresh();
        }else if (flag){
            doRefresh();
        }
        ViewGroup parent=(ViewGroup)rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
      return rootView;
    }

    protected  void doRefresh(){
        requestData();
    }
    /***
     * 初始化
     */
    protected    abstract void initView(View view,LayoutInflater inflater);

    /**
     * 通过类名启动activity
     *
     */
    protected void openActivity(Class<?> pClass) {
        Intent intent = new Intent(getActivity(), pClass);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_left,0);
    }

    /**
     * 通过类名启动activity
     *
     * @param pClass
     *            要启动的类
     * @param serializable
     *            要传递的参数
     */
    protected void openActivity(Class<?> pClass, Serializable serializable) {
        Intent intent = new Intent(getActivity(), pClass);
        intent.putExtra(Dict.SERIALIZABLE,serializable);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_t);
    }
    protected  void setLayoutId(int layoutId){
        this.layoutId=layoutId;
    }


    /**
     * 通过类名启动activity
     *
     * @param pClass
     *            要启动的类
     * @param id
     *            要传递的参数 id
     */
    protected void openActivity(Class<?> pClass,String id,String playerName) {
        Intent intent = new Intent(getActivity(), pClass);
        intent.putExtra(Dict.ID,id);
        intent.putExtra(Dict.PLAYER_NAME,playerName);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_t);

    }

    /**
     * 通过类名启动activity
     *
     * @param pClass
     *            要启动的类
     * @param id
     *            要传递的参数 id
     */
    protected void openActivity(Class<?> pClass,String id) {
        Intent intent = new Intent(getActivity(), pClass);
        intent.putExtra(Dict.ID,id);
        getActivity().startActivity(intent);

    }
    protected void openActivityA(Class<?> pClass,String id) {
        Intent intent = new Intent(getActivity(), pClass);
        intent.putExtra(Dict.ID,id);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_t);

    }

    /****
     * 请求数据
     */
    protected   void  requestData(){

    }

}
