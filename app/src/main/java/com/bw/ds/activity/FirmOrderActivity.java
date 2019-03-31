package com.bw.ds.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.adapter.AdressAdapter;
import com.bw.ds.adapter.OrderForGoodsAdapter;
import com.bw.ds.bean.AdressBean;
import com.bw.ds.bean.FirmMoreBean;
import com.bw.ds.bean.OrderForGoodsBean;
import com.bw.ds.bean.PlaceNoPrderOkBean;
import com.bw.ds.presenter.PlaceNnOrder;
import com.bw.ds.view.PlaceNoOrderView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FirmOrderActivity extends AppCompatActivity implements PlaceNoOrderView {
    private myHandLer myHandLer=new  myHandLer();
    private SharedPreferences sp;
    private String uid;
    private String sid;
    private RecyclerView adrecy;
    private TextView zongjia;
    private int aa=1;
    private Button submit;
    private List<AdressBean.ResultBean> result;
    private PlaceNnOrder placeNnOrder;
    private List<FirmMoreBean> list1;
    private TextView shuliang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_order);
           //初始化P层
        placeNnOrder = new PlaceNnOrder(FirmOrderActivity.this);
        placeNnOrder.attachview(FirmOrderActivity.this);
        shuliang = findViewById(R.id.firmshulaing);
             //获取总价的和提交订单的ID
        zongjia = findViewById(R.id.firmzongjia);
        submit = findViewById(R.id.submit);
             //地址的适配器
        adrecy = findViewById(R.id.adrecy);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(FirmOrderActivity.this);
        adrecy.setLayoutManager(linearLayoutManager1);
            //所需购买的值
        RecyclerView recy=   findViewById(R.id.shrecy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FirmOrderActivity.this);
        recy.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        final int id = intent.getExtras().getInt("id");
        String name = intent.getStringExtra("name");
        final int price = intent.getExtras().getInt("price");
        String pic = intent.getStringExtra("pic");
        String[] split = pic.split(",");



                  zongjia.setText(price+"");


        //点击提交订单
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       //获取sid   uid
                uid = sp.getString("uid", "");
                sid = sp.getString("sid", "");

                int adressid = result.get(0).getId();
                Gson gson = new Gson();
                String  date = gson.toJson(list1);
                       //网络请求去P层
                placeNnOrder.place(uid,sid,zongjia.getText()+"",adressid,date);
            }
        });

                //商品展示
                List<OrderForGoodsBean> list=new ArrayList<>();
   OrderForGoodsBean orderForGoodsBean = new OrderForGoodsBean(id, name, 1, split[0], price, false);
                list.add(orderForGoodsBean);
                   //适配器
        OrderForGoodsAdapter orderForGoodsAdapter = new OrderForGoodsAdapter(FirmOrderActivity.this,list);
              recy.setAdapter(orderForGoodsAdapter);

              //得到总价的值
        orderForGoodsAdapter.setMoney(new OrderForGoodsAdapter.Money() {

            @Override
            public void getdate(int sum) {
                zongjia.setText(sum+"");
            }

            @Override
            public void getdate2(int a) {
                shuliang.setText(a+"");
                list1 = new ArrayList<>();
                FirmMoreBean firmMoreBean = new FirmMoreBean(id+"",a+"");
                list1.add(firmMoreBean);
            }

            @Override
            public void getdate3(int b) {
                list1 = new ArrayList<>();
                FirmMoreBean firmMoreBean = new FirmMoreBean(id+"",b+"");
                list1.add(firmMoreBean);
            }

        });



        sp = getSharedPreferences("嗯", MODE_PRIVATE);
        uid = sp.getString("uid", "");
        sid = sp.getString("sid", "");

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
                Message message = new Message();
                message.obj=string;
                myHandLer.sendMessage(message);
            }
        });

    }

    //得到订单状态
    @Override
    public void view(PlaceNoPrderOkBean body) {
        //订单状态
        String message = body.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        //得到订单号  进入支付页面
        String orderId = body.getOrderId();
         //判断
               if (message.equals("创建订单成功")){
                   Intent intent = new Intent(FirmOrderActivity.this, PayMentActivity.class);
                   intent.putExtra("orderId",orderId);
                   intent.putExtra("price",zongjia.getText());
                   startActivity(intent);
                   finish();
                   return;
               }
           Toast.makeText(this, "Bug老子改了哈哈哈", Toast.LENGTH_SHORT).show();
    }

    //地址
    class myHandLer extends Handler {
          @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String obj = (String) msg.obj;
            Gson gson = new Gson();
            AdressBean adressBean = gson.fromJson(obj, AdressBean.class);
              result = adressBean.getResult();
            //适配器
            AdressAdapter adressAdapter = new AdressAdapter(FirmOrderActivity.this, result);
            adrecy.setAdapter(adressAdapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        uid = sp.getString("uid", "");
        sid = sp.getString("sid", "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        placeNnOrder.detachview();
    }
}
