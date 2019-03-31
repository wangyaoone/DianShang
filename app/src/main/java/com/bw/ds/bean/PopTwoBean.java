package com.bw.ds.bean;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/30 16:31:04
 * @Description:
 */
public class PopTwoBean {

    /**
     * result : [{"id":"1001003001","name":"皮鞋"},{"id":"1001003002","name":"商务休闲鞋"},{"id":"1001003003","name":"板鞋"},{"id":"1001003004","name":"帆布鞋"},{"id":"1001003005","name":"运动鞋"}]
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
         * id : 1001003001
         * name : 皮鞋
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
