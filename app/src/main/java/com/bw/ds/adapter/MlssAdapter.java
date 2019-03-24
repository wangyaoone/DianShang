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
 * @Date: 2019/3/21 11:39:19
 * @Description:
 */
public class MlssAdapter extends RecyclerView.Adapter<MlssAdapter.viewholder> {
   private Context context;
   private List<PageBean.ResultBean.MlssBean.CommodityListBeanXX>list;

    public MlssAdapter(Context context, List<PageBean.ResultBean.MlssBean.CommodityListBeanXX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.mlss_item,null,false);
        viewholder viewholder = new viewholder(view);
        return viewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, final int i) {
        Uri uri = Uri.parse(list.get(i).getMasterPic());
        viewholder.img.setImageURI(uri);
        viewholder.name.setText(list.get(i).getCommodityName());
        viewholder.money.setText("¥"+list.get(i).getPrice());
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDate.getdate(list.get(i).getCommodityId());
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
            img = itemView.findViewById(R.id.mlssimg);
            name = itemView.findViewById(R.id.mlssname);
            money = itemView.findViewById(R.id.mlssmoney);
        }
    }

       public interface MDate{
            void getdate(int i);
       }
       private MDate mDate;

    public void setmDate(MDate mDate) {
        this.mDate = mDate;
    }
}
