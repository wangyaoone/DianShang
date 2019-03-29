package com.bw.ds.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.adapter.MoneyAdapter;
import com.bw.ds.bean.MoneyBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyPageMoneyActivity extends AppCompatActivity {
    private myHandler myHandler=new myHandler();
    private TextView money;
    private RecyclerView recy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_money);

        money = findViewById(R.id.childmoney);
        recy = findViewById(R.id.childrecy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyPageMoneyActivity.this);
        recy.setLayoutManager(linearLayoutManager);
        Intent intent = getIntent();
        final String uid = intent.getStringExtra("uid");
        final String sid = intent.getStringExtra("sid");

         // Log.i("aaa",sid+"--"+uid);
 String url="http://mobile.bwstudent.com/small/user/verify/v1/findUserWallet?page=1&count=100";
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
                      //Log.i("aaa",string);
                Message message = new Message();
                message.obj=string;
                myHandler.sendMessage(message);
            }
        });
    }
       class myHandler extends Handler{
           @Override
           public void handleMessage(Message msg) {
               super.handleMessage(msg);
                  String  obj = (String) msg.obj;
               Gson gson = new Gson();
               MoneyBean moneyBean = gson.fromJson(obj, MoneyBean.class);
               MoneyBean.ResultBean result = moneyBean.getResult();
                    //适配器

               int balance = result.getBalance();
               money.setText("¥:"+balance);
               MoneyAdapter moneyAdapter = new MoneyAdapter(MyPageMoneyActivity.this,result);
               recy.setAdapter(moneyAdapter);
           }
       }
}
