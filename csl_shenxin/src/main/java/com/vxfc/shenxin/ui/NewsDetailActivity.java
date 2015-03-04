package com.vxfc.shenxin.ui;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.vxfc.shenxin.R;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;

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
