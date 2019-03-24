package com.bw.ds.presenter;

import android.util.Log;

import com.bw.ds.base.BasePresenter;
import com.bw.ds.bean.LoginBean;
import com.bw.ds.model.Model;
import com.bw.ds.view.View;

import java.util.Map;

/**
 * @Auther: 12547
 * @Date: 2019/3/20 11:16:12
 * @Description:
 */
public class Presenter extends BasePresenter<View> {

    private final Model model;
    private final View view;


    //构造参数
    public Presenter(View viewdate) {
        model = new Model();
        view = viewdate;

    }

   @Override   //登录
    public void date(Map<String, String> map) {
        model.date(map);
           //接口回调得到M层的值   登录的值
           model.setClickDate(new Model.ClickDate() {
               @Override
               public void getdate(LoginBean date) {
                       view.login(date);
               }
           });
    }
    @Override    //注册
    public void regdate(Map<String, String> map) {
        model.regdate(map);
        model.setRegDate(new Model.RegDate() {
            @Override
            public void getRegdate(String date) {
                view.reg(date);
            }
        });
    }
}
