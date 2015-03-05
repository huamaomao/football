package com.vxfc.shenxin.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.WindowManager;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.domian.Token;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 工具
 * Created by Hua on 2014/7/31.
 */
public class Util {
    /************************************************url 请求 解析 method ******************************************************************/
    /*****
     *
     * @param requestApi
     * @param webModel
     * @return
     */
    public static String jointUrl(String requestApi,String webModel){
        StringBuilder builder=new StringBuilder(UrlApi.SERVER_IP);
        builder.append(webModel);
        builder.append("/");
        builder.append(requestApi);
        return builder.toString();
    }

    /****
     *获取单个比分（0-0）0
     * @param score
     * @param model 0(表示A队) or !0（表示B队）
     * @return
     */
   public static String getScoreAorB(String score,int model){
       if (!isEmpty(score)){
           String[] strs=score.split("-");
           if (strs!=null&&strs.length==2){
                return model==0?strs[0]:strs[1];
           }
       }
       return "0";

   }

    /***
     * token 解析
     * @param url
     * @return
     */
    public static Token convertToken(String url){
          if (isEmpty(url)) return  null;
          url=url.substring(url.indexOf("#")+1);
          String str[]=url.split("&");
        if (str==null)return null;
         Token token=new Token();
         String str0[]=null;
         try {
             for (String s:str){
                 str0=s.split("=");

                 if (Dict.ACCESS_TOKEN.equals(str0[0])){
                     token.setAccess_token(str0[1]);
                 }else if(Dict.EXPIRES_IN.equals(str0[0])){
                      try {
                          token.setExpires_in(Long.valueOf(str0[1]));
                      }catch (Exception e){

                      }
                 }else if (Dict.REFRESH_TOKEN.equals(str0[0])){
                      token.setRefresh_token(str0[1]);
                 }else if(Dict.STATE.equals(str0[0])){
                     try {
                         token.setState(Integer.parseInt(str0[1]));
                     }catch (Exception e){
                         token.setState(0);
                     }
                 }
             }
             return token;
         }catch (Exception e){
           return null;
         }

    }

    /************************************************View 控制  method ******************************************************************/

    public static void openActivity(Class<?> pClass,Activity activity,ActivityModel model){
        openActivity(pClass,null,activity,model,false);
    }

    public static void openActivity(Class<?> pClass,Activity activity){
        openActivity(pClass,null,activity,ActivityModel.ACTIVITY_MODEL_0,false);
    }

    public static void openActivity(Class<?> pClass, Bundle pBundle,Activity activity,ActivityModel type) {
        openActivity(pClass,pBundle,activity,type,false);
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
        activity.startActivity(intent);
        switch (type.value){
            case 0:
                break;
            case 1:
                activity.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_t);
                break;
            case 2:
                activity.overridePendingTransition(R.anim.slide_out_right,R.anim.slide_t);
                break;
            case 3:
                activity.overridePendingTransition(R.anim.slide_t,R.anim.slide_in_right);
                break;
        }
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

    /************************************************数据 method ******************************************************************/
    /***
     * 判断是否可用
     * @param context
     * @return
     */
    public static boolean isNetAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isAvailable());
    }

    /****
     * 判断是否3g网络
     * @param context
     * @return
     */
    public static boolean is3rd(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /****
     * 判断是否wifi网络
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /***
     * 初始化时间
     * @param startYear  开始年份>今年
     * @return
     */
    public static Integer[]  getYearList(int startYear){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        int endYear=calendar.get(Calendar.YEAR);
        int length=endYear-startYear+1;
        Integer yearList[]=new Integer[length];
        for (int i=0;i<length;i++){
            yearList[i]=endYear-i;
        }
        return yearList;
    }

    /**
     *判断是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
      return (null==str||"".equals(str)||"null".equals(str))?true:false;
    }

    public static  boolean not0(String str){
        return (null==str||"0".equals(str)||"".equals(str)||"null".equals(str))?false:true;
    }

    /****
     * @  根据对象的属性排序
     * @param list
     * @param fieldName
     * @param flag  ture 正序  false 倒序
     */
    public static void sort(List list,final String fieldName, final boolean flag){
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                int ret =0;
                try {
                    Field field1 = a.getClass().getDeclaredField(fieldName);
                    Field field2 = a.getClass().getDeclaredField(fieldName);
                    field1.setAccessible(true);
                    field2.setAccessible(true);
                   if (flag){
                       ret = Integer.valueOf(field1.get(a).toString()).compareTo(Integer.valueOf(field2.get(b).toString()));
                   }else {
                       ret = Integer.valueOf(field1.get(b).toString()).compareTo(Integer.valueOf(field2.get(a).toString()));
                   }
                } catch (Exception ne) {
                    ne.printStackTrace();
                }
                return ret;
            }
        });
    }

    public static String initTextValue(String text){
        if (isEmpty(text)){
            return "0";
        }else {
            return text;
        }
    }

    public static String initTextEmpty(String text){
        if (isEmpty(text)){
            return "";
        }else {
            return text;
        }
    }


    public static boolean isNull(Object o){
        return o==null?true:false;
    }
    public static boolean notNull(Object o){
        return o==null?false:true;
    }

    public static int parseInt(String str){
        if (isEmpty(str)) return 0;
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return 0;
        }
    }
}
