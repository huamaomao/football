package com.vxfc.shenxin.util;

import com.litesuits.http.parser.BitmapParser;
import com.litesuits.http.request.Request;
import com.litesuits.http.request.param.HttpMethod;
/**
 *  请求api
 */
public class RequestUtil {


    /*****
     * Token 过期验证
     * @param clientId  用户id
     * @param refreshToken  刷新授权码
     * @return 请求对象
     */
    public static Request requestRefreshTokenTask(String clientId,String refreshToken){
        return new Request(Util.jointUrl(UrlApi.ACCESS_TOKEN,UrlApi.WEB_MODEL_BASE)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.CLIENT_ID,clientId)
                .addUrlParam(Dict.REFRESH_TOKEN,refreshToken);
    }

    /***
     * 授权码请求
     * @return 请求对象
     */
    public static Request requestUserToken(){
        return  new Request(Util.jointUrl(UrlApi.AUTHORIZE,UrlApi.WEB_MODEL_BASE)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.CLIENT_ID, Dict.USER_NAME)
                .addUrlParam(Dict.SIGN, Dict.SIGN_CODE).
                        addUrlParam(Dict.REDIRECT_URI, Dict.URL).
                        addUrlParam(Dict.STATE, "1234");
    }

    /************
     * 请求最近一次比赛信息
     * @param userToken 授权码
     * @param teamId  球队id
     * @return  请求对象
     */
    public static  Request requestRecently(String userToken,String teamId){
        return new Request(Util.jointUrl(UrlApi.RECENTLY, UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.TEAM_ID, teamId).addUrlParam(Dict.ACCESS_TOKEN,userToken);
    }

    /*****
     * 获取两队对战历史接口
     * @param userToken  授权码
     * @param team_a_id  球队id
     * @param team_b_id  球队id
     * @return  请求对象
     */
    public static Request requestWarHistory(String userToken,String team_a_id,String team_b_id){
       return new Request(Util.jointUrl(UrlApi.WAR_HISTORY,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.TEAM_B_ID,team_b_id).addUrlParam(Dict.TEAM_A_ID, team_a_id)
                .addUrlParam(Dict.ACCESS_TOKEN,userToken);
    }

    /*****
     *
     * @param userToken   授权码
     * @param teamId  球队id
     * @return 请求对象
     */
    public static Request requestRecentRecord(String userToken,String teamId){
        return new Request(Util.jointUrl(UrlApi.RECENT_RECORD,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.TEAM_ID, teamId)
                .addUrlParam(Dict.ACCESS_TOKEN,userToken);
    }


    /*****
     *  获取积分榜
     * @param userToken   授权码
     * @return 请求对象
     */
    public static Request requestList(String userToken){
        return new Request(Util.jointUrl(UrlApi.LIST,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken);
    }


    /*****
     *  获取新闻列表
     * @param userToken   授权码
     * @return 请求对象
     */
    public static Request requestNewList(String userToken,String page){
        return new Request(Util.jointUrl(UrlApi.NEW_LIST,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).addUrlParam(Dict.PAGE,page);
    }

    /*****
     *  获取新闻详细
     * @param userToken   授权码
     * @param id   新闻id
     * @return 请求对象
     */
    public static Request requestNewDetail(String userToken,String id){
        return new Request(Util.jointUrl(UrlApi.NEW_DETAIL,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).addUrlParam(Dict.ID,id);
    }

    /*****
     *  获取球队荣誉
     * @param userToken   授权码
     * @param teamId   球队id
     * @return 请求对象
     */
    public static Request requestTeamGlories(String userToken,String teamId){
        return new Request(Util.jointUrl(UrlApi.TEAM_GLORIES,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).addUrlParam(Dict.TEAM_ID,teamId);
    }

    /*****
     *  获取球队简介
     * @param userToken   授权码
     * @param teamId   球队id
     * @return 请求对象
     */
    public static Request requestTeamProfile(String userToken,String teamId){
        return new Request(Util.jointUrl(UrlApi.TEAM_PROFILE,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).addUrlParam(Dict.TEAM_ID,teamId);
    }

    /*****
     *  球员信息
     * @param userToken   授权码
     * @param playerId   球队id
     * @return 请求对象
     */
    public static Request requestPlayerInfo(String userToken,String playerId){
        return new Request(Util.jointUrl(UrlApi.PLAYER_INFO,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).addUrlParam(Dict.PLAYER_ID,playerId);
    }
    /*****
     *  球员履历
     * @param userToken   授权码
     * @param playerId   球队id
     * @return 请求对象
     */
    public static Request requestPlayerRecord(String userToken,String playerId){
        return new Request(Util.jointUrl(UrlApi.playerRecord,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).addUrlParam(Dict.PLAYER_ID,playerId);
    }

    /*****
     *  获取球员荣誉
     * @param userToken   授权码
     * @param playerId   球队id
     * @return 请求对象
     */
    public static Request requestPlayerGlories(String userToken,String playerId){
        return new Request(Util.jointUrl(UrlApi.PLAYER_GLORIES,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).addUrlParam(Dict.PLAYER_ID,playerId);
    }

    /***
     * 获取球队对阵信息
     * @param userToken
     * @param fixtureId
     * @param team_id
     * @return
     */
    public static Request requestAgainst(String userToken,String fixtureId,String team_id){
        return new Request(Util.jointUrl(UrlApi.against,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.FXITURE_ID,fixtureId).
                addUrlParam(Dict.TEAM_ID,team_id);

    }

    /****
     * 获取首发名单
     * @param userToken
     * @param teamId
     * @param fixtureId
     * @return
     */
    public static Request requestFistList(String userToken,String teamId,String fixtureId){
        return new Request(Util.jointUrl(UrlApi.fistList,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.TEAM_ID,teamId).
                addUrlParam(Dict.FXITURE_ID,fixtureId);
    }

    /*****
     * 获取球队防守数据(最近三场)
     * @param userToken
     * @param team_a_id
     * @param team_b_id
     * @return
     */
    public static Request requestTeamDefenseData(String userToken,String team_a_id,String team_b_id){
        return new Request(Util.jointUrl(UrlApi.teamDefenseData,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.TEAM_A_ID,team_a_id).
                addUrlParam(Dict.TEAM_B_ID,team_b_id);
    }

    /*****
     * 获取球队进攻数据(最近三场)
     * @param userToken
     * @param team_a_id
     * @param team_b_id
     * @return
     */
    public static Request requestTeamAttackData(String userToken,String team_a_id,String team_b_id){
        return new Request(Util.jointUrl(UrlApi.teamAttackData,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.TEAM_A_ID,team_a_id).
                addUrlParam(Dict.TEAM_B_ID,team_b_id);
    }
    /*****
     * 获取球队常规数据(最近三场)
     * @param userToken
     * @param team_a_id
     * @param team_b_id
     * @return
     */
    public static Request requestTeamGeneralData(String userToken,String team_a_id,String team_b_id){
        return new Request(Util.jointUrl(UrlApi.teamGeneralData,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.TEAM_A_ID,team_a_id).
                addUrlParam(Dict.TEAM_B_ID,team_b_id);
    }
    /*****
     * 获取球队防守数据（一场）
     * @param userToken
     * @param team_a_id
     * @param team_b_id
     * @param fixtureId
     * @return
     */
    public static Request requestTeamLiveDefenseData(String userToken,String team_a_id,String team_b_id,String fixtureId){
        return new Request(Util.jointUrl(UrlApi.teamLiveDefenseData,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.TEAM_A_ID,team_a_id).
                addUrlParam(Dict.TEAM_B_ID,team_b_id)
                .addUrlParam(Dict.FXITURE_ID,fixtureId);
    }

    /*****
     * 获取球队进攻数据(一场):
     * @param userToken
     * @param team_a_id
     * @param team_b_id
     * @param fixtureId
     * @return
     */
    public static Request requestTeamLiveAttackData(String userToken,String team_a_id,String team_b_id,String fixtureId){
        return new Request(Util.jointUrl(UrlApi.teamLiveAttackData,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.TEAM_A_ID,team_a_id).
                addUrlParam(Dict.TEAM_B_ID,team_b_id)
                .addUrlParam(Dict.FXITURE_ID,fixtureId);
    }

    /*****
     * 获取球队常规数据(一场)
     * @param userToken
     * @param team_a_id
     * @param team_b_id
     * @param fixtureId
     * @return
     */
    public static Request requestTeamLiveGeneralData(String userToken,String team_a_id,String team_b_id,String fixtureId){
        return new Request(Util.jointUrl(UrlApi.teamLiveGeneralData,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.TEAM_A_ID,team_a_id).
                addUrlParam(Dict.TEAM_B_ID,team_b_id)
                .addUrlParam(Dict.FXITURE_ID,fixtureId);
    }

    /*****
     * 获取球员历史排行
     * @param userToken
     * @param teamId
     * @return
     */
    public static Request requestHistoricalRankings(String userToken,String teamId,String year){
        return new Request(Util.jointUrl(UrlApi.historicalRankings,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.TEAM_ID,teamId).addUrlParam(Dict.YEAR,year);
    }

    /*****
     * 获取球员历史名单排行
     * @param userToken
     * @param teamId
     * @param year
     * @return
     */
    public static Request requestPlayerList(String userToken,String teamId,String year){
        return new Request(Util.jointUrl(UrlApi.playerList,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.TEAM_ID,teamId).
                addUrlParam(Dict.YEAR,year);
    }
    /*****
     * 获取球员进攻数据
     * @param userToken
     * @param player_id
     * @param year
     * @return
     */
    public static Request requestPlayerAttackData(String userToken,String player_id,String year){
        return new Request(Util.jointUrl(UrlApi.playerAttackData,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.PLAYER_ID,player_id).
                addUrlParam(Dict.YEAR,year);
    }
    /*****
     * 获取球员防守数据
     * @param userToken
     * @param player_id
     * @param year
     * @return
     */
    public static Request requestPlayerDefenseData(String userToken,String player_id,String year){
        return new Request(Util.jointUrl(UrlApi.playerDefenseData,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.PLAYER_ID,player_id).
                addUrlParam(Dict.YEAR,year);
    }

    /*****
     * 获取球员常规数据
     * @param userToken
     * @param player_id
     * @param year
     * @return
     */
    public static Request requestPlayerGeneralData(String userToken,String player_id,String year){
        return new Request(Util.jointUrl(UrlApi.playerGeneralData,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.PLAYER_ID,player_id).
                addUrlParam(Dict.YEAR,year);
    }

    /*****
     * 获取球员排行
     * @param userToken
     * @param team_id
     * @param year
     * @return
     */
    public static Request requestPlayerBankings(String userToken,String team_id,String year){
        return new Request(Util.jointUrl(UrlApi.playerRankings,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.TEAM_ID,team_id).
                addUrlParam(Dict.YEAR,year);
    }
    /*****
     *  赛程
     * @param userToken
     * @param team_id
     * @param year
     * @return
     */
    public static Request requestFixtureList(String userToken,String team_id,String round){
        return new Request(Util.jointUrl(UrlApi.fixtureList,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                addUrlParam(Dict.ACCESS_TOKEN, userToken).
                addUrlParam(Dict.TEAM_ID,team_id).
                addUrlParam(Dict.ROUND,round);
    }


    public static Request requestImage(String path,BitmapParser parser){
      return new  Request(Util.jointUrl(UrlApi.images,UrlApi.WEB_MODEL_CSL))
              .addUrlParam(Dict.PATH,path).setMethod(HttpMethod.Get).setDataParser(parser);
    }

}