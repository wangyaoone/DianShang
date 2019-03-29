package com.bw.ds.presenter;

import com.bw.ds.base.BasePresenter;
import com.bw.ds.model.PayModel;
import com.bw.ds.view.PayView;

import java.util.Map;

/**
 * @Auther: 12547
 * @Date: 2019/3/29 15:23:07
 * @Description:
 */
public class PayPresenter extends BasePresenter<PayView> {

    private final PayModel payModel;
    private final PayView payView;

    public PayPresenter(PayView view) {
        payModel = new PayModel();
        payView = view;
    }

    public void pay(String uid, String sid, String orderId) {
        payModel.pay(uid,sid,orderId);
        payModel.setPayDate(new PayModel.PayDate() {
            @Override
            public void getdate(String message) {
                payView.view(message);
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
