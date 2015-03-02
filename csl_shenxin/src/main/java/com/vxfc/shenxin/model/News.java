package com.vxfc.shenxin.model;

/***
 * 
 * @ClassName: News
 * @Description: 新闻
 * @author Hua
 * @date 2014-8-20
 * 
 */
public class News {
    public String id;
    public String title;
    public String content;
    public String type;
    public String time;
    public String contentTitle;
    public String createTime;
    public String thumbnailPath;
    public String fromLocation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (!id.equals(news.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
