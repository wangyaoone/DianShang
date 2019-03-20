package com.bw.ds.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.base.BaseActivity;

public class LoginActivity extends BaseActivity {


    private EditText kname;
    private EditText kpwd;
    private CheckBox kremble;
    private TextView kregister;
    private Button klogin;

    @Override
    protected int layoutResID() {
        return R.layout.activity_login;
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


    }
}
