package com.bw.ds.model;

import android.util.Log;

import com.bw.ds.api.Api;
import com.bw.ds.api.ApiService;
import com.bw.ds.bean.SearchBean;
import com.bw.ds.util.UtilDate;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: 12547
 * @Date: 2019/3/24 15:20:41
 * @Description:
 */
public class SearchModel {

    //接口回调
      public  interface SearCh{
           void getdate(List<SearchBean.ResultBean> result);
      }
      private SearCh searCh;

      public void setSearCh(SearCh searCh) {
          this.searCh = searCh;
      }

    public void search(String name, int page) {
                 //调用工具类
        ApiService getserviser = UtilDate.getUtilDate().getserviser(Api.Url, ApiService.class);
        Call<SearchBean> call = getserviser.getsearch(name, page, 5);
             call.enqueue(new Callback<SearchBean>() {
                 @Override
                 public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                     List<SearchBean.ResultBean> result = response.body().getResult();
                     searCh.getdate(result);
                 }
                 @Override
                 public void onFailure(Call<SearchBean> call, Throwable t) {

                 }
             });
    }
}
