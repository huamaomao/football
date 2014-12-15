package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.ListNewsAdapater;
import com.vxfc.shenxin.entity.News;

import java.util.ArrayList;
import java.util.List;

import com.vxfc.shenxin.ui.NewsDetailActivity;
import com.vxfc.shenxin.util.RequestUtil;

/**
 * *
 * 新闻/公告
 */
public class NewsFragment extends BaseListFragment {

    private List<News> lsData;
    private int page = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId(R.layout.public_listview);
    }


    protected void initView(final View view, LayoutInflater inflater) {
        lsData = new ArrayList<News>();
        adapter = new ListNewsAdapater(getActivity(), lsData);
        pullDownLoadListView(view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = lsData.get(position);
                openActivityA(NewsDetailActivity.class, news.getId());
            }
        });
    }

    @Override
    protected void requestData() {
        super.requestData();
        page = 0;
        application.execute(RequestUtil.requestNewList(application.getToken().getAccess_token(), String.valueOf(page)), new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                List<News> temp = JSON.parseArray(data, News.class);
                if (temp != null) {
                    lsData.clear();
                    lsData.addAll(temp);
                    adapter.notifyDataSetChanged();
                    if (temp.size() > 9) {
                        pullListView.setPullLoadEnabled(true);
                        pullListView.setHasMoreData(true);
                    }
                }
                pullListView.onPullDownRefreshComplete();
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                application.networkErrorMessage(e, res);
                pullListView.onPullDownRefreshComplete();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void requestMoreLoadingData() {
        page++;
        application.execute(RequestUtil.requestNewList(application.getToken().getAccess_token(), String.valueOf(page)), new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                List<News> temp = JSON.parseArray(data, News.class);
                if (temp != null&&temp.size()>0) {
                    lsData.addAll(temp);
                    adapter.notifyDataSetChanged();
                } else {
                    pullListView.setHasMoreData(false);
                }
                pullListView.onPullUpRefreshComplete();
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                application.networkErrorMessage(e, res);
                pullListView.onPullUpRefreshComplete();
            }
        });
    }
}
