package com.bw.ds.api;

import com.bw.ds.bean.AddShoppBean;
import com.bw.ds.bean.BannaBean;
import com.bw.ds.bean.DetailsBean;
import com.bw.ds.bean.LoginBean;
import com.bw.ds.bean.PageBean;
import com.bw.ds.bean.RegisterBean;
import com.bw.ds.bean.SearchBean;
import com.bw.ds.bean.ShoppBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * @Auther: 12547
 * @Date: 2019/3/20 10:28:17
 * @Description:
 */
public interface ApiService {
      //登录
      @POST("small/user/v1/login")
      @FormUrlEncoded
      Call<LoginBean>getlogin(@FieldMap Map<String,String>map);
      //登录
      @POST("small/user/v1/register")
      @FormUrlEncoded
      Call<RegisterBean>getReg(@FieldMap Map<String,String>map);
       //轮播
      @GET("small/commodity/v1/bannerShow")
      Call<BannaBean>getban();
      //首页展示
      @GET("small/commodity/v1/commodityList")
      Call<PageBean>getpage();
      //详情数据
      @GET("small/commodity/v1/findCommodityDetailsById")
      Call<DetailsBean>getdetails(@Query("commodityId")int commodityId);
      //查询购物车
      @GET("small/order/verify/v1/findShoppingCart")
      Call<ShoppBean>getshopp();
      //同步购物车 加入购物车      不好使  换okgo
     /* @PUT("small/order/verify/v1/syncShoppingCart")
      Call<ResponseBody> getaddshopp(@Header("userId")String userId, @Header("sessionId")String sessionId, @Query("data")String date);*/
     //根据关键词查询商品信息
      @GET("small/commodity/v1/findCommodityByKeyword")
      Call<SearchBean>getsearch(@Query("keyword")String keyword, @Query("page")int page, @Query("count")int count);
}
