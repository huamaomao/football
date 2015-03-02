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
import android.webkit.WebView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.model.News;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.UrlApi;
import com.vxfc.shenxin.util.Util;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Hua on 2014/8/13.
 */
public class NewsDetailActivity extends BaseActivity {
    private TextView tv_content,tv_item_0,tv_item_1;
    private String newsId;
    @InjectView(R.id.web_view) WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.inject(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        setBackActionBarTilte("新闻详细");
        webView.getSettings().setJavaScriptEnabled(true);//设置使用够执行JS脚本
        webView.getSettings().setBuiltInZoomControls(false);//设置使支持缩放
        webView.setInitialScale(39);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.requestFocus();
        //webView.getSettings().setBlockNetworkImage(true);
        webView.loadUrl(RequestUtil.requestHtm(getIntent().getStringExtra(Dict.ID)));
    }
}
