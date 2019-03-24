package com.bw.ds.model;

import android.util.Log;

import com.bw.ds.api.Api;
import com.bw.ds.api.ApiService;
import com.bw.ds.base.BaseModel;
import com.bw.ds.bean.LoginBean;
import com.bw.ds.bean.RegisterBean;
import com.bw.ds.util.UtilDate;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: 12547
 * @Date: 2019/3/20 11:39:33
 * @Description:
 */
public class Model implements BaseModel {

      //接口回调
      public  interface ClickDate{
           void getdate(LoginBean date);
      }
      private ClickDate clickDate;
      public void setClickDate(ClickDate clickDate) {
          this.clickDate = clickDate;
      }
    //接口回调
    public  interface RegDate{
        void getRegdate(String date);
    }
    private RegDate regDate;

    public void setRegDate(RegDate regDate) {
        this.regDate = regDate;
    }

    //登录的方法
    public void date(Map<String, String> map) {
        ApiService getserviser = UtilDate.getUtilDate().getserviser(Api.Url, ApiService.class);
               Call<LoginBean> getlogin = getserviser.getlogin(map);
                    getlogin.enqueue(new Callback<LoginBean>() {
                        @Override
                        public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                            LoginBean body = response.body();
                             clickDate.getdate(body);
                        }
                        @Override
                        public void onFailure(Call<LoginBean> call, Throwable t) {

                        }
                    });
    }

    @Override   //注册
    public void regdate(Map<String, String> map) {
        ApiService getserviser = UtilDate.getUtilDate().getserviser(Api.Url, ApiService.class);
        Call<RegisterBean> call = getserviser.getReg(map);
        call.enqueue(new Callback<RegisterBean>() {
            @Override
            public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                String status = response.body().getStatus();
                regDate.getRegdate(status);

            }
            @Override
            public void onFailure(Call<RegisterBean> call, Throwable t) {

            }
        });
    }


}
