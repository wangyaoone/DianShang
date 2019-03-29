package com.bw.ds.model;

import android.util.Log;

import com.bw.ds.api.Api;
import com.bw.ds.api.ApiService;
import com.bw.ds.bean.AddShoppBean;
import com.bw.ds.bean.DetailsBean;
import com.bw.ds.bean.ShoppBean;
import com.bw.ds.presenter.DetailsPresenter;
import com.bw.ds.util.UtilDate;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @Auther: 12547
 * @Date: 2019/3/21 15:44:29
 * @Description:
 */
public class DetailsModel {


    //接口回调
      public interface Hdate{
           void getdate(DetailsBean.ResultBean result);
           void chashopp(List<ShoppBean.ResultBean> result);
           void addshopp(String s);
      }
      private Hdate hdate;

    public void setHdate(Hdate hdate) {
        this.hdate = hdate;
    }

    public void details(int id) {
        ApiService getserviser = UtilDate.getUtilDate().getserviser(Api.Url, ApiService.class);
        Call<DetailsBean> call = getserviser.getdetails(id);
               call.enqueue(new Callback<DetailsBean>() {
                   @Override
                   public void onResponse(Call<DetailsBean> call, Response<DetailsBean> response) {
                       DetailsBean.ResultBean result = response.body().getResult();
                      /* Log.i("aa",result+"");*/
                       hdate.getdate(result);
                   }
                   @Override
                   public void onFailure(Call<DetailsBean> call, Throwable t) {

                   }
               });

    }

    //先查询购物车的操作
    public void chaShopp(String uid, String sid) {
        //找网络工具类
        ApiService apiService = UtilDate.getUtilDate().getserviserHand(Api.Url, uid, sid, ApiService.class);
        Call<ShoppBean> call = apiService.getshopp();
        call.enqueue(new Callback<ShoppBean>() {
            @Override
            public void onResponse(Call<ShoppBean> call, Response<ShoppBean> response) {
                List<ShoppBean.ResultBean> result = response.body().getResult();
                    /*  Log.i("xxx",result+"");*/
                hdate.chashopp(result);
            }

            @Override
            public void onFailure(Call<ShoppBean> call, Throwable t) {

            }
        });
    }
    //进行同步购物车  及添加购物车
    public void addShopp(String uid, String sid, String sss) {
          Log.i("aaa",sss);
       //找网络工具类
      /* ApiService apiService = UtilDate.getUtilDate().getserviserHand(Api.Url, uid, sid, ApiService.class);
        Call<AddShoppBean> call = apiService.getaddshopp(sss);
        call.enqueue(new Callback<AddShoppBean>() {
            @Override
            public void onResponse(Call<AddShoppBean> call, Response<AddShoppBean> response) {
                String message = response.body().getMessage();
                       Log.i("aaa",message);
            }
            @Override
            public void onFailure(Call<AddShoppBean> call, Throwable t) {

            }
        });*/

         //使用ok Go
        OkGo.<String>put(Api.SYNCSHOPPINGCART)
                .headers("userId",uid)
                .headers("sessionId",sid)
                .params("data",sss)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String s = response.body().toString();

                            //Log.i("aaa",s);
                        hdate.addshopp(s);
                    }
                });

        /*ApiService getserviser = UtilDate.getUtilDate().getserviser(Api.Url, ApiService.class);

        Log.e("gs","获取信息------"+uid.toString());
        Call<ResponseBody> call = getserviser.getaddshopp(uid, sid, sss);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String requesData = response.body().string();
                      Log.e("gs","requesData------"+requesData);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("gs","requesData-错误信息-----"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/
        /*Log.e("sssssssss", "addShopp: "+uid+sid+sss );
        Retrofit build = new Retrofit.Builder().baseUrl(Api.Url).build();
        build.create(ApiService.class).getaddshopp(uid,sid,sss).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    if (string==null){
                        Log.e("string", "onResponse: 无参" );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/
    }
}
