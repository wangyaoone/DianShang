package com.bw.ds.model;

import android.util.Log;

import com.bw.ds.api.Api;
import com.bw.ds.api.ApiService;
import com.bw.ds.bean.BannaBean;
import com.bw.ds.bean.PageBean;
import com.bw.ds.util.UtilDate;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: 12547
 * @Date: 2019/3/20 20:56:14
 * @Description:
 */
public class PageModel {

    public interface Date{
        //轮播的数据
        void getdate(List<BannaBean.ResultBean> date);
        //首页展示的数据
        void getpagedate(PageBean.ResultBean result);
    }
    private Date date;
    public void setDate(Date date) {
        this.date = date;
    }

      //轮播的数据
    public void bannar() {
        ApiService getserviser = UtilDate.getUtilDate().getserviser(Api.Url, ApiService.class);
        Call<BannaBean> call = getserviser.getban();
        call.enqueue(new Callback<BannaBean>() {
            @Override
            public void onResponse(Call<BannaBean> call, Response<BannaBean> response) {
              List<BannaBean.ResultBean> result = response.body().getResult();
                date.getdate(result);

            }
            @Override
            public void onFailure(Call<BannaBean> call, Throwable t) {

            }
        });
    }
     //首页展示的数据
    public void PageDate() {
        ApiService getserviser = UtilDate.getUtilDate().getserviser(Api.Url, ApiService.class);
        Call<PageBean> call = getserviser.getpage();
       call.enqueue(new Callback<PageBean>() {
            @Override
            public void onResponse(Call<PageBean> call, Response<PageBean> response) {
                PageBean.ResultBean result = response.body().getResult();
                date.getpagedate(result);
            }
            @Override
            public void onFailure(Call<PageBean> call, Throwable t) {

            }
        });
    }
}
