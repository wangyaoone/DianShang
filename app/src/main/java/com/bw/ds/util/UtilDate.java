package com.bw.ds.util;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Auther: 12547
 * @Date: 2019/3/20 11:46:36
 * @Description:
 */
public class UtilDate {
     //设置单例模式
     //私有的静态的成员变量
     private static UtilDate utilDate=null;
     //私有的构造方法
     private UtilDate() {
     }
     //返回公共的静态的实例方法
     public static UtilDate getUtilDate(){
         if (utilDate==null){
             synchronized (UtilDate.class){
                 if (utilDate==null){
                     utilDate=new UtilDate();
                 }
             }
         }
            return utilDate;
     }

      //ok
      private static OkHttpClient okHttpClient=null;
      public static OkHttpClient getOkHttpClient(final String uid, final String sid){
            //日志拦截器
      HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
             @Override
             public void log(String message) {
                     // Log.i("拦截器",message);
             }
         });
         //创建日志拦截器模式
         httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                  //ok
           okHttpClient = new OkHttpClient.Builder()
                  //添加日志拦截器
                 .addInterceptor(httpLoggingInterceptor)

                 //添加网络拦截器
                 .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

                 //添加运用拦截器
                 .addInterceptor(new Interceptor() {
                     @Override
                     public Response intercept(Chain chain) throws IOException {
                         Request builder1 = chain.request().newBuilder()
                                 .addHeader("userId",uid)
                                 .addHeader("sessionId",sid)
                                 .build();
                         return chain.proceed(builder1);
                     }
                 })
                 .build();

         return okHttpClient;
     }


     //请求方式 不带请求头
      public static Retrofit getretrofin(String url){
          Retrofit builder = new Retrofit.Builder()
                  .baseUrl(url)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();
               return builder;
      }

      public <T>T getserviser(String url,Class<T>service){
          Retrofit getretrofin = getretrofin(url);
              T t = getretrofin.create(service);
                      return t;
      }


    //请求方式 带请求头
    public static Retrofit getretrofinHand(String url,String uid,String sid){
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient(uid,sid))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return builder;
    }

    public <T>T getserviserHand(String url,String uid,String sid,Class<T>service){
        Retrofit retrofit = getretrofinHand(url, uid, sid);
        T t = retrofit.create(service);
        return t;
    }

}
