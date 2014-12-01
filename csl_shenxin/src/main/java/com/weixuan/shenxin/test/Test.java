package com.weixuan.shenxin.test;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Hua on 2014/8/2.
 */
public class Test {
   
   /* public static void main(String[] arg){
        //System.out.println(2014-2000);

      *//*  RecentGameTeam gameTeam = new RecentGameTeam();
        System.out.println(gameTeam.getTeamBId());
        FragmentTransaction transaction;
       transaction.setCustomAnimations()*//*
       *//* String s=" {\"expires_in\":86400000,\"refresh_token\":\"483359bd5b2c44b0903b5a1428260022\",\"access_token\":\"4accd6d2d5914de6bd61360930da2cb9\"}";
        Token token=  JSON.parseObject(s, Token.class);
        System.out.println(token);*//*
//        String str="http://shenxinfc.online.sh.cn/sports/gb/content/attachement/jpg/site1/20140825/0025116bbfb11564de1612.jpg";
//        System.out.println(str.substring(str.lastIndexOf("/")));
        System.out.println(DateUtil.timeDifference("2014-09-15","19:00:00"));


    }*/

    public static void sort(List ls,final String method ){
        Collections.sort(ls, new Comparator() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                   Field field1= a.getClass().getDeclaredField(method);
                   Field field2= a.getClass().getDeclaredField(method);
                    ret=Integer.valueOf(field1.get(a).toString()).compareTo(Integer.valueOf(field2.get(b).toString()));
                } catch (Exception ne) {
                }
                return ret;
            }
        });
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
