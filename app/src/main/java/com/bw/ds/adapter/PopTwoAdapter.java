package com.bw.ds.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.bean.PopTwoBean;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/30 16:37:52
 * @Description:
 */
public class PopTwoAdapter extends RecyclerView.Adapter<PopTwoAdapter.myviewholder> {

    private Context context;
    private List<PopTwoBean.ResultBean>list;

    public PopTwoAdapter(Context context, List<PopTwoBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.pop_item2child,null,false);
        myviewholder myviewholder = new myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, final int i) {
        myviewholder.tex.setText(list.get(i).getName());

         myviewholder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 clickDate.getdate(list.get(i).getId());
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        private final TextView tex;

        public myviewholder(@NonNull View itemView) {
               super(itemView);
               tex = itemView.findViewById(R.id.ername);
           }
       }


       public interface ClickDate{
             void getdate(String id);
       }
       private ClickDate clickDate;

       public void setClickDate(ClickDate clickDate) {
        this.clickDate = clickDate;
       }
}
