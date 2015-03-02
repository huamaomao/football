package com.vxfc.shenxin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vxfc.common.util.Log;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.QuickAdapter;
import com.vxfc.shenxin.ui.FootballApplication;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.Util;

import java.io.Serializable;

import butterknife.ButterKnife;

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
    protected  void setLayoutId(int layoutId){
        this.layoutId=layoutId;
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
        Log.d("hua",this+"==="+this.getClass());
       ButterKnife.inject(this,rootView);
      return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    protected  void doRefresh(){
        requestData();
    }
    /***
     * 初始化
     */
    protected    abstract void initView(View view,LayoutInflater inflater);

    /****
     * 请求数据
     */
    protected   void  requestData(){

    }

}
