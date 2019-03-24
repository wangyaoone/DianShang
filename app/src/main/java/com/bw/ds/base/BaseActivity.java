package com.bw.ds.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @Auther: 12547
 * @Date: 2019/3/17 14:18:33
 * @Description:
 */
public abstract class BaseActivity<T> extends AppCompatActivity {

    public T date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                //布局
                setContentView(layoutResID());
                //P层初始化
                date = initPresenter();
                //控件
                 initView();
                //数据
                 initDate();
    }

    protected abstract T initPresenter();
    protected abstract int layoutResID();
    protected abstract void initView();
    protected abstract void initDate();
}
