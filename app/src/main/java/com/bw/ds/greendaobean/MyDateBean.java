package com.bw.ds.greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Auther: 12547
 * @Date: 2019/3/24 13:26:21
 * @Description:
 */
@Entity
public class MyDateBean {

        @Id(autoincrement = true)
        private long id;
        private String name;
        @Generated(hash = 985590452)
        public MyDateBean(long id, String name) {
            this.id = id;
            this.name = name;
        }
        @Generated(hash = 154959573)
        public MyDateBean() {
        }
        public long getId() {
            return this.id;
        }
        public void setId(long id) {
            this.id = id;
        }
        public String getName() {
            return this.name;
        }
        public void setName(String name) {
            this.name = name;
        }


}
