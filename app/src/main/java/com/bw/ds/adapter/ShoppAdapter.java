package com.bw.ds.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.bean.ShoppBean;
import com.bw.ds.myview.addView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 12547
 * @Date: 2019/3/22 16:02:36
 * @Description:
 */
public class ShoppAdapter extends RecyclerView.Adapter<ShoppAdapter.viewholder> {
     private Context context;
     private List<ShoppBean.ResultBean>list;


    public ShoppAdapter(Context context, List<ShoppBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.shoppchild_item,null,false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder viewholder, final int i) {
        viewholder.check.setChecked(list.get(i).isIschecked());
        Uri uri = Uri.parse(list.get(i).getPic());
        viewholder.img.setImageURI(uri);
        viewholder.name.setText(list.get(i).getCommodityName());
        viewholder.money.setText("¥"+list.get(i).getPrice());

        //反选
        viewholder.check.setOnCheckedChangeListener(null);
        viewholder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
             list.get(i).setIschecked(viewholder.check.isChecked());
                      bool.getdate();
            }
        });

        viewholder.addview.setnumbre(list.get(i).getAaa());
        //接收自定义控件的值
        viewholder.addview.setDateClick(new addView.DateClick() {
            @Override
            public void getdate(int num) {
              list.get(i).setAaa(num);
                bool.getdate();
                bool.del(num,i);
            }
        });

        //点击删除
        viewholder.delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bool.delete(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        private final CheckBox check;
        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView money;
        private final addView addview;
        private final Button delbutton;

        public viewholder(@NonNull View itemView) {
                super(itemView);
            check = itemView.findViewById(R.id.shoppcheck);
            img = itemView.findViewById(R.id.shoppimg);
            name = itemView.findViewById(R.id.shoppname);
            money = itemView.findViewById(R.id.shoppmomey);
            addview = itemView.findViewById(R.id.addview);
            delbutton = itemView.findViewById(R.id.btnDelete);
            }
        }



    //接口回调
       public interface Bool{
          void getdate();
          void delete(int i);
          void del(int num,int i);
       }
       private Bool bool;

       public void setBool(Bool bool) {
           this.bool = bool;
       }
}
