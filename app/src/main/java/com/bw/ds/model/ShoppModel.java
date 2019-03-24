package com.bw.ds.model;

import android.util.Log;

import com.bw.ds.api.Api;
import com.bw.ds.api.ApiService;
import com.bw.ds.bean.ShoppBean;
import com.bw.ds.util.UtilDate;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: 12547
 * @Date: 2019/3/22 14:00:07
 * @Description:
 */
public class ShoppModel {

    //接口回调值
      public interface ShoppDate{
           void getdate(List<ShoppBean.ResultBean> result);
      }
      private ShoppDate shoppDate;

      public void setShoppDate(ShoppDate shoppDate) {
        this.shoppDate = shoppDate;
      }

    public void Shopp(String uid, String sid) {
        //找网络工具类
        ApiService apiService = UtilDate.getUtilDate().getserviserHand(Api.Url, uid, sid, ApiService.class);
        Call<ShoppBean> call = apiService.getshopp();
           call.enqueue(new Callback<ShoppBean>() {
               @Override
               public void onResponse(Call<ShoppBean> call, Response<ShoppBean> response) {
                   List<ShoppBean.ResultBean> result = response.body().getResult();
                   shoppDate.getdate(result);
               }

               @Override
               public void onFailure(Call<ShoppBean> call, Throwable t) {

               }
           });
    }
}
