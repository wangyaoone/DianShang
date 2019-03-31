package com.bw.ds.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.bean.PopOneBean;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/30 15:54:21
 * @Description:
 */
public class PopOneAdapter extends RecyclerView.Adapter<PopOneAdapter.myviewholder> {

      private Context context;
      private List<PopOneBean.ResultBean>list;

    public PopOneAdapter(Context context, List<PopOneBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
          View view=LayoutInflater.from(context).inflate(R.layout.pop_item1child,null,false);
          myviewholder myviewholder = new myviewholder(view);
              return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final myviewholder myviewholder, final int i) {
        myviewholder.yiname.setText(list.get(i).getName());

               final String id = list.get(i).getId();

        myviewholder.yiname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date.getdate(id);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        private final TextView yiname;

        public myviewholder(@NonNull View itemView) {
               super(itemView);
            yiname = itemView.findViewById(R.id.yiname);

           }
       }

     public interface Date{
             void getdate(String id);

       }
       private Date date;

    public void setDate(Date date) {
        this.date = date;
    }
}
