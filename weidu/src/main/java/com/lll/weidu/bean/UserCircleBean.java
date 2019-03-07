package com.lll.weidu.bean;

public class UserCircleBean {
    //    "commodityId": 1,
//            "content": "哈哈",
//            "createTime": 1550627255000,
//            "greatNum": 0,
//            "headPic": "http://172.17.8.100/images/small/default/user.jpg",
//            "id": 340,
//            "image": "",
//            "nickName": "N7_74Am6",
//            "userId": 32
    private int commodityId;
    private String content;
    private long createTime;
    private int greatNum;
    private String headPic;
    private int id;
    private String image;
    private String nickName;
    private int userId;
    private int whetherGreat;

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getGreatNum() {
        return greatNum;
    }

    public void setGreatNum(int greatNum) {
        this.greatNum = greatNum;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWhetherGreat() {
        return whetherGreat;
    }

    public void setWhetherGreat(int whetherGreat) {
        this.whetherGreat = whetherGreat;
    }
}
