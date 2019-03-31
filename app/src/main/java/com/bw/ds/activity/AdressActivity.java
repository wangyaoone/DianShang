package com.bw.ds.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.adapter.AdressAdapter;
import com.bw.ds.bean.AdressBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AdressActivity extends AppCompatActivity {
    private myHandLer myHandLer=new  myHandLer();
    private TextView ovre;
    private RecyclerView recy;
    private Button newadress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);

        ovre = findViewById(R.id.adressover);
        recy = findViewById(R.id.adressrecy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdressActivity.this);
        recy.setLayoutManager(linearLayoutManager);
        newadress = findViewById(R.id.newadress);

        Intent intent = getIntent();
        final String uid = intent.getStringExtra("uid");
        final String sid = intent.getStringExtra("sid");
       String url="http://mobile.bwstudent.com/small/user/verify/v1/receiveAddressList";
        //当前页面网络请求  解析
        OkHttpClient builder = new OkHttpClient.Builder()
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
        Request build = new Request.Builder().url(url).build();
        Call call = builder.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                    //Log.i("bbb",string);
                Message message = new Message();
                      message.obj=string;
                myHandLer.sendMessage(message);
            }
        });

        //点击关闭当前页面
        ovre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   finish();
            }
        });

        //点击进入新增收获地址页面
        newadress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AdressActivity.this, NewAdressActivity.class);
                     startActivity(intent1);
            }
        });
    }

      class myHandLer extends Handler{
          @Override
          public void handleMessage(Message msg) {
              super.handleMessage(msg);
              String obj = (String) msg.obj;
              Gson gson = new Gson();
              AdressBean adressBean = gson.fromJson(obj, AdressBean.class);
              List<AdressBean.ResultBean> result = adressBean.getResult();
                        //适配器
       AdressAdapter adressAdapter = new AdressAdapter(AdressActivity.this,result);
              recy.setAdapter(adressAdapter);
          }
      }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        final String uid = intent.getStringExtra("uid");
        final String sid = intent.getStringExtra("sid");
        String url="http://mobile.bwstudent.com/small/user/verify/v1/receiveAddressList";
        //当前页面网络请求  解析
        OkHttpClient builder = new OkHttpClient.Builder()
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
        Request build = new Request.Builder().url(url).build();
        Call call = builder.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                //Log.i("bbb",string);
                Message message = new Message();
                message.obj=string;
                myHandLer.sendMessage(message);
            }
        });
    }
}
