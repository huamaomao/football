package com.vxfc.shenxin.ui;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.litesuits.http.LiteHttpClient;
import com.litesuits.http.async.HttpAsyncExecutor;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.exception.HttpNetException;
import com.litesuits.http.exception.HttpServerException;
import com.litesuits.http.request.Request;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.litesuits.http.response.handler.HttpResponseHandler;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.vxfc.shenxin.model.RecentGameTeam;
import com.vxfc.shenxin.model.Token;
import com.vxfc.shenxin.util.Team;
import java.io.File;


public class FootballApplication extends Application {
    private Token token = new Token();
    private HttpAsyncExecutor asyncExecutor;
    private LiteHttpClient client;
    private RecentGameTeam gameTeam;
    public static final int NETWORK_ERROR = 1;
    public String teamId = Team.ShenXin.id;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case NETWORK_ERROR:
                    msgShow(msg.obj.toString());
                    break;
            }
        }
    };

    public static void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "vxfc/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);

    }

    /**
     * 提示条
     *
     * @param content 提示的内容
     */
    public void msgShow(String content) {
       Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    /**
     * 提示条
     *
     * @param content 提示的内容
     */
    public void msgLongShow(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    /**
     * 提示条
     *
     * @param content 提示的内容
     */
    public void handleToast(String content) {
        Message msg = new Message();
        msg.what = NETWORK_ERROR;
        msg.obj=content;
        handler.sendMessage(msg);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        client= LiteHttpClient.newApacheHttpClient(getApplicationContext());
        client.config(getApplicationContext(),true,true,false,true);
        asyncExecutor=HttpAsyncExecutor.newInstance(client);
        initImageLoader(getApplicationContext());
    }

    /****
     * 网络请求
     * @param res
     * @param UIHandler
     */
    public void execute(final Request res,final HttpModelHandler<String> UIHandler){
        asyncExecutor.execute(res,UIHandler);
    }
    /****
     * 网络请求
     * @param res
     * @param UIHandler
     */
    public void execute(final Request res,final HttpResponseHandler UIHandler){
        asyncExecutor.execute(res,UIHandler);
    }
    /**
     * **
     * 异常
     *
     * @param e
     * @param res
     */
    public void networkErrorMessage(HttpException e, Response res) {
        if (e instanceof HttpNetException) {
            HttpNetException netException = (HttpNetException) e;
            handleToast(netException.getExceptionType().chiReason);
        } else if (e instanceof HttpServerException) {
            HttpServerException serverException = (HttpServerException) e;
            handleToast(serverException.getExceptionType().chiReason);
        }
    }

    /**
     * **
     * 异常
     *
     * @param e
     * @param e
     */
    public void errorMessage(Exception e) {
       handleToast(e.getMessage());
    }

    public RecentGameTeam getGameTeam() {
        return gameTeam;
    }

    public void setGameTeam(RecentGameTeam gameTeam) {
        this.gameTeam = gameTeam;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
