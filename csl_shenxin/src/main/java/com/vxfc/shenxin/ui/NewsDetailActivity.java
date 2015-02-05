package com.vxfc.shenxin.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.model.News;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.UrlApi;
import com.vxfc.shenxin.util.Util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Hua on 2014/8/13.
 */
public class NewsDetailActivity extends BaseActivity {
    private TextView tv_content,tv_item_0,tv_item_1;
    private String newsId;
    private News news;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Dict.data_change:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    @Override
    protected void initView() {
        setBackActionBarTilte("新闻详细");
        tv_content=(TextView)findViewById(R.id.tv_content);
        tv_item_0=(TextView)findViewById(R.id.tv_item_0);
        tv_item_1=(TextView)findViewById(R.id.tv_item_1);
        newsId= getIntent().getStringExtra(Dict.ID);
        requestData();
        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
