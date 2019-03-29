package com.bw.ds.bean;

/**
 * @Auther: 12547
 * @Date: 2019/3/27 19:55:34
 * @Description:
 */
public class OrderForGoodsBean {

    private int commodityId;
    private String commodityName;
    private int count;
    private String pic;
    private int price;
    private boolean ischecked=false;

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isIschecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    public OrderForGoodsBean(int commodityId, String commodityName, int count, String pic, int price, boolean ischecked) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.count = count;
        this.pic = pic;
        this.price = price;
        this.ischecked = ischecked;
    }
}
