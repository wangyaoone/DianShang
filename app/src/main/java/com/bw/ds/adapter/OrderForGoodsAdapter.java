package com.bw.ds.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.bean.OrderForGoodsBean;
import com.bw.ds.myview.addView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/27 20:17:00
 * @Description:
 */
public class OrderForGoodsAdapter extends RecyclerView.Adapter<OrderForGoodsAdapter.myviewholder> {
    int nnn=1;
    private Context   context;
    private List<OrderForGoodsBean> list;

    public OrderForGoodsAdapter(Context context, List<OrderForGoodsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.firmordercheld_item,null,false);
        myviewholder myviewholder = new myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, final int i) {
        Uri uri = Uri.parse(list.get(i).getPic());
        myviewholder.img.setImageURI(uri);
        myviewholder.name.setText(list.get(i).getCommodityName());
        myviewholder.money.setText("¥"+list.get(i).getPrice());
        money.getdate2(nnn);
        myviewholder.addview.setDateClick(new addView.DateClick() {
            @Override
            public void getdate(int num) {
                int sum=list.get(i).getPrice()*num;
                money.getdate(sum);
                money.getdate2(num);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        private final com.facebook.drawee.view.SimpleDraweeView img;
        private final TextView name;
        private final TextView money;
        private final addView addview;
           public myviewholder(@NonNull View itemView) {
               super(itemView);

               img = itemView.findViewById(R.id.firmimg);
               name = itemView.findViewById(R.id.firmname);
               money = itemView.findViewById(R.id.firmmoney);
               addview = itemView.findViewById(R.id.firmview);

           }
       }

          public interface Money{
               void getdate(int sum);
               void getdate2(int a);
              void getdate3(int b);
          }
          private Money money;

          public void setMoney(Money money) {
             this.money = money;
          }
}
