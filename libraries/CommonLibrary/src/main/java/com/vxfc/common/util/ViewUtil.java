package com.vxfc.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.WindowManager;

import com.vxfc.common.R;

/**
 * Created by Hua_ on 2015/2/6.
 */
public class ViewUtil {

    /************************************************View 控制  method ******************************************************************/

    public static void openActivity(Class<?> pClass,Activity activity,ActivityModel model){
        openActivity(pClass,null,activity,model,false);
    }

    public static void openActivity(Class<?> pClass,Activity activity){
        openActivity(pClass,null,activity,ActivityModel.ACTIVITY_MODEL_0,false);
    }

    public static void openActivity(Class<?> pClass, Bundle pBundle,Activity activity,ActivityModel type) {
        openActivity(pClass,null,activity,type,false);
    }

    /**
     * 通过类名启动activity
     *
     * @param pClass
     *            要启动的类
     * @param pBundle
     *            要传递的参数
     * @param type
     *           动画
     * @param flag
     *          是否finish
     */
    public static void openActivity(Class<?> pClass, Bundle pBundle,Activity activity,ActivityModel type,boolean flag) {
        Intent intent = new Intent(activity, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        switch (type.value){
            case 0:
                break;
            case 1:
                activity.overridePendingTransition(R.anim.slide_t, R.anim.slide_out_right);
                break;
            case 2:
                activity.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_t);
                break;
            case 3:
                activity.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                break;
        }
        activity.startActivity(intent);
        if (flag){
            activity.finish();
        }
    }



    /****
     * 屏幕
     * @param context
     * @return
     */
    public static Display getDisplay(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay();
    }



    /**
     * Fragment 跳转
     * @param fm
     * @param fragmentClass
     * @param args
     * @param layoutid
     */
    public static Fragment turnToFragment(FragmentManager fm, Class<? extends Fragment> fragmentClass,Bundle args,int layoutid) {
        Fragment fragment = fm.findFragmentByTag(fragmentClass.getSimpleName());
        boolean isFragmentExist = true;
        if (fragment == null) {
            try {
                isFragmentExist = false;
                fragment = fragmentClass.newInstance();
                fragment.setArguments(new Bundle());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(fragment.isAdded()){
            return fragment;
        }
        if( args != null && !args.isEmpty() ) {
            fragment.getArguments().putAll(args);
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out);
        if( isFragmentExist ) {
            ft.replace(layoutid, fragment);
        } else {
            ft.replace(layoutid, fragment, fragmentClass.getSimpleName());
        }
        ft.addToBackStack(fragmentClass.getSimpleName());
        ft.commitAllowingStateLoss();
        return fragment;
    }

    /*****
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
