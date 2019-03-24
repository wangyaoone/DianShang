package com.bw.ds.view;

import com.bw.ds.bean.DetailsBean;
import com.bw.ds.bean.ShoppBean;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/21 15:56:02
 * @Description:
 */
public interface DetailsView {
       void view(DetailsBean.ResultBean result);
       void vieww(List<ShoppBean.ResultBean> result);
       void viewww(String message);
}
