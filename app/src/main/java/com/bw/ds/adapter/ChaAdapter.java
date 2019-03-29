package com.bw.ds.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.bean.ChaBean;
import com.bw.ds.myview.addView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/29 18:45:50
 * @Description:
 */
public class ChaAdapter extends RecyclerView.Adapter<ChaAdapter.myviewholder> {

   private Context context;
   private List<ChaBean.OrderListBean>list;

    public ChaAdapter(Context context, List<ChaBean.OrderListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view=LayoutInflater.from(context).inflate(R.layout.cha_item,null,false);
            myviewholder myviewholder = new myviewholder(view);
               return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, int i) {
        myviewholder.dingdanhao.setText(list.get(i).getOrderId());
            //时间无
        List<ChaBean.OrderListBean.DetailListBean> detailList = list.get(i).getDetailList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        myviewholder.ccrecy.setLayoutManager(linearLayoutManager);

        ChildChaAdapter childChaAdapter = new ChildChaAdapter(context,detailList);
        myviewholder.ccrecy.setAdapter(childChaAdapter);
                 int shuliang=0;
                 int zongjia=0;
            for (int a=0;a<detailList.size();a++){
                ChaBean.OrderListBean.DetailListBean detailListBean = detailList.get(a);
                int commodityCount = detailListBean.getCommodityCount();
                 shuliang+=commodityCount;

                int commodityPrice = detailListBean.getCommodityPrice();
                zongjia+=commodityPrice*shuliang;
            }
        myviewholder.jian.setText("共"+shuliang+"件商品,需付款");
        myviewholder.yuan.setText(zongjia+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        private final TextView dingdanhao;
        private final RecyclerView ccrecy;
        private final TextView time;

        private final TextView jian;
        private final TextView yuan;
        private final Button quxiao;
        private final Button zhifu;


        public myviewholder(@NonNull View itemView) {
               super(itemView);
            dingdanhao = itemView.findViewById(R.id.chahao);
            time = itemView.findViewById(R.id.chatime);
            ccrecy = itemView.findViewById(R.id.cccrecy);


            jian = itemView.findViewById(R.id.chajian);
            yuan = itemView.findViewById(R.id.chayuan);
            quxiao = itemView.findViewById(R.id.chaquxiao);
            zhifu = itemView.findViewById(R.id.chazhifu);
           }
       }
}
