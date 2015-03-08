package com.vxfc.shenxin.util;


import com.vxfc.common.util.CommonUtil;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.R;

/***
 *球队
 */
public enum Team {
    All("0","全部球队",0,0),
    ShenXin("14","上海申鑫", R.drawable.team_shenxin,R.drawable.team_shenxin_t),
    GuoAn("1","北京国安", R.drawable.team_guoan,R.drawable.team_guoan_t) ,
    AErBin("2","大连阿尔滨",R.drawable.team_aerbing,R.drawable.team_aerbing_t),
    FuLi("3","广州富力",R.drawable.team_fuli,R.drawable.team_fuli_t),
    RenHe("4","贵州人和",R.drawable.team_renhe,R.drawable.team_renhe_t),
    YiTeng("5","哈尔滨毅腾",R.drawable.team_yiteng,R.drawable.team_yiteng_t),
    LuCheng("6","杭州绿城",R.drawable.team_lucheng,R.drawable.team_lucheng_t),
    JianYe("7","河南建业",R.drawable.team_jianye,R.drawable.team_jianye_t),
    HengDa("8","广州恒大",R.drawable.team_hengda,R.drawable.team_hengda_t),
    ShunTian("9","江苏舜天",R.drawable.team_shuntian,R.drawable.team_shuntian_t),
    HongYuan("10","辽宁宏远",R.drawable.team_hongyuang,R.drawable.team_hongyuang_t),
    LuNeng("11","山东鲁能",R.drawable.team_luneng,R.drawable.team_lucheng_t),
    ShangGang("12","上海上港",R.drawable.team_shanggang,R.drawable.team_shanggang_t),
    LuDi("13","上海申花",R.drawable.team_shenhua,R.drawable.team_shenhua_t),
    TaiDa("15","天津泰达",R.drawable.team_taida,R.drawable.team_taida_t),
    YaTai("16","长春亚泰",R.drawable.team_yatai,R.drawable.team_yatai_t);
    public String id;
    public String name;
    /** 透明 **/
    public int icon_t;
    public int icon;
    private Team(String id,String name,int icon,int  icon_t){
        this.id=id;
        this.name=name;
        this.icon=icon;
        this.icon_t=icon_t;
    }



    /****
     *   获取球员位置
     * @param location
     * @return
     */
    public static String getPlayerLocation(String location){
           String str=null;
            switch (location){
                case "1":
                    str="门";
                    break;
                case "2":
                     str="卫";
                    break;
                case "3":
                    str="中";
                    break;
                case "4":
                    str="锋";
                    break;

            }
        return  str;
    }
    /****
     *  球员类型
     * @param type
     * @return
     */
    public static String getPlayerType(String type){
        String str=null;
        switch (type){
            case "Y":
                str="首发";
                break;
            case "N":
                str="替补";
                break;
        }
        return  str;
    }
    /****
     *  显示替补（替）
     * @param type
     * @return
     */
    public static String getPlayerSubstitute(String type){
        String str="";
        switch (type){
            case "N":
                str="(替)";
                break;
        }
        return  str;
    }

    public static String getPlayStatus(String type){
        String str=null;
        if(CommonUtil.isEmpty(type)) return "";
        switch (type){
            case "N":
                str="未赛";
                break;
            case "I":
                str="直播";
                break;
            case "F":
                str="结束 ";
                break;
        }
        return  str;
    }


    /***
     *  透明icon
     * @param teamId
     */
    public static int  getTeamIconT(String teamId){
      for (Team team:Team.values()){
            if (team.id.equals(teamId)){
                return team.icon_t;
            }
        }
        return  0;
    }

    /***
     *
     * @param teamId
     */
    public static   int  getTeamIcon(String teamId){
        for (Team team:Team.values()){
            if (team.id.equals(teamId)){
                return team.icon;
            }
        }
        return 0;
    }

    /***
     *
     * @param score
     * @param flag true  主场   false  客场
     * @return
     */
    public static   int  getScore(String score,boolean flag){
       int scoreRes=0;
       if (Util.isEmpty(score)){
           return scoreRes;
       }
      switch (score){
          case Dict.SCORE_A:
              scoreRes=flag?R.drawable.shape_round_red:R.drawable.shape_round_yellow;
              break;
          case Dict.SCORE_B:
              scoreRes=flag?R.drawable.shape_round_yellow:R.drawable.shape_round_red;
              break;
          case Dict.SCORE_C:
              scoreRes=R.drawable.shape_round_green;
              break;
      }
        return scoreRes;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static void main(String[] args) {

           switch (""){
               case Dict.STATUS_N:

                   break;
               case Dict.STATUS_F:

                   break;
               case Dict.STATUS_P:
                   break;
               case Dict.STATUS_1:

                   break;
               case Dict.STATUS_2:

                   break;
               case Dict.STATUS_3:

                   break;
               case Dict.STATUS_4:

                   break;
               case Dict.STATUS_5:

                   break;
               case Dict.STATUS_6:

                   break;
               case Dict.STATUS_7:

                   break;
           }
    }
}
