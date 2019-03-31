package com.bw.ds.api;

import com.bw.ds.bean.BannaBean;
import com.bw.ds.bean.ChaBean;
import com.bw.ds.bean.CircleBean;
import com.bw.ds.bean.DetailsBean;
import com.bw.ds.bean.LoginBean;
import com.bw.ds.bean.NewAdressBean;
import com.bw.ds.bean.PageBean;
import com.bw.ds.bean.PayBean;
import com.bw.ds.bean.PlaceNoPrderOkBean;
import com.bw.ds.bean.PopOneBean;
import com.bw.ds.bean.PopTwoBean;
import com.bw.ds.bean.RegisterBean;
import com.bw.ds.bean.SearchBean;
import com.bw.ds.bean.ShoppBean;
import com.bw.ds.bean.ThreeShoppBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
     //圈子类表
      @GET("small/circle/v1/findCircleList")
      Flowable<CircleBean> getcircle(@Query("page")int page, @Query("count")int count);
      //创建订单
      @POST("small/order/verify/v1/createOrder")
      @FormUrlEncoded
      Call<PlaceNoPrderOkBean>getplacenoorder(@Field("totalPrice")double totalPrice, @Field("addressId")int addressId, @Field("orderInfo")String orderInfo);
      //支付
      @POST("small/order/verify/v1/pay")
      @FormUrlEncoded
      Call<PayBean>getpay(@Field("orderId")String orderId, @Field("payType")int payType);
      //根据订单状态查询订单信息
      @GET("small/order/verify/v1/findOrderListByStatus")
      Call<ChaBean>getstate(@Query("status")int status, @Query("page")int page, @Query("count")int count);
      //添加收获地址
      @POST("small/user/verify/v1/addReceiveAddress")
      @FormUrlEncoded
      Call<NewAdressBean>getnewadress(@Field("realName")String realName, @Field("phone")String phone, @Field("address")String address, @Field("zipCode")String zipCode);
      //三级分类一级
      @GET("small/commodity/v1/findFirstCategory")
      Call<PopOneBean>getpop1();
      //三级分类二级
      @GET("small/commodity/v1/findSecondCategory")
      Call<PopTwoBean>getpop2(@Query("firstCategoryId")String firstCategoryId);
       //三级分类第三级
       @GET("small/commodity/v1/findCommodityByCategory")
       Call<ThreeShoppBean>getthreeshopp(@Query("categoryId")String categoryId, @Query("page")int page, @Query("count")int count);
}
