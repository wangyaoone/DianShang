package com.bw.ds.bean;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/31 14:37:52
 * @Description:
 */
public class ThreeShoppBean {


    /**
     * result : [{"commodityId":52,"commodityName":"唐狮女鞋夏季女款凉鞋女士凉鞋女鞋平底女士凉拖夹脚女鞋凉鞋休闲凉鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/lx/7/1.jpg","price":109,"saleNum":0},{"commodityId":49,"commodityName":"唐狮女士凉鞋女鞋平底凉鞋女士凉拖露趾女鞋凉鞋休闲凉鞋女夏季女款凉鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/lx/4/1.jpg","price":109,"saleNum":0},{"commodityId":46,"commodityName":"新品韩版闪亮少女一字扣女士凉鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/lx/1/1.jpg","price":149,"saleNum":0},{"commodityId":51,"commodityName":"唐狮夏季女鞋女士凉鞋女款平底凉鞋百搭女士凉拖夹脚女凉鞋休闲凉鞋女","masterPic":"http://172.17.8.100/images/small/commodity/nx/lx/6/1.jpg","price":109,"saleNum":0},{"commodityId":48,"commodityName":"夏季新款少女百搭一字扣女士凉鞋凉拖女鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/lx/3/1.jpg","price":79,"saleNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 52
         * commodityName : 唐狮女鞋夏季女款凉鞋女士凉鞋女鞋平底女士凉拖夹脚女鞋凉鞋休闲凉鞋
         * masterPic : http://172.17.8.100/images/small/commodity/nx/lx/7/1.jpg
         * price : 109
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

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

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
