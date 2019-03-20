package com.bw.ds.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @Auther: 12547
 * @Date: 2019/3/19 14:12:19
 * @Description:
 */
public class Applition extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
          //初始化图片框架
        Fresco.initialize(this);
    }
}
