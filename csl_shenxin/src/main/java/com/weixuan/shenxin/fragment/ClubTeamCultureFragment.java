package com.weixuan.shenxin.fragment;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.ui.TeamVideoPlayerActivity;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;

/**
 * *
 * 新闻/公告
 */
public class ClubTeamCultureFragment extends BaseFragment{
    SoundPool sp;
    private int sp_key;
    private Switch switch_id;
    private ImageView iv_item_0;
    private MediaPlayer mediaPlayer;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId(R.layout.f_club_team_culture);
    }

    protected void initView(View view, LayoutInflater inflater) {
        switch_id=(Switch)view.findViewById(R.id.switch_id);
        switch_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                  if (b){
                      if (mediaPlayer!=null){
                          mediaPlayer.start();
                      }
                  }else {
                      if (mediaPlayer!=null){
                          mediaPlayer.stop();
                      }
                  }
            }
        });
        Vitamio.initialize(getActivity(), getResources().getIdentifier("libarm", "raw", getActivity().getPackageName()));
        mediaPlayer=createMediaPlayer(getActivity(),R.raw.shenxin);
       // InitSound();
        iv_item_0=(ImageView)view.findViewById(R.id.iv_item_0);
        iv_item_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(TeamVideoPlayerActivity.class);
            }
        });
    }


    public MediaPlayer createMediaPlayer(Context context, int resid) {
        try {
            AssetFileDescriptor afd = context.getResources().openRawResourceFd(resid);
            MediaPlayer mp = new MediaPlayer(context);
            mp.setDataSource(afd.getFileDescriptor());
            afd.close();
            mp.prepare();
            return mp;
        } catch (Exception ex) {
            Log.d(TAG, "create failed:", ex);
            // fall through
        }
        return null;
    }


    public void InitSound() {
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        sp_key=sp.load(getActivity(), R.raw.shenxin, 1);
    }

    public void playSound(int number) {
        AudioManager am = (AudioManager) getActivity()
                .getSystemService(Context.AUDIO_SERVICE);
        float audioMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volumnCurrent = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        float volumnRatio = volumnCurrent / audioMaxVolumn;
        sp.play(sp_key, volumnRatio, volumnRatio, 1, number,  1f);
    }

}
