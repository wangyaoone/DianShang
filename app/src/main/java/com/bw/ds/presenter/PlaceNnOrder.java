package com.bw.ds.presenter;

import com.bw.ds.base.BaseFragmentPragrent;
import com.bw.ds.bean.PlaceNoPrderOkBean;
import com.bw.ds.model.PlaceNoOrderModel;
import com.bw.ds.view.PlaceNoOrderView;

/**
 * @Auther: 12547
 * @Date: 2019/3/28 14:37:13
 * @Description:
 */
public class PlaceNnOrder extends BaseFragmentPragrent<PlaceNoOrderView> {

    private final PlaceNoOrderModel placeNoOrderModel;
    private final PlaceNoOrderView placeNoOrderView;


    public PlaceNnOrder(PlaceNoOrderView view) {
        placeNoOrderModel = new PlaceNoOrderModel();
        placeNoOrderView = view;
    }

    public void place(String uid, String sid, String money, int adressid, String date) {
        placeNoOrderModel.place(uid,sid,money,adressid,date);
        placeNoOrderModel.setZz(new PlaceNoOrderModel.Zz() {
            @Override
            public void getdate(PlaceNoPrderOkBean body) {
                placeNoOrderView.view(body);
            }
        });
    }

    @Override
    public void Bana() {

    }

    @Override
    public void PageDate() {

    }
}
