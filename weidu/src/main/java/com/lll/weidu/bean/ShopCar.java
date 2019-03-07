package com.lll.weidu.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShopCar {
    @Id(autoincrement = true)
    private long id;
    private int commodityId;
    private String commodityName;
    private int count;
    private String pic;
    private double price;
    private boolean check;
    private int selected;
    @Generated(hash = 323922099)
    public ShopCar(long id, int commodityId, String commodityName, int count,
            String pic, double price, boolean check, int selected) {
        this.id = id;
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.count = count;
        this.pic = pic;
        this.price = price;
        this.check = check;
        this.selected = selected;
    }
    @Generated(hash = 1637372148)
    public ShopCar() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getCommodityId() {
        return this.commodityId;
    }
    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }
    public String getCommodityName() {
        return this.commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public boolean getCheck() {
        return this.check;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }
    public int getSelected() {
        return this.selected;
    }
    public void setSelected(int selected) {
        this.selected = selected;
    }
    
}
