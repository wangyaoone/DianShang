package com.bw.ds.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.bean.AdressBean;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/27 17:24:11
 * @Description:
 */
public class AdressAdapter extends RecyclerView.Adapter<AdressAdapter.myviewholder> {

   private Context context;
   private List<AdressBean.ResultBean> list;

    public AdressAdapter(Context context, List<AdressBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View view=LayoutInflater.from(context).inflate(R.layout.adress_item,null,false);
          myviewholder myviewholder = new myviewholder(view);
           return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, int i) {
        myviewholder.name.setText(list.get(i).getRealName());
        myviewholder.phone.setText(list.get(i).getPhone());
        myviewholder.adrss.setText(list.get(i).getAddress());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView phone;
        private final TextView adrss;
        private final CheckBox check;
        private final Button update;
        private final Button ok;

        public myviewholder(@NonNull View itemView) {
               super(itemView);
            name = itemView.findViewById(R.id.adressname);
            phone = itemView.findViewById(R.id.adressphone);
            adrss = itemView.findViewById(R.id.adressadress);
            check = itemView.findViewById(R.id.adresscheck);
            update = itemView.findViewById(R.id.adressupdate);
            ok = itemView.findViewById(R.id.adressok);
           }
       }
}
