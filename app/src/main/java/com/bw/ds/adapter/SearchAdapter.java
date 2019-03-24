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
import com.bw.ds.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/24 15:46:23
 * @Description:
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.myviewholder> {

    private Context context;
    private List<SearchBean.ResultBean>list;

    public SearchAdapter(Context context, List<SearchBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.search_view,null,false);
        myviewholder myviewholder = new myviewholder(view);
           return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, final int i) {
        Uri uri = Uri.parse(list.get(i).getMasterPic());
        myviewholder.img.setImageURI(uri);
        myviewholder.name.setText(list.get(i).getCommodityName());
        myviewholder.money.setText("¥"+list.get(i).getPrice());
        myviewholder.num.setText("以售:"+list.get(i).getSaleNum());

        //点击回调
        myviewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateClick.getdate(list.get(i).getCommodityId());
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

      //接口回调
        public interface DateClick{
            void  getdate(int i);
        }
        private DateClick dateClick;

    public void setDateClick(DateClick dateClick) {
        this.dateClick = dateClick;
    }
}
