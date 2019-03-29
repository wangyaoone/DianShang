package com.bw.ds.model;

import android.util.Log;

import com.bw.ds.api.Api;
import com.bw.ds.api.ApiService;
import com.bw.ds.bean.PayBean;
import com.bw.ds.util.UtilDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: 12547
 * @Date: 2019/3/29 15:26:01
 * @Description:
 */
public class PayModel {

    //接口回调
       public interface PayDate{
           void  getdate(String message);
       }
       private PayDate payDate;

       public void setPayDate(PayDate payDate) {
           this.payDate = payDate;
       }

    public void pay(String uid, String sid, String orderId) {
           //Log.i("xxx",uid+"-"+sid+"-"+orderId);
             //调用工具类
        ApiService apiService = UtilDate.getUtilDate().getserviserHand(Api.Url,uid, sid,  ApiService.class);
        Call<PayBean> call = apiService.getpay(orderId, 1);
        call.enqueue(new Callback<PayBean>() {
            @Override
            public void onResponse(Call<PayBean> call, Response<PayBean> response) {
                String message = response.body().getMessage();
                payDate.getdate(message);
            }
            @Override
            public void onFailure(Call<PayBean> call, Throwable t) {

            }
        });
    }
}
