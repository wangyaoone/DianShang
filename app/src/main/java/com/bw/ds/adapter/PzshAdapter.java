package com.bw.ds.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.bean.PageBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/21 12:00:06
 * @Description:
 */
public class PzshAdapter extends RecyclerView.Adapter<PzshAdapter.myviewholder> {

    private Context context;
    private List<PageBean.ResultBean.PzshBean.CommodityListBeanX>list;

    public PzshAdapter(Context context, List<PageBean.ResultBean.PzshBean.CommodityListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.pzsh_item,null,false);
        myviewholder myviewholder = new myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, final int i) {
        Uri uri = Uri.parse(list.get(i).getMasterPic());
        myviewholder.img.setImageURI(uri);
        myviewholder.name.setText(list.get(i).getCommodityName());
        myviewholder.morey.setText("¥"+list.get(i).getPrice());
        myviewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDate.getdate(list.get(i).getCommodityId());
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView morey;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.pzshimg);
            name = itemView.findViewById(R.id.pzshname);
            morey = itemView.findViewById(R.id.pzshmorey);
        }
    }

       public interface PDate{
            void getdate(int i);
       }
       private PDate pDate;

    public void setpDate(PDate pDate) {
        this.pDate = pDate;
    }
}
