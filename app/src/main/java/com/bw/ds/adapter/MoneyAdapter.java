package com.bw.ds.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.bean.MoneyBean;

import java.util.Date;

/**
 * @Auther: 12547
 * @Date: 2019/3/27 16:31:02
 * @Description:
 */
public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.myviewholder> {

     private Context context;
     private MoneyBean.ResultBean list;

    public MoneyAdapter(Context context, MoneyBean.ResultBean list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
          View view=LayoutInflater.from(context).inflate(R.layout.money_item,null,false);
        myviewholder myviewholder = new myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, int i) {
        myviewholder.money.setText("¥"+list.getDetailList().get(i).getAmount());
        long consumerTime = list.getDetailList().get(i).getConsumerTime();
        Date date = new Date(consumerTime);
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        myviewholder.time.setText(format);

    }

    @Override
    public int getItemCount() {
        return list.getDetailList().size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        private final TextView money;
        private final TextView time;

        public myviewholder(@NonNull View itemView) {
              super(itemView);
            money = itemView.findViewById(R.id.item_money);
            time = itemView.findViewById(R.id.item_time);

          }
      }
}
