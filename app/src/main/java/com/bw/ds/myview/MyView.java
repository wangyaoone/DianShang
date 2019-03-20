package com.bw.ds.myview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.ds.R;

/**
 * @Auther: 12547
 * @Date: 2019/3/17 18:50:20
 * @Description:
 */
public class MyView extends LinearLayout {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context,  AttributeSet attrs) {
        super(context, attrs);
              initDate(context,attrs);
    }


    public MyView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initDate(final Context context, AttributeSet attrs) {
   View view=LayoutInflater.from(context).inflate(R.layout.my_view,null,false);
               addView(view);
           //找到控件
         /*ImageView kfen= view.findViewById(R.id.myfen);
         ImageView kmysou= view.findViewById(R.id.mysou);
           final EditText ked= view.findViewById(R.id.myed);
             //搜索的点击事件
        kmysou.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
     Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show();
                }
        });
     // 输入框的点击事件
        ked.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                myClick.getdate();
            }
        });*/

    }
    /*//接口回调
     public  interface MyClick{
         void  getdate();
     }
     private MyClick myClick;

    public void setMyClick(MyClick myClick) {
        this.myClick = myClick;
    }*/

}
