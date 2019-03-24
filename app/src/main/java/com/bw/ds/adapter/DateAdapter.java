package com.bw.ds.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.ds.R;
import com.bw.ds.bean.PageBean;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/21 11:22:08
 * @Description:
 */
public class DateAdapter extends RecyclerView.Adapter<DateAdapter.myviewholder> {

    private Context context;
    private PageBean.ResultBean result;

    public DateAdapter(Context context, PageBean.ResultBean result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view=LayoutInflater.from(context).inflate(R.layout.pagechild_item,null,false);
        myviewholder myviewholder = new myviewholder(view);
              return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, int i) {
        //热销新品的值
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        myviewholder.kr1.setLayoutManager(staggeredGridLayoutManager);
        List<PageBean.ResultBean.RxxpBean.CommodityListBean> commodityList = result.getRxxp().getCommodityList();
        RxxpAdapter rxxpAdapter = new RxxpAdapter(context,commodityList);
        myviewholder.kr1.setAdapter(rxxpAdapter);
        //魔力时尚
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        myviewholder.kr2.setLayoutManager(linearLayoutManager);
        List<PageBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList1 = result.getMlss().getCommodityList();
        MlssAdapter mlssAdapter = new MlssAdapter(context,commodityList1);
        myviewholder.kr2.setAdapter(mlssAdapter);
        //品质生活
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        myviewholder.kr3.setLayoutManager(gridLayoutManager);
        List<PageBean.ResultBean.PzshBean.CommodityListBeanX> commodityList2 = result.getPzsh().getCommodityList();
        PzshAdapter pzshAdapter = new PzshAdapter(context,commodityList2);
        myviewholder.kr3.setAdapter(pzshAdapter);

        //接收到的值
        rxxpAdapter.setrDate(new RxxpAdapter.RDate() {
            @Override
            public void getdate(int i) {
                if (date!=null){
                    date.getdate(i);
                }
            }
        });
        mlssAdapter.setmDate(new MlssAdapter.MDate() {
            @Override
            public void getdate(int i) {
                if (date!=null){
                    date.getdate(i);
                }
            }
        });
        pzshAdapter.setpDate(new PzshAdapter.PDate() {
            @Override
            public void getdate(int i) {
                if (date!=null){
                    date.getdate(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class myviewholder extends RecyclerView.ViewHolder{

        private final RecyclerView kr1;
        private final RecyclerView kr2;
        private final RecyclerView kr3;

        public myviewholder(@NonNull View itemView) {
               super(itemView);
            kr1 = itemView.findViewById(R.id.recyone);
            kr2 = itemView.findViewById(R.id.recytwo);
            kr3 = itemView.findViewById(R.id.recythree);
           }
       }

       public interface Date{
            void getdate(int i);
       }
       private Date date;

      public void setDate(Date date) {
        this.date = date;
      }
}
