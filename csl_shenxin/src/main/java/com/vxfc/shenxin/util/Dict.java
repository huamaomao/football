package com.vxfc.shenxin.util;

/**
 * 字典类
 * Created by Hua on 2014/8/2.
 */
public  interface Dict {

    /****************************   字典          *******************************/
    final String ACCESS_TOKEN = "access_token";
    final String STATE = "state";
    final String EXPIRES_IN = "expires_in";
    final String REFRESH_TOKEN = "refresh_token";
    final String CLIENT_ID="client_id";
    final String REDIRECT_URI="redirect_uri";
    final String SIGN="sign";
    final String ID="id";
    final String BUNDLE="bundle";
    final String SERIALIZABLE="serializable";

    final String PLAYER_NAME="playerName";
    final String TEAM_ID="team_id";
    final String TEAM_A_ID="team_a_id";
    final String TEAM_B_ID="team_b_id";
    final String  PLAYER_ID="player_id";
    final String  FXITURE_ID="fixture_id";
    final String  YEAR="year";
    final String  ROUND="round";
    final String  PAGE="page";
    final String  MODEL="model";
    final String  PATH="path";


    /**************************    常量   *********************************************/
    final  int HANDLER_CHENGE_PAGE = 0;
    final  int TIME_UPDATE = 1;
    final  int TIME_PLAY = 2;

    String USER_NAME="ningyu";
    String SIGN_CODE="828924d507d18e60158f492eed3d81e8f9e800ef";
    String  URL="www.baidu.com";

    final String cache_image_news="/ShenXin/image/news";




    public static final String FISRT_N = "N";
    public static final String FISRT_Y= "Y";



    /**
     * 主队胜
     */
    public static final String SCORE_A = "A";
    /**
     * 客队胜
     */
    public static final String SCORE_B = "B";
    /**
     * 平
     */
    public static final String SCORE_C = "C";

    /**
     * 门将
     */
    public static final int POSITION_GOALKEEPER = 1;
    /**
     * 后卫
     */
    public static final int POSITION_DEFENDER = 2;
    /**
     * 前卫/中场
     */
    public static final int POSITION_MIDFIELDER = 3;
    /**
     * 前锋/中锋
     */
    public static final int POSITION_STRIKER = 4;


    /************************handle***code********************************/
    public static final int data_change=0;
    /**
     * 未开赛
     */
    public static final String STATUS_N = "N";
    /**
     * 已完赛
     */
    public static final String STATUS_F = "F";
    /**
     * 赛前
     */
    public static final String STATUS_P= "P";
    /**
     * 上半场
     */
    public static final String STATUS_1  = "1";
    /**
     * 下半场
     */
    public static final String STATUS_2  = "2";
     /**
     * 加时赛-上半场
     */
    public static final String STATUS_3  = "3";
    /**
     * 加时赛-下半场
     */
    public static final String STATUS_4  = "4";
    /**
     * 点球大战
     */
    public static final String STATUS_5  = "5";
    /**
     * 中场休息
     */
    public static final String STATUS_6  = "6";
    /**
     * 加时赛-中场休息
     */
    public static final String STATUS_7  = "7";


}
