package com.vxfc.shenxin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.util.DateUtil;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.adapter.ListNewsAdapater;
import com.vxfc.shenxin.domian.News;
import java.util.LinkedList;
import com.vxfc.shenxin.domian.NewsPage;
import com.vxfc.shenxin.domian.ResponseModel;
import com.vxfc.shenxin.domian.param.NewParam;
import com.vxfc.shenxin.ui.NewsDetailActivity;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;

/**
 * *
 * 新闻/公告
 */
public class NewsFragment extends BaseListFragment {

    private LinkedList<News> lsData;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId(R.layout.public_listview);
    }

    protected void initView(final View view, LayoutInflater inflater) {
        lsData = new LinkedList<News>();
        adapter = new ListNewsAdapater(getActivity(), lsData);
        pullDownLoadListView(view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = lsData.get(position);
                Bundle bundle=new Bundle();
                bundle.putString(Dict.ID,news.id);
                Util.openActivity(NewsDetailActivity.class,bundle,getActivity(), ActivityModel.ACTIVITY_MODEL_1);
            }
        });
    }

    @Override
    protected void requestData() {
        super.requestData();
        NewParam param=new NewParam();
        param.date= DateUtil.getDate();
        param.team_id=application.teamId;
        application.execute(RequestUtil.requestNewList(param), new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                NewsPage newsPage = JSON.parseObject(data, NewsPage.class);
                if (Util.notNull(newsPage)) {
                   int size=Integer.valueOf(newsPage.size);
                   if (lsData.size()>0){
                       for (int i=size-1;i>=0;i--){
                           if (!lsData.contains(newsPage.items.get(i))){
                               lsData.addFirst(newsPage.items.get(i));
                           }
                       }
                   }else {
                       lsData.addAll(newsPage.items);
                   }
                    adapter.notifyDataSetChanged();
                    if (lsData.size()>=15) {
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
        NewParam param=new NewParam();
        param.team_id=application.teamId;
        param.date=lsData.getLast().time;
        param.page="1";
        application.execute(RequestUtil.requestNextNewList(param), new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                NewsPage newsPage = JSON.parseObject(data, NewsPage.class);
                if (newsPage != null&&newsPage.size!="0") {
                    lsData.addAll(newsPage.items);
                    adapter.notifyDataSetChanged();
                }
                pullListView.onPullUpRefreshComplete();
            }

            @Override
            protected void onFailure(HttpException e, Response res){
                ResponseModel model=JSON.parseObject(res.getString(), ResponseModel.class);
                Log.d("hua",res.getString());
                if (Util.notNull(model)&&model.statusCode.equals("40005")){
                    application.msgShow("没有新闻了...");
                    pullListView.setHasMoreData(false);
                }else {
                    //application.networkErrorMessage(e, res);
                    pullListView.setHasMoreData(false);
                }
                pullListView.onPullUpRefreshComplete();
            }
        });
    }
}
