package com.bw.ds.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.base.BaseActivity;
import com.bw.ds.presenter.PayPresenter;
import com.bw.ds.view.PayView;
import com.suke.widget.SwitchButton;

public class PayMentActivity extends BaseActivity<PayPresenter> implements PayView {


    private String orderId;
    private String price;
    private Button qian;
    private SwitchButton money;
    private SwitchButton weixin;
    private SwitchButton zhifubao;
    private SharedPreferences sp;

    @Override
    protected int layoutResID() {
        return R.layout.activity_pay_ment;
    }

    @Override
    protected PayPresenter initPresenter() {
        date=new PayPresenter(this);
        date.attachview(this);
        return date;
    }

    @Override
    protected void initView() {
         //控件
        qian = findViewById(R.id.payqian);

        //三个选择器的按钮
        money = findViewById(R.id.paymoney);
        weixin = findViewById(R.id.payweixin);
        zhifubao = findViewById(R.id.payzhihubao);

       TextView fin=  findViewById(R.id.finsh);
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     finish();
            }
        });
    }

    @Override
    protected void initDate() {
        sp = getSharedPreferences("嗯", MODE_PRIVATE);
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        price = intent.getStringExtra("price");
        qian.setText("余额支付"+price+"元");
          //微信支付
        weixin.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                       if (isChecked){
                          Toast.makeText(PayMentActivity.this, "暂不支持此支付", Toast.LENGTH_SHORT).show();
                       }
            }
        });
       //支付宝支付
        zhifubao.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                    if (isChecked){
                        Toast.makeText(PayMentActivity.this, "暂不支持此支付", Toast.LENGTH_SHORT).show();

                    }
            }
        });


        //点击结算
        qian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                      if (money.isChecked()){
                   //实例化P层   进行网络请求
                          String uid = sp.getString("uid", "");
                          String sid = sp.getString("sid", "");
                          date.pay(uid,sid,orderId);
                      }else
                      {
              Toast.makeText(PayMentActivity.this, "请选择支付方式", Toast.LENGTH_SHORT).show();
                      }
            }
        });
    }

    //得到支付的结果
    @Override
    public void view(String message) {
            if (message.equals("支付成功")){
                 findViewById(R.id.yes).setVisibility(View.VISIBLE);
                findViewById(R.id.no).setVisibility(View.GONE);
                   return;
            }
            if (message.equals("支付失败")){
                findViewById(R.id.no).setVisibility(View.VISIBLE);
                findViewById(R.id.yes).setVisibility(View.GONE);
            }
    }


    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        date.detachview();
    }
}
