package com.bw.ds.presenter;

import android.util.Log;

import com.bw.ds.base.BasePresenter;
import com.bw.ds.bean.DetailsBean;
import com.bw.ds.bean.ShoppBean;
import com.bw.ds.model.DetailsModel;
import com.bw.ds.view.DetailsView;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 12547
 * @Date: 2019/3/21 15:34:05
 * @Description:
 */
public class DetailsPresenter extends BasePresenter<DetailsView> {

    private final DetailsModel detailsModel;
    private final DetailsView detailsView;

    public DetailsPresenter(DetailsView view) {
        detailsModel = new DetailsModel();
        detailsView = view;
    }

    public void detailsDate(int id) {
        detailsModel.details(id);
        detailsModel.setHdate(new DetailsModel.Hdate() {
            @Override
            public void getdate(DetailsBean.ResultBean result) {
                detailsView.view(result);
            }

            @Override
            public void chashopp(List<ShoppBean.ResultBean> result) {
               /* Log.i("aa",result+"");*/
                detailsView.vieww(result);
            }

            @Override
            public void addshopp(String message) {
                detailsView.viewww(message);
            }
        });
    }

    //进行查询购物车
    public void chaShopp(String uid, String sid) {
        detailsModel.chaShopp(uid,sid);
    }

     //添加购物车
    public void addShopp(String uid, String sid, String sss) {
        detailsModel.addShopp(uid,sid,sss);
    }


    @Override
    public void date(Map<String, String> map) {

    }

    @Override
    public void regdate(Map<String, String> map) {

    }


}
