package com.bw.ds.bean;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/26 11:54:05
 * @Description:
 */
public class CircleBean {

    /**
     * result : [{"commodityId":1,"content":"元气满满","createTime":1553216151000,"greatNum":5,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":788,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-21/2380920190321195551.png","nickName":"2A_dE4I6","userId":752,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552607214000,"greatNum":1,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-13/20190313115956.png","id":787,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-14/2346920190314184654.jpg","nickName":"nanye1112","userId":1636,"whetherGreat":2}]
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
         * commodityId : 1
         * content : 元气满满
         * createTime : 1553216151000
         * greatNum : 5
         * headPic : http://172.17.8.100/images/small/default/user.jpg
         * id : 788
         * image : http://172.17.8.100/images/small/circle_pic/2019-03-21/2380920190321195551.png
         * nickName : 2A_dE4I6
         * userId : 752
         * whetherGreat : 2
         */

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
}
