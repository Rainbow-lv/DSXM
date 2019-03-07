package com.lll.weidu.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {
    //    "headPic": "http://172.17.8.100/images/small/default/user.jpg",
//            "nickName": "N7_74Am6",
//            "phone": "18811212759",
//            "sessionId": "155004125388332",
//            "sex": 1,
//            "userId": 32
   @Id(autoincrement = true)
    private long userId;
    private String headPic;
    private String nickName;
    private String phone;
    private String sessionId;
    private int sex;
    private int status;

    @Generated(hash = 2127177811)
    public User(long userId, String headPic, String nickName, String phone,
            String sessionId, int sex, int status) {
        this.userId = userId;
        this.headPic = headPic;
        this.nickName = nickName;
        this.phone = phone;
        this.sessionId = sessionId;
        this.sex = sex;
        this.status = status;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
