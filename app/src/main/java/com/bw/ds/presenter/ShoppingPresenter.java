package com.bw.ds.presenter;

import com.bw.ds.base.BasePresenter;
import com.bw.ds.bean.ShoppBean;
import com.bw.ds.model.ShoppModel;
import com.bw.ds.view.ShoppView;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 12547
 * @Date: 2019/3/22 13:47:12
 * @Description:
 */
public class ShoppingPresenter extends BasePresenter<ShoppView> {

    private final ShoppModel shoppModel;
    private final ShoppView shoppView;

    public ShoppingPresenter(ShoppView view) {
        shoppModel = new ShoppModel();
        shoppView = view;
    }
      //查询购物车

    public void Shopp(String uid, String sid) {
        shoppModel.Shopp(uid,sid);
        shoppModel.setShoppDate(new ShoppModel.ShoppDate() {
            @Override
            public void getdate(List<ShoppBean.ResultBean> result) {
                shoppView.view(result);
            }
        });
    }








    @Override
    public void date(Map<String, String> map) {
    }

    @Override
    public void regdate(Map<String, String> map) {
    }
}
