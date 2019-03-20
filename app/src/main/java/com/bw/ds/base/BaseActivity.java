package com.bw.ds.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @Auther: 12547
 * @Date: 2019/3/17 14:18:33
 * @Description:
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                //布局
                setContentView(layoutResID());
                //控件
                 initView();
                //数据
                 initDate();
    }


    protected abstract int layoutResID();
    protected abstract void initView();
    protected abstract void initDate();
}
