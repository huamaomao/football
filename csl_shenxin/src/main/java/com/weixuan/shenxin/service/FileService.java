package com.weixuan.shenxin.service;

import android.content.Context;
import org.apache.http.util.EncodingUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Hua on 2014/8/18.
 */
public class FileService {


    private Context context;



    public FileService(Context context){
        this.context=context;
    }
    //写数据
    public void writeFile(String fileName,String writestr){
        FileOutputStream fos=null;
        try{
            fos =context.openFileOutput(fileName,Context.MODE_PRIVATE);
            byte [] bytes = writestr.getBytes();
            fos.write(bytes);
            fos.flush();
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            if (fos!=null){
               try {
                   fos.close();
               }catch (Exception e){

               }
            }
        }
    }

    //读数据
    public String readFile(String fileName){
        String res=null;
        FileInputStream fis=null;
        try{
            fis =context.openFileInput(fileName);
            int length = fis.available();
            byte [] buffer = new byte[length];
            fis.read(buffer);
            res = EncodingUtils.getString(buffer, "UTF-8");
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            if (fis!=null){
                try {
                    fis.close();
                }catch (Exception e){

                }
            }
        }
        return res;

    }
}
