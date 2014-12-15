package com.vxfc.shenxin.fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.ui.VideoPlayerActivity;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.LyricView;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.utils.StringUtils;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * *
 * 新闻/公告
 */
public class ClubTeamCultureFragment extends BaseFragment implements View.OnClickListener, io.vov.vitamio.MediaPlayer.OnInfoListener, io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener {
    private ImageButton iv_item_0,iv_item_1;
    private SeekBar playSeekBar;
    private MediaPlayer mediaPlayer;
    private LyricView lyricView;
    private TextView tv_item_0,tv_item_1;
    /**播放**/
    private int current;
    private int endTimte;
    private int INTERVAL=45;//歌词每行的间隔
    private MediaController controller;
    private final static int UPDATE_SEEKBAR=1;
    /**视屏***/
    private String path = "http://114.80.68.147:9999/vedio/shenxin_video.mp4";
    private Uri uri;
    private io.vov.vitamio.widget.VideoView mVideoView;
    private ProgressBar pb;
    private TextView downloadRateView, loadRateView;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_SEEKBAR:
                    playSeekBar.setProgress(current);
                    break;
            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_club_team_culture);
        if (!LibsChecker.checkVitamioLibs(getActivity()))
            return;
    }


    protected void initView(View view, LayoutInflater inflater) {
        iv_item_0=(ImageButton)view.findViewById(R.id.iv_item_0);
        iv_item_0.setOnClickListener(this);
        iv_item_1=(ImageButton)view.findViewById(R.id.iv_item_1);
        iv_item_1.setOnClickListener(this);
        tv_item_0=(TextView)view.findViewById(R.id.tv_item_0);
        tv_item_1=(TextView)view.findViewById(R.id.tv_item_1);
        playSeekBar=(SeekBar)view.findViewById(R.id.playSeekBar);
        playSeekBar.setMax(0);
        playSeekBar.setSecondaryProgress(0);
        playSeekBar.setProgress(0);
        lyricView=(LyricView)view.findViewById(R.id.mylrc);
        lyricView.setRawId("shenxin_gc.mp3");
        lyricView.setTextSize();

        mVideoView = (VideoView)view.findViewById(R.id.buffer);
        pb = (ProgressBar)view.findViewById(R.id.probar);
        downloadRateView = (TextView) view.findViewById(R.id.download_rate);
        loadRateView = (TextView) view.findViewById(R.id.load_rate);
        mVideoView.setVideoURI(Uri.parse(path));
        controller=new MediaController(getActivity(),true);
        mVideoView.setMediaController(controller);
        mVideoView.setOnInfoListener(this);
        controller.setFileName("上海申鑫队歌");
        mVideoView.setOnBufferingUpdateListener(this);

        mVideoView.setOnPreparedListener(new io.vov.vitamio.MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(io.vov.vitamio.MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });

        playSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                current=seekBar.getProgress();
                seekPlay();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        initMusic();
    }

    private void initMusic(){
        mediaPlayer =MediaPlayer.create(getActivity(),R.raw.shenxin);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setWakeMode(getActivity(), PowerManager.PARTIAL_WAKE_LOCK);
        endTimte=(int) (mediaPlayer.getDuration() / 1000);
        playSeekBar.setMax(endTimte);
        tv_item_0.setText( StringUtils.generateTime(mediaPlayer.getDuration()));
        tv_item_1.setText(StringUtils.generateTime(mediaPlayer.getCurrentPosition()));

    }

    public void seekPlay() {
        if (current >= endTimte - 2) {
            mediaPlayer.seekTo((endTimte - 2) * 1000);
        } else {
            mediaPlayer.seekTo(current * 1000);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            mediaPlayer.stop();
            updatePlayState();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void play(){
      try {
          mediaPlayer.start();
          lyricView.setOffsetY(220 - lyricView.SelectIndex(mediaPlayer.getCurrentPosition())
                  * (lyricView.getSIZEWORD() + INTERVAL-1));
          mediaPlayer.seekTo(current*1000);
          handler.postDelayed(updateSeekbar,500);
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       if (Util.notNull(mediaPlayer)){
           mediaPlayer.stop();
           mediaPlayer.release();
       }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_item_0:
                Util.openActivity(VideoPlayerActivity.class,null,getActivity(), ActivityModel.ACTIVITY_MODEL_1);
                break;
            case R.id.iv_item_1:
                if (Util.notNull(mediaPlayer)){
                   try{
                       if (mediaPlayer.isPlaying()){
                           current=mediaPlayer.getCurrentPosition()/1000;
                           mediaPlayer.pause();
                           handler.removeCallbacks(updateSeekbar);
                       }else {
                           play();
                       }

                   }catch (Exception e){
                   }
                }
                updatePlayState();
                break;
        }
    }

    private void  updatePlayState(){
        if (mediaPlayer.isPlaying())
            iv_item_1.setImageResource(R.drawable.mediacontroller_pause);
        else
         iv_item_1.setImageResource(R.drawable.mediacontroller_play);
    }


    Runnable updateSeekbar=new Runnable() {
        @Override
        public void run() {
            if (Util.notNull(playSeekBar)){
                if (current>=endTimte){
                    current=0;
                    updatePlayState();
                    handler.removeCallbacks(updateSeekbar);
                }
                tv_item_1.setText(StringUtils.generateTime(mediaPlayer.getCurrentPosition()));
                current = mediaPlayer.getCurrentPosition()/1000;
                playSeekBar.setProgress(current);
                lyricView.setOffsetY(lyricView.getOffsetY() - lyricView.SpeedLrc());
                lyricView.SelectIndex(mediaPlayer.getCurrentPosition());
                lyricView.invalidate();
                handler.postDelayed(updateSeekbar,500);
            }
        }
    };


    @Override
    public boolean onInfo(io.vov.vitamio.MediaPlayer mp, int what, int extra) {
        switch (what) {
            case io.vov.vitamio.MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);

                }
                break;
            case io.vov.vitamio.MediaPlayer.MEDIA_INFO_BUFFERING_END:
                mVideoView.start();
                pb.setVisibility(View.GONE);
                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                break;
            case io.vov.vitamio.MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }

    @Override
    public void onBufferingUpdate(io.vov.vitamio.MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }

}
