package com.vxfc.shenxin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hua_ on 2015/2/27.
 */
public class NewsPage implements Serializable {
    public String page;
    public String size;
    public String lastTime;
    public List<News> items;
}
