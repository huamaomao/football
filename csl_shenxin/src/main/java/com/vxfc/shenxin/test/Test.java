package com.vxfc.shenxin.test;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.List;

/**
 * Created by Hua on 2014/8/2.
 */
public class Test {
   
   public static void main(String[] arg){
       java.io.FileInputStream fis = null;
       try {
           KeyStore ks = KeyStore.getInstance("JKS");
           fis = new java.io.FileInputStream("F:\\weixuan\\football\\signed\\weixuan_app.jks");
           ks.load(fis, "weixuan1234".toCharArray());
           PrivateKey caprk = (PrivateKey) ks.getKey("weixuan", "weixuan1234".toCharArray());
           System.out.println(caprk);

           System.out.println( caprk.getEncoded());
       } catch (Exception e){
           e.printStackTrace();
       } finally{
           if (fis != null) {
               try {
                   fis.close();
               }catch (Exception e){

               }
           }
       }
     /*  ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
       ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
       Object o = null;
       try {
           if (scriptEngine!=null) {
               //o = scriptEngine.eval(s);
               o = scriptEngine.eval("12+(0.7657-0.70)/(0.80-0.70)*12");
               System.out.println("eval:==="+o);
//		        double d = Double.parseDouble(o.toString());
           }
       } catch (ScriptException e) {
           e.printStackTrace();
       }*/

   }
        //System.out.println(2014-2000);

      /*  RecentGameTeam gameTeam = new RecentGameTeam();
        System.out.println(gameTeam.getTeamBId());
        FragmentTransaction transaction;
       transaction.setCustomAnimations()*//*
       *//* String s=" {\"expires_in\":86400000,\"refresh_token\":\"483359bd5b2c44b0903b5a1428260022\",\"access_token\":\"4accd6d2d5914de6bd61360930da2cb9\"}";
        Token token=  JSON.parseObject(s, Token.class);
        System.out.println(token);*//*
//        String str="http://shenxinfc.online.sh.cn/sports/gb/content/attachement/jpg/site1/20140825/0025116bbfb11564de1612.jpg";
//        System.out.println(str.substring(str.lastIndexOf("/")));
        System.out.println(DateUtil.timeDifference("2014-09-15","19:00:00"));


  */

    public static void sort(List ls,final String method ){

    }

    static  class  User{
        String id;
        String name;

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
