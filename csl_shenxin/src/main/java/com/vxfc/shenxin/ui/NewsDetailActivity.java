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
import android.view.MenuItem;
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
import com.vxfc.shenxin.entity.News;
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
                    spanned=Html.fromHtml(news.getContent(),imgGetter,null);
                    tv_content.setText(spanned);
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


    @Override
    protected void requestData() {
        super.requestData();
        application.execute(RequestUtil.requestNewDetail(application.getToken().getAccess_token(),newsId),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                News temp= JSON.parseObject(data,News.class);
                if (null!=temp){
                    news=temp;
                    spanned=Html.fromHtml(news.getContent(),imgGetter,null);
                    tv_item_0.setText(news.getTitle());
                    StringBuilder builder=new StringBuilder(news.getCreateTime());
                    builder.append(" ");
                    builder.append(news.getFromLocation());
                    tv_item_1.setText(builder.toString());
                    tv_content.setText(spanned);
                }
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                application.networkErrorMessage(e,res);
            }
        });
    }

    Spanned spanned;

    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(final String source) {
           File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "weixuan/Cache");
           final File cacheFile =new File(cacheDir,source);
           StringBuilder builder=new StringBuilder(UrlApi.imageUrl);
           builder.append(source);
            BitmapDrawable  drawable=(BitmapDrawable)BitmapDrawable.createFromPath(cacheFile.getPath());
            if (drawable==null){
               ImageLoader.getInstance().loadImage(builder.toString(),new ImageLoadingListener() {
                   @Override
                   public void onLoadingStarted(String imageUri, View view) {
                   }
                   @Override
                   public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                   }

                   @Override
                   public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                       if (Util.notNull(loadedImage)){
                           try {
                               if (!cacheFile.exists()){
                                   cacheFile.getParentFile().mkdirs();
                               }
                               FileOutputStream fos=new FileOutputStream(cacheFile);
                               loadedImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
                               fos.flush();
                               fos.close();
                               loadedImage.recycle();
                               handler.sendEmptyMessage(Dict.data_change);
                           } catch (Exception e) {
                               e.printStackTrace();
                           } finally {

                           }
                       }

                   }

                   @Override
                   public void onLoadingCancelled(String imageUri, View view) {

                   }
               });
              return null;
           }
            drawable.setTargetDensity(getResources().getDisplayMetrics());
            drawable.setBounds(0, 0,(int)(drawable.getIntrinsicWidth()*1.2),
                    (int)(drawable.getIntrinsicHeight()*1.2));
            return drawable;
        }
    };
}
