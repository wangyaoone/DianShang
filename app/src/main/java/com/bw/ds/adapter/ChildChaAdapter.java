package com.bw.ds.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.bean.ChaBean;
import com.bw.ds.myview.addView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/29 19:44:47
 * @Description:
 */
public class ChildChaAdapter  extends RecyclerView.Adapter<ChildChaAdapter.viewholder> {

    private Context context;
    private List<ChaBean.OrderListBean.DetailListBean>list;

    public ChildChaAdapter(Context context, List<ChaBean.OrderListBean.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View view =LayoutInflater.from(context).inflate(R.layout.childcha_item,null,false);
        viewholder viewholder = new viewholder(view);
             return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {
        String commodityPic = list.get(i).getCommodityPic();
        String[] split = commodityPic.split(",");
        Uri uri = Uri.parse(split[0]);
        viewholder.img.setImageURI(uri);
        viewholder.name.setText(list.get(i).getCommodityName());
        viewholder.money.setText("¥"+list.get(i).getCommodityPrice());
        EditText ed= viewholder.view.findViewById(R.id.eddate);
        ed.setText(list.get(i).getCommodityCount()+"");
        viewholder.view.setDateClick(new addView.DateClick() {
            @Override
            public void getdate(int num) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView money;
        private final addView view;
           public viewholder(@NonNull View itemView) {
               super(itemView);
               img = itemView.findViewById(R.id.chaimg);
               name = itemView.findViewById(R.id.chaname);
               money = itemView.findViewById(R.id.chamoney);
               view = itemView.findViewById(R.id.chaview);
           }
       }

}
