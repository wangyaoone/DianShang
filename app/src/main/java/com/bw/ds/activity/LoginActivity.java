package com.bw.ds.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.base.BaseActivity;
import com.bw.ds.bean.LoginBean;
import com.bw.ds.presenter.Presenter;
import com.bw.ds.util.UtilPhone;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity<Presenter> implements com.bw.ds.view.View {

    //map集合 用来存放手机号及密码
    private Map<String ,String>map=new HashMap<>();
    private EditText kname;
    private EditText kpwd;
    private CheckBox kremble;
    private TextView kregister;
    private Button klogin;
    private SharedPreferences sp;
    private SharedPreferences spp;

    private String pwd;
    private String name;

    @Override
    protected int layoutResID() {
        return R.layout.activity_login;
    }
    @Override    //P层
    protected Presenter initPresenter() {
        date=new Presenter(this);
        date.attachview(this);
        return date;
    }

    @Override
    protected void initView() {
        kname = findViewById(R.id.loginname);
        kpwd = findViewById(R.id.loginpwd);
        kremble = findViewById(R.id.rember);
        kregister = findViewById(R.id.register);
        klogin = findViewById(R.id.login);
    }

    @Override
    protected void initDate() {

           //点击进入注册页面
        kregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                     startActivity(intent);
            }
        });
        //点击登录
        klogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name = kname.getText().toString();
                pwd = kpwd.getText().toString();
                //正则手机验证
                boolean telPhoneNumber = UtilPhone.isTelPhoneNumber(name);
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
                    Toast.makeText(LoginActivity.this, "都输入了?!就登", Toast.LENGTH_SHORT).show();
                          return;
                }
                if (pwd.length()<3){
                    Toast.makeText(LoginActivity.this, "密码长度够吗?", Toast.LENGTH_SHORT).show();
                          return;
                }
                if (!telPhoneNumber){
                    Toast.makeText(LoginActivity.this, "你觉得格式对吗?", Toast.LENGTH_SHORT).show();
                          return;
                }
                   //判断的差不多了  传值进入P层
                    map.put("phone", name);
                    map.put("pwd", pwd);
                    date.date(map);
            }
        });
               //使用SP
        sp = getSharedPreferences("记住", MODE_PRIVATE);
               if (sp.getBoolean("密码",false)){
                   String uname = sp.getString("name", "");
                   String upwd = sp.getString("pwd", "");
                   kname.setText(uname);
                   kpwd.setText(upwd);
                   kremble.setChecked(true);
               }
        spp = getSharedPreferences("嗯", MODE_PRIVATE);
    }
    @Override    //得到状态码
    public void login(LoginBean date) {
        Log.i("aa",date.getResult().getNickName());;
        //使用wven回值
        EventBus.getDefault().post(date.getResult().getHeadPic()+","+date.getResult().getNickName());
              if (date.getStatus().equals("0000")){

                  //将uid及sid存进去用于将来取值
                  SharedPreferences.Editor edit1 = spp.edit();
                  edit1.putString("uid",date.getResult().getUserId()+"");
                  edit1.putString("sid",date.getResult().getSessionId()+"");
                  edit1.commit();

                  //如果成功将密码和账号存进SP中
                  SharedPreferences.Editor edit = sp.edit();
                  edit.putBoolean("密码",kremble.isChecked());
                  edit.putString("name",name);
                  edit.putString("pwd",pwd);
                  edit.commit();

       Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
              }else
              {
                  Toast.makeText(this, "用户名不存在请先注册", Toast.LENGTH_SHORT).show();
              }
    }

    @Override
    public void reg(String date) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        date.detachview();
    }
}
