package com.vxfc.shenxin.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *对战历史
 * Created by Hua on 2014/8/2.
 */
public class AgainstHistorical {
   private String num;//两队公开战过几场
   private String win;//主队胜了几场
   private String ping;//主队平了几场
   private String  fu;//主队输了几场
   private List<RecentGameTeam> items=new ArrayList<RecentGameTeam>();//场次结合

    @Override
    public String toString() {
        return "AgainstHistorical{" +
                "num='" + num + '\'' +
                ", win='" + win + '\'' +
                ", ping='" + ping + '\'' +
                ", fu='" + fu + '\'' +
                ", items=" + items +
                '}';
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public String getFu() {
        return fu;
    }

    public void setFu(String fu) {
        this.fu = fu;
    }

    public List<RecentGameTeam> getItems() {
        return items;
    }

    public void setItems(List<RecentGameTeam> items) {
        this.items = items;
    }
}
