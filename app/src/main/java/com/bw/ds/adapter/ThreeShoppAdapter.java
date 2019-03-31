package com.bw.ds.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.bean.ThreeShoppBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/31 14:41:14
 * @Description:
 */
public class ThreeShoppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int one=0;
    private static final int two=1;
       private Context context;
       private List<ThreeShoppBean.ResultBean>list;

    public ThreeShoppAdapter(Context context, List<ThreeShoppBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==one){
            View view=LayoutInflater.from(context).inflate(R.layout.search_view,null,false);
            myviewholder myviewholder = new myviewholder(view);
              return myviewholder;
        }else
        {
            View view=LayoutInflater.from(context).inflate(R.layout.onloadmore_item,null,false);
            myviewholder2 myviewholder2 = new myviewholder2(view);
            return myviewholder2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof myviewholder){
            Uri uri = Uri.parse(list.get(i).getMasterPic());
            ((myviewholder) viewHolder).img.setImageURI(uri);
            ((myviewholder) viewHolder).name.setText(list.get(i).getCommodityName());
            ((myviewholder) viewHolder).money.setText("¥"+list.get(i).getPrice());
            ((myviewholder) viewHolder).num.setText("以售:"+list.get(i).getSaleNum());

        }else if (viewHolder instanceof myviewholder2){

        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int commodityId = list.get(i).getCommodityId();
                click.getdate(commodityId);
            }
        });

    }

    //判断布局
    @Override
    public int getItemViewType(int position) {
        if (list.size()==position){
            return two;
        }
        return one;
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    class myviewholder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView money;
        private final TextView num;
            public myviewholder(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.searchimg);
                name = itemView.findViewById(R.id.searchname);
                money = itemView.findViewById(R.id.searchmoey);
                num = itemView.findViewById(R.id.searchnum);
            }
        }

        class myviewholder2 extends RecyclerView.ViewHolder{
            private final ContentLoadingProgressBar iimg;
            public myviewholder2(@NonNull View itemView) {
                super(itemView);
                iimg = itemView.findViewById(R.id.pb_progress);
            }
        }


        public interface  Click{
            void    getdate(int commodityId);
        }
        private Click click;

    public void setClick(Click click) {
        this.click = click;
    }
}
