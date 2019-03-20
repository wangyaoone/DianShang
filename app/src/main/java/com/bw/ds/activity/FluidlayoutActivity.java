package com.bw.ds.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.base.BaseActivity;
import com.bw.ds.myview.MyView;
import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;

import java.util.ArrayList;
import java.util.List;

public class FluidlayoutActivity extends BaseActivity {

    private MyView kmytwo;
    private AutoFlowLayout kflowLayout;
    private Button kclear_history;
    private List<String> list;

    @Override
    protected int layoutResID() {
        return R.layout.activity_fluidlayout;
    }

    @Override
    protected void initView() {
        kmytwo = findViewById(R.id.myviewtwo);
        //流逝布局控件
        kflowLayout = findViewById(R.id.flowLayout);
        kclear_history = findViewById(R.id.clear_history);
    }

    @Override   //数据
    protected void initDate() {
        list = new ArrayList<>();
         //点击获取输入框的值
      kmytwo.findViewById(R.id.mysou).setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
        EditText k=  findViewById(R.id.myviewtwo).findViewById(R.id.myed);
               String s = k.getText().toString();
                     list.add(s);
                    //添加数据方法
                    addData(list);
           }
      });

      //点击清除缓存
        kclear_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            EditText k=  findViewById(R.id.myviewtwo).findViewById(R.id.myed);
               k.getText().clear();
                  list.clear();
                kflowLayout.removeAllViews();
            }
        });
    }
    //添加布局
    public void addData(final List<String> list) {
        //流式布局适配器
        kflowLayout.setAdapter(new FlowAdapter(list) {
            @Override
            public View getView(int i) {
                //引入视图
      View view = LayoutInflater.from(FluidlayoutActivity.this).inflate(R.layout.fluidlayoutactivity_item, null, false);
                //获取视图控件
            TextView ktt= view.findViewById(R.id.auto_tv);
                //修改值
                ktt.setText(list.get(i));
                //清空当前集合
                list.clear();
                //返回视图
                return view;
            }
        });
    }
}
