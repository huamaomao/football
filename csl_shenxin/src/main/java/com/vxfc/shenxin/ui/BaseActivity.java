/*
 * Created by Storm Zhang, Feb 11, 2014.
 */

package com.vxfc.shenxin.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.Request;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.view.IMessageView;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.IRequestView;

public class BaseActivity extends FragmentActivity implements IRequestView,IMessageView {
    protected FootballApplication application;
    protected InputMethodManager imm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        application=(FootballApplication)getApplication();
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

	}

    public void setBackActionBarTilte(CharSequence str){
        ActionBar actionBar=getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(null);
        actionBar.setLogo(null);
        actionBar.setTitle(str);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            onHomeClick();
            return true;
        }else if (item.getItemId()!=0){
            onMenuItemSelected(item.getItemId());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /***
     * home
     */
    protected void onHomeClick(){
        toMainActity();
    }

    /***
     * action  item onclick
     * @param id
     */
    protected void onMenuItemSelected(int id){

    }

    protected   void  requestData(){

    }

    protected void initView(){

    }

    /**
     * 通过类名启动activity
     *
     */
    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 通过类名启动activity
     *
     * @param pClass
     *            要启动的类
     * @param pBundle
     *            要传递的参数
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (pBundle != null) {
            intent.putExtra(Dict.BUNDLE, pBundle);
        }
       overridePendingTransition(R.anim.slide_in_left, 0);
        startActivity(intent);
    }

    /**
     * 通过类名启动activity
     *
     * @param pClass
     *            要启动的类
     *            要传递的参数
     */
    protected void startActivity(Class<?> pClass) {
        Intent intent = new Intent(this, pClass);
        startActivity(intent);
    }

    protected void openActivityRight(Class<?> pClass){
        openActivity(pClass);
    }

    protected void openActivitySlide(Class<?> pClass){
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
        openActivity(pClass);
    }

    /*****
     * 键盘
     * @param isShowSoft
     * @param editText
     */
    protected void hideOrShowSoftInput(boolean isShowSoft, EditText editText) {
        if (isShowSoft) {
            imm.showSoftInput(editText, 0);
        } else {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    // 获得当前程序版本信息
    protected String getVersionName() throws Exception {
        PackageManager packageManager = getPackageManager();
        // 0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),
                0);
        return packInfo.versionName;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            toMainActity();
        }
        return super.onKeyUp(keyCode, event);
    }

    protected void  toMainActity(){
        Util.openActivity(MainActivity.class,null,this, ActivityModel.ACTIVITY_MODEL_3);
    }


    public void showMsg(String msg){
        application.msgShow(msg);
    }

    public void showLongMsg(String msg){
        application.msgLongShow(msg);
    }

    @Override
    public void request(Request res, HttpModelHandler<String> UIHandler) {
        application.execute(res,UIHandler);
    }

    @Override
    public void responseFailure(HttpException e, Response res) {
        application.networkErrorMessage(e,res);
    }

    @Override
    public void msgShow(String msg) {
        application.msgShow(msg);
    }

    @Override
    public void msgLongShow(String msg) {
        application.msgLongShow(msg);
    }
}
