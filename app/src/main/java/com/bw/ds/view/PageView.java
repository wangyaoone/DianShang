package com.bw.ds.view;

import com.bw.ds.bean.BannaBean;
import com.bw.ds.bean.PageBean;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/20 21:19:31
 * @Description:
 */
public interface PageView {
        void banna(List<BannaBean.ResultBean> date);
        void pagedate(PageBean.ResultBean result);
}
