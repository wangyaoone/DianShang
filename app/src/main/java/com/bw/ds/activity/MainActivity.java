package com.bw.ds.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bw.ds.R;
import com.bw.ds.base.BaseActivity;
import com.bw.ds.fragment.CircleFragment;
import com.bw.ds.fragment.HomePageFragment;
import com.bw.ds.fragment.IndentFragment;
import com.bw.ds.fragment.MyPageFragment;
import com.bw.ds.fragment.ShoppingCartFragment;

public class MainActivity extends BaseActivity {


    private RadioGroup kgroup;
    private FragmentManager supportFragmentManager;



    @Override   //布局
    protected int layoutResID() {
        return R.layout.activity_main;
    }
    @Override
    protected Object initPresenter() {
        return null;
    }

    @Override    //控件
    protected void initView() {
        //RadioGroup控件
        kgroup = findViewById(R.id.group);
    }

    @Override    //数据
    protected void initDate() {
        //获取管理者
        supportFragmentManager = getSupportFragmentManager();
        //开启事物
        final FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        //添加布局
        final HomePageFragment homePageFragment = new HomePageFragment();
        final CircleFragment circleFragment = new CircleFragment();
        final ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
        final IndentFragment indentFragment = new IndentFragment();
        final MyPageFragment myPageFragment = new MyPageFragment();
         beginTransaction.add(R.id.fr,homePageFragment);
         beginTransaction.add(R.id.fr,circleFragment);
         beginTransaction.add(R.id.fr,shoppingCartFragment);
         beginTransaction.add(R.id.fr,indentFragment);
         beginTransaction.add(R.id.fr,myPageFragment);
            //默认第一页
        beginTransaction.show(homePageFragment).hide(circleFragment).hide(shoppingCartFragment).hide(indentFragment).hide(myPageFragment);
            beginTransaction.commit();

        kgroup.check(kgroup.getChildAt(0).getId());
        // 下面获取  Radiogroup  的改变事件    在从新开启事物 提交
        kgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction begin = supportFragmentManager.beginTransaction();
                switch (checkedId){
                    case R.id.b1:
                        begin.show(homePageFragment).hide(circleFragment).hide(shoppingCartFragment).hide(indentFragment).hide(myPageFragment);
                        break;
                    case R.id.b2:
                        begin.show(circleFragment).hide(homePageFragment).hide(shoppingCartFragment).hide(indentFragment).hide(myPageFragment);
                        break;
                    case R.id.b3:
                        begin.show(shoppingCartFragment).hide(homePageFragment).hide(circleFragment).hide(indentFragment).hide(myPageFragment);
                        break;
                    case R.id.b4:
                        begin.show(indentFragment).hide(homePageFragment).hide(circleFragment).hide(shoppingCartFragment).hide(myPageFragment);
                        break;
                    case R.id.b5:
                        begin.show(myPageFragment).hide(homePageFragment).hide(circleFragment).hide(shoppingCartFragment).hide(indentFragment);
                        break;
                }
                  begin.commit();
            }
        });
    }
}
