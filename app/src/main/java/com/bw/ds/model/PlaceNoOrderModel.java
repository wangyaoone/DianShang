package com.bw.ds.model;

import android.util.Log;

import com.bw.ds.api.Api;
import com.bw.ds.api.ApiService;
import com.bw.ds.bean.PlaceNoPrderOkBean;
import com.bw.ds.util.UtilDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: 12547
 * @Date: 2019/3/28 14:41:38
 * @Description:
 */
public class PlaceNoOrderModel {

    public interface Zz{
          void getdate(PlaceNoPrderOkBean body);
    }
    private Zz zz;

    public void setZz(Zz zz) {
        this.zz = zz;
    }

    public void place(String uid, String sid, String money, int adressid, String date) {
        int i = Integer.parseInt(money);

        Log.i("aaa",uid+"-"+sid);
                     //调用工具类
       ApiService apiService = UtilDate.getUtilDate().getserviserHand(Api.Url, uid, sid, ApiService.class);
        Call<PlaceNoPrderOkBean> call = apiService.getplacenoorder((double)i, adressid, date);
        call.enqueue(new Callback<PlaceNoPrderOkBean>() {
            @Override
            public void onResponse(Call<PlaceNoPrderOkBean> call, Response<PlaceNoPrderOkBean> response) {
                PlaceNoPrderOkBean body = response.body();
                zz.getdate(body);
            }
            @Override
            public void onFailure(Call<PlaceNoPrderOkBean> call, Throwable t) {

            }
        });
    }
}
