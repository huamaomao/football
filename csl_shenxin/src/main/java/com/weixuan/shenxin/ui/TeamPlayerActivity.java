package com.weixuan.shenxin.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.RadioGroup;
import com.weixuan.shenxin.R;
import com.weixuan.shenxin.fragment.PlayerAboutFragment;
import com.weixuan.shenxin.fragment.PlayerGameStatsFragment;
import com.weixuan.shenxin.fragment.PlayerRecordFragment;
import com.weixuan.shenxin.fragment.PlayerRecordsHonorFragment;
import com.weixuan.shenxin.util.Dict;
import com.weixuan.shenxin.util.Util;

/**
 * Created by Hua on 2014/8/13.
 */
public class TeamPlayerActivity extends BaseActivity {
    public RadioGroup rgGroup;
    private String playerId;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_player);
        playerId=getIntent().getStringExtra(Dict.ID);
        playerName=getIntent().getStringExtra(Dict.PLAYER_NAME);
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            backActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void initView() {
         setBackActionBarTilte(playerName);
        this.rgGroup=(RadioGroup)findViewById(R.id.rg_group_m);
        Util.turnToFragment(getSupportFragmentManager(), PlayerAboutFragment.class, null, R.id.fl_m_content);
        rgGroup.check(R.id.rbtn_left_menu_0);
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_left_menu_0:
                        Util.turnToFragment(getSupportFragmentManager(), PlayerAboutFragment.class, null, R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_1:
                        Util.turnToFragment(getSupportFragmentManager(), PlayerGameStatsFragment.class, null, R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_2:
                        Util.turnToFragment(getSupportFragmentManager(), PlayerRecordsHonorFragment.class, null, R.id.fl_m_content);
                        break;
                    case R.id.rbtn_left_menu_3:
                        Util.turnToFragment(getSupportFragmentManager(), PlayerRecordFragment.class, null, R.id.fl_m_content);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            backActivity();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    public String getPlayerId() {
        return playerId;
    }

    private void backActivity(){
        overridePendingTransition(R.anim.slide_t,R.anim.slide_out_right);
        finish();
    }
}