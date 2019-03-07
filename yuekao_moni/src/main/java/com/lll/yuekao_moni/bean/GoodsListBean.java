package com.lll.yuekao_moni.bean;

public class GoodsListBean {
//     "aid": 1,
//             "createtime": "2018-12-17T14:53:49",
//             "icon": "http://www.zhaoapi.cn/images/quarter/ad1.png",
//             "title": "第十三界瑞丽模特大赛",
//             "type": 0,
//             "url": "http://m.mv14449315.icoc.bz/index.jsp"
    private int aid;
    private String createtime;
    private String icon;
    private String title;
    private int type;
    private String url;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
