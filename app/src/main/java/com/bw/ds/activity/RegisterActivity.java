package com.bw.ds.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.base.BaseActivity;
import com.bw.ds.bean.LoginBean;
import com.bw.ds.presenter.Presenter;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends BaseActivity<Presenter> implements com.bw.ds.view.View {
    //map集合 用来存放手机号及密码
    private Map<String ,String> map=new HashMap<>();
    private EditText kregistername;
    private EditText kyzm;
    private EditText kregisterpwd;
    private TextView ktiaologin;
    private Button kregister;

    @Override
    protected int layoutResID() {
        return R.layout.activity_register;
    }
    @Override
    protected Presenter initPresenter() {
        date=new Presenter(this);
        date.attachview(this);
        return date;
    }

    @Override   //控件
    protected void initView() {
        kregistername = findViewById(R.id.registername);
        kyzm = findViewById(R.id.yzm);
        kregisterpwd = findViewById(R.id.registerpwd);
        ktiaologin = findViewById(R.id.tiaologin);
        kregister = findViewById(R.id.register);
    }

    @Override
    protected void initDate() {
        //点击进入登录页面
        ktiaologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
            //注册的点击事件
        kregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = kregistername.getText().toString();
                String pwd = kregisterpwd.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
                    Toast.makeText(RegisterActivity.this, "都输入了?!就登", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.length()<3){
                    Toast.makeText(RegisterActivity.this, "密码长度够吗?", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断的差不多了  传值进入P层
                map.put("phone", name);
                map.put("pwd", pwd);
                date.regdate(map);
            }
        });
    }

    @Override
    public void login(LoginBean date) {}
    @Override
    public void reg(String date) {
           if (date.equals("0000")){
              Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                      finish();
           }else
           {
              Toast.makeText(this, "次号码已存在", Toast.LENGTH_SHORT).show();
           }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        date.detachview();
    }
}
