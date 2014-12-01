package com.weixuan.shenxin.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.weixuan.shenxin.R;

/**
 * *
 * 新闻/公告
 */
public class ClubTeamCultureFragment extends BaseFragment{

    SoundPool sp;
    private int sp_key;
    private Switch switch_id;

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
                      playSound(0);
                  }else {
                      sp.pause(sp_key);
                  }
            }
        });
        InitSound();
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
