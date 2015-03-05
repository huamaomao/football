package com.vxfc.shenxin.util;

import com.litesuits.http.request.Request;
import com.litesuits.http.request.param.HttpMethod;
import com.vxfc.common.util.CommonUtil;
import com.vxfc.shenxin.domian.param.CslParam;
import com.vxfc.shenxin.domian.param.MemberParam;
import com.vxfc.shenxin.domian.param.NewParam;
import com.vxfc.shenxin.domian.param.SignParam;

/**
 *  请求api
 */
public class RequestUtil {

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
     * @param newParam
     * @return 请求对象
     */
    public static Request requestNewList(NewParam newParam){
        return new Request(Util.jointUrl(UrlApi.NEW_LIST,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
               setParamModel(newParam);
    }
    /*****
     *  获取下一页新闻列表
     * @param newParam
     * @return 请求对象
     */
    public static Request requestNextNewList(NewParam newParam){
        return new Request(Util.jointUrl(UrlApi.NEXT_NEW_LIST,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
                setParamModel(newParam);
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
     * @param param
     * @return
     */
    public static Request requestFistList(CslParam param){
        return new Request(Util.jointUrl(UrlApi.fistList,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
              setParamModel(param);
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
     * @param param
     * @return
     */
    public static Request requestFixtureList(CslParam param){
        return new Request(Util.jointUrl(UrlApi.fixtureList,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get).
              setParamModel(param);
    }
  /*  public static Request requestImage(String path,BitmapParser parser){
      return new  Request(Util.jointUrl(UrlApi.images,UrlApi.WEB_MODEL_CSL))
              .addUrlParam(Dict.PATH,path).setMethod(HttpMethod.Get).setDataParser(parser);
    }*/

    /*****
     * 预备队 一线队
     * @param param
     * @return
     */
    public static Request requestFirstTeamPlayer(CslParam param){
        return new  Request(Util.jointUrl(UrlApi.firstTeamPlayer,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get)
                .setParamModel(param);
    }

    /****
     *教练组
     * @param param
     * @return
     */
    public static Request requestCoachTeamList(CslParam param){
        return new  Request(Util.jointUrl(UrlApi.coachTeamList,UrlApi.WEB_MODEL_CSL)).setMethod(HttpMethod.Get)
                .setParamModel(param);
    }


    /*********MEMBER  会员****************/
    /****
     * 会员注册
     * @param param
     * @return
     */
    public static Request requestRegister(MemberParam param) {
        return new Request(Util.jointUrl(UrlApi.REGISTER,UrlApi.WEB_MODEL_MEMBER)).setMethod(HttpMethod.Post).
             setParamModel(param);
    }
    /****
     * 3rd会员注册
     * @param param
     * @return
     */
    public static Request requestRegister3rd(MemberParam param) {
        return new Request(Util.jointUrl(UrlApi.REGISTER_3RD,UrlApi.WEB_MODEL_MEMBER)).setMethod(HttpMethod.Post).
                setParamModel(param);
    }
    /****
     * request sms code
     * @param param
     * @return
     */
    public static Request requestSms(MemberParam param) {
        return new Request(Util.jointUrl(UrlApi.SMS,UrlApi.WEB_MODEL_MEMBER)).setMethod(HttpMethod.Post).
                setParamModel(param);
    }
    /****
     * login
     * @param param
     * @return
     */
    public static Request requestLogin(MemberParam param) {
        return new Request(Util.jointUrl(UrlApi.LOGIN,UrlApi.WEB_MODEL_MEMBER)).setMethod(HttpMethod.Post).
                setParamModel(param);
    }
    /****
     * 用户信息
     * @param param
     * @return
     */
    public static Request requestMemberInfo(MemberParam param) {
        return new Request(Util.jointUrl(UrlApi.MEMBER_INFO,UrlApi.WEB_MODEL_MEMBER)).setMethod(HttpMethod.Get).
                setParamModel(param);
    }

    /****
     * 是否签到
     * @param param
     * @return
     */
    public static Request requestIsSign(SignParam param) {
        return new Request(Util.jointUrl(UrlApi.IS_SIGN,UrlApi.WEB_MODEL_MEMBER)).setMethod(HttpMethod.Get).
                setParamModel(param);
    }
    /****
     * 签到
     * @param param
     * @return
     */
    public static Request requestSign(SignParam param) {
        return new Request(Util.jointUrl(UrlApi.SIGN,UrlApi.WEB_MODEL_MEMBER)).setMethod(HttpMethod.Post).
                setParamModel(param);
    }

    /****
     * 是否主场签到
     * @param param
     * @return
     */
    public static Request requestIsTeamSign(SignParam param) {
        return new Request(Util.jointUrl(UrlApi.IS_TEAM_SIGN,UrlApi.WEB_MODEL_MEMBER)).setMethod(HttpMethod.Get).
                setParamModel(param);
    }
    /****
     * 主场签到
     * @param param
     * @return
     */
    public static Request requestTeamSign(SignParam param) {
        return new Request(Util.jointUrl(UrlApi.TEAM_SIGN,UrlApi.WEB_MODEL_MEMBER)).setMethod(HttpMethod.Post).
                setParamModel(param);
    }

    public static String getUrl(String path){
        if(!CommonUtil.isEmpty(path)){
            return null;
        }
        StringBuilder builder=new StringBuilder(UrlApi.SERVER_IP);
        builder.append(UrlApi.WEB_MODEL_CSL).append("/").append(path);
        return builder.toString();
    }
    public static String requestHtm(String id){
        if (Util.isEmpty(id)) return "";
        StringBuilder builder=new StringBuilder(UrlApi.SERVER_IP);
        builder.append(UrlApi.WEB_MODEL_CSL).
                append("/").append(id).append(".htm");
        return builder.toString();
    }

    /*****
     *
     * @param url
     * @return
     */
    public static String getUrl(String url){
        if (Util.isEmpty(url)) return null;
        StringBuilder builder=new StringBuilder(UrlApi.SERVER_IP);
        builder.append(UrlApi.WEB_MODEL_CSL).append("/").append(url);
        return builder.toString();
    }

    /****
     *
     * @param id
     * @return
     */
    public static String requestHtm(String id){
        if (Util.isEmpty(id)) return "";
        StringBuilder builder=new StringBuilder(UrlApi.SERVER_IP);
        builder.append(UrlApi.WEB_MODEL_CSL).
                append("/").append(id).append(".htm");
        return builder.toString();
    }


}
