package com.vxfc.shenxin.fragment;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.view.PullToRefreshBase;
import com.vxfc.shenxin.view.PullToRefreshListView;
public abstract class BaseListFragment extends BaseFragment {
    protected PullToRefreshListView pullListView=null;
    protected ListView listView=null;
    protected BaseAdapter adapter=null;

    protected void pullDownListView(View view){
        pullListView=(PullToRefreshListView)view.findViewById(R.id.ls_data);
        listView=pullListView.getRefreshableView();
        listView.setDivider(getResources().getDrawable(R.drawable.ls_divider));
        listView.setDividerHeight(2);
        pullListView.setPullLoadEnabled(false);
        pullListView.setScrollLoadEnabled(false);
        setOnRefreshListener();

    }

    protected void initListView(View view){
        pullListView=(PullToRefreshListView)view.findViewById(R.id.ls_data);
        listView=pullListView.getRefreshableView();
        listView.setDivider(getResources().getDrawable(R.drawable.ls_divider));
        listView.setDividerHeight(2);
        pullListView.setPullLoadEnabled(false);
        pullListView.setScrollLoadEnabled(false);
        setOnRefreshListener();
    }

    protected void pullDownLoadListView(View view){
        initListView(view);
    }

    /****
     * 请求数据
     */
    protected void doPullRefreshing(){
        pullListView.doPullRefreshing(true,0);
    }

    protected  void doRefresh(){
        doPullRefreshing();
    }


    protected void setOnRefreshListener(){
        pullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                requestData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                requestMoreLoadingData();
            }
        });
    }
    protected void  requestMoreLoadingData(){

    }
}
