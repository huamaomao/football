package com.vxfc.shenxin.util;

/**
 * 网络请求
 *
 */
public interface UrlApi {
    enum IP{
        S("http://vxfc.com.cn:9999/"),L("http://vxfc.net/");
        public String value;
        private IP(String value){
            this.value=value;
        }
    }
    final static String SERVER_IP =IP.S.value;
    final static String WEB_MODEL_MEMBER = "member-api-server";
    final static String WEB_MODEL_CSL = "csl-api-server";
    //final static String AUTHORIZE = "authorize";//获取授权码接口
   // final static String ACCESS_TOKEN = "access_token";//通过刷新码刷新授权码接口
    final static String LOGIN = "login";//用户登录
    final static String SMS = "sms";//使用短信验证接口
    final static String REGISTER = "register";//用户注册
    final static String RECENTLY = "recently";//获取首页的球队最近一次比赛信息（未开赛倒计时，开赛直播）
    final static String WAR_HISTORY = "warHistory";//获取两队对战历史
    final static String RECENT_RECORD = "recentRecord";//获取球队历史战绩
    final static String LIST = "integral";//获取积分榜
    final static String NEW_LIST = "firstPageNewsList";//取新闻
    final static String NEXT_NEW_LIST = "nextPageNewsList";// 下一页新闻
    final static String NEW_DETAIL = "newsDetail";//新闻详细
    final static String TEAM_PROFILE = "teamProfile";//球队简介
    final static String TEAM_GLORIES = "teamGlories";//球队荣誉
    final static String PLAYER_INFO = "playerInfo";//球员信息
    final static String PLAYER_GLORIES = "playerGlories";//球员历史荣誉
    final static String against = "against";//获取球队对阵信息
    final static String fistList = "fistList";// 获取首发名单
    final static String teamDefenseData = "teamDefenseData";//获取球队防守数据(最近三场)
    final static String teamAttackData = "teamAttackData";//获取球队进攻数据(最近三场)
    final static String teamGeneralData = "teamGeneralData";//获取球队常规数据(最近三场)
    final static String teamLiveDefenseData = "teamLiveDefenseData";//获取球队防守数据（一场）
    final static String teamLiveAttackData = "teamLiveAttackData";//获取球队进攻数据(一场):
    final static String teamLiveGeneralData = "teamLiveGeneralData";//获取球队常规数据(一场)
    final static String historicalRankings = "historicalRankings";//获取球员历史排行
    final static String playerList = "playerList";//获取球员历史名单排行
    final static String playerAttackData = "playerAttackData";//获取球员进攻数据
    final static String playerDefenseData = "playerDefenseData";//获取球员防守数据
    final static String playerGeneralData = "playerGeneralData";//获取球员常规数据
    final static String playerRankings = "playerRankings";//获取球员排行
    final static String fixtureList = "fixtureList";//赛程列表
    final static String playerRecord = "playerRecord";//球员履历


}
