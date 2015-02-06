package com.vxfc.common.util;

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
import com.vxfc.common.R;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具
 * Created by Hua on 2014/7/31.
 */
public class Util {

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

    /*****
     * 验证手机号 格式
     * @param mobiles
     * @return
     *
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     */

    public static boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    /****
     * 验证邮箱格式
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
