package com.vxfc.shenxin.ui;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.*;

import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.vxfc.common.util.CommonUtil;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.domian.InfoList;
import com.vxfc.shenxin.domian.Member;
import com.vxfc.shenxin.domian.param.MemberParam;
import com.vxfc.shenxin.fragment.*;
import com.vxfc.shenxin.service.SharedService;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;


public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,ActionBar.OnNavigationListener{

    public   NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;
    private SharedService service;
    private DisplayImageOptions options;
    private MenuItem memberItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        service=new SharedService(this);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.icon_membert)
                .showImageOnFail(R.drawable.icon_membert)
                .showImageForEmptyUri(R.drawable.icon_membert)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        Util.turnToFragment(getSupportFragmentManager(), NewsMainFragment.class, null, R.id.container);
        mNavigationDrawerFragment.rgGroup.check(R.id.rbtn_left_menu_1);

    }

    @Override
    protected void requestData() {
        MemberParam param=new MemberParam();
       String  memberId=service.getValue(Dict.MEMBER_ID);
        if (CommonUtil.isEmpty(memberId)) return;
        param.setUserId(memberId);
        request(RequestUtil.requestMemberInfo(param), new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                Log.i(data);
                Member member= JSON.parseObject(data, Member.class);
                if (CommonUtil.notNull(member)){
                   /* ivPhoto.setTag(member.getPhoto());
                    ImageLoader.getInstance().displayImage(, ivPhoto,options);*/

                   Bitmap bitmap= ImageLoader.getInstance().loadImageSync(RequestUtil.getUrl(member.getPhoto()),options);
                    if(CommonUtil.notNull(bitmap)){
                        memberItem.setIcon(new BitmapDrawable(getResources(),bitmap));
                        bitmap.recycle();
                    }
                }
            }

            @Override
            protected void onFailure(HttpException e, Response res) {

            }
        });
    }

    @Override
    public void onNavigationDrawerItemSelected(int id) {
       switch (id){
            case R.id.rbtn_left_menu_0:
                NewsMainFragment fragment=(NewsMainFragment) Util.turnToFragment(getSupportFragmentManager(),NewsMainFragment.class,null,R.id.container);
               // fragment.setNewsTab(1);
                mTitle=getString(R.string.left_menu_0);
                break;
            case R.id.rbtn_left_menu_1:
                NewsMainFragment fragment1=(NewsMainFragment) Util.turnToFragment(getSupportFragmentManager(),NewsMainFragment.class,null,R.id.container);
                //fragment1.setNewsTab(2);
                mTitle=getString(R.string.left_menu_1);
                break;
            case R.id.rbtn_left_menu_2:
               Util.turnToFragment(getSupportFragmentManager(),ScheduleFragment.class,null,R.id.container);
                mTitle=getString(R.string.left_menu_2);
                break;
            case R.id.rbtn_left_menu_3:
                Util.turnToFragment(getSupportFragmentManager(),ClubFragment.class,null,R.id.container);
                mTitle=getString(R.string.left_menu_3);
                break;
            case R.id.rbtn_left_menu_4:
                Util.turnToFragment(getSupportFragmentManager(),TeamMainFragment.class,null,R.id.container);
                mTitle=getString(R.string.left_menu_4);
                break;
        }
        restoreActionBar();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(mTitle);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       /* if (!mNavigationDrawerFragment.isDrawerOpen()) {
           // getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }*/
        getMenuInflater().inflate(R.menu.global, menu);
         memberItem=menu.findItem(R.id.action_member);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_member) {
            Util.openActivity(MemberInfoActivity.class, this);
            return true;
        }

        return mNavigationDrawerFragment.onOptionsItemSelected(item);
    }

    private int index=0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
       if (keyCode == KeyEvent.KEYCODE_BACK){
            index++;
            if (index==1){
                application.handleToast("再按一次退出客户端");
            }else if(index==2){
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            return true;
        }
        index=0;
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(int i, long l) {

        return false;
    }
}
