package com.vxfc.shenxin.service;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hua_ on 2015/2/27.
 */
public class SharedService {
    private SharedPreferences preferences;
    private static final String name="shenxin_member";
    public SharedService(Context context){
        preferences=context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    public void putString(String key,String value){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public String getValue(String key){
       return preferences.getString(key,null);
    }
}
