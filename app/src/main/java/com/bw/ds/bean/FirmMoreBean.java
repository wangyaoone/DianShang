package com.bw.ds.bean;

/**
 * @Auther: 12547
 * @Date: 2019/3/28 14:18:42
 * @Description:
 */
public class FirmMoreBean {
    private String commodityId;
    private String amount;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public FirmMoreBean(String commodityId, String amount) {
        this.commodityId = commodityId;
        this.amount = amount;
    }
}
