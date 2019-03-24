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
 * @Date: 2019/3/21 11:26:37
 * @Description:
 */
public class RxxpAdapter extends RecyclerView.Adapter<RxxpAdapter.viewholder> {

    private Context context;
    private List<PageBean.ResultBean.RxxpBean.CommodityListBean> list;

    public RxxpAdapter(Context context, List<PageBean.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.rxxp_item,null,false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, final int i) {
        Uri uri = Uri.parse(list.get(i).getMasterPic());
        viewholder.img.setImageURI(uri);
        viewholder.name.setText(list.get(i).getCommodityName());
        viewholder.money.setText("¥"+list.get(i).getPrice());
        //点击事件回调
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rDate.getdate(list.get(i).getCommodityId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  viewholder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView money;

        public viewholder(@NonNull View itemView) {
               super(itemView);
            img = itemView.findViewById(R.id.rxxpimg);
            name = itemView.findViewById(R.id.rxxpname);
            money = itemView.findViewById(R.id.rxxpmorey);
           }
       }

       public interface RDate{
            void  getdate(int i);
       }
       private RDate rDate;

       public void setrDate(RDate rDate) {
        this.rDate = rDate;
       }
}
