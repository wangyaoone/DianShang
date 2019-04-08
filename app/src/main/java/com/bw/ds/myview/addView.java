package com.bw.ds.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;

/**
 * @Auther: 12547
 * @Date: 2019/3/22 19:14:41
 * @Description:
 */
public class addView extends LinearLayout {

    private TextView kjia;
    private EditText ked;
    private TextView kjian;
    private int i;

    public addView(Context context) {
        super(context);
    }

    public addView(Context context, AttributeSet attrs) {
        super(context, attrs);
            initDate(context,attrs);
    }


    public addView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setnumbre(int numbre){
        ked.setText(numbre+"");
    }

    private void initDate(final Context context, AttributeSet attrs) {
       LayoutInflater.from(context).inflate(R.layout.add_item,this);
        kjian = findViewById(R.id.jian);
        ked = findViewById(R.id.eddate);
        kjia = findViewById(R.id.jia);
                //为输入框设置默认值
               ked.setText(1+"");
        String s = ked.getText().toString();
        i = Integer.parseInt(s);
        //加减的点击事件
        kjian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                   if (i >=1){
                       i--;
                       ked.setText(i +"");
                       dateClick.getdate(i);
                   }
            }
        });

        kjia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                ked.setText(i +"");
                dateClick.getdate(i);
            }
        });
    }

    //接口回调
      public  interface  DateClick{
             void getdate(int num);
      }
      private DateClick dateClick;

    public void setDateClick(DateClick dateClick) {
        this.dateClick = dateClick;
    }
}
