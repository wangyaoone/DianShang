package com.bw.ds.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.bean.ShoppBean;
import com.bw.ds.myview.addView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 12547
 * @Date: 2019/3/22 16:02:36
 * @Description:
 */
public class ShoppAdapter extends RecyclerView.Adapter<ShoppAdapter.viewholder> {
     private Map<String,Boolean> map=new HashMap<>();
     private Map<Integer,Integer>mapmoney=new HashMap<>();
     private Context context;
     private List<ShoppBean.ResultBean>list;


    public ShoppAdapter(Context context, List<ShoppBean.ResultBean> list) {
        this.context = context;
        this.list = list;
        getGetcheck(false);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.shoppchild_item,null,false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder viewholder, final int i) {
        viewholder.check.setChecked(list.get(i).isIschecked());
        Uri uri = Uri.parse(list.get(i).getPic());
        viewholder.img.setImageURI(uri);
        viewholder.name.setText(list.get(i).getCommodityName());
        viewholder.money.setText("¥"+list.get(i).getPrice());
               //设置全不选及全选
        String s = list.get(i).getCommodityId() + "";
        viewholder.check.setChecked(map.get(s));
              //在点击事件中设置反选功能
        viewholder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = list.get(i).getCommodityId() + "";
                map.put(s,  viewholder.check.isChecked());
                boolean flag=true;
                 for (String key:map.keySet()){
                     Boolean aBoolean = map.get(key);
                        if (!aBoolean){
                            flag=false;
                            bool.getdate(flag);
                        }
                 }
               if (flag){
                   bool.getdate(flag);
               }
            }
        });

        //接收自定义控件的值
        viewholder.addview.setDateClick(new addView.DateClick() {
            @Override
            public void getdate(int num) {
                 //次值为输入框的值
            }
        });

        //点击删除
        viewholder.delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bool.delete(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        private final CheckBox check;
        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView money;
        private final addView addview;
        private final Button delbutton;

        public viewholder(@NonNull View itemView) {
                super(itemView);
            check = itemView.findViewById(R.id.shoppcheck);
            img = itemView.findViewById(R.id.shoppimg);
            name = itemView.findViewById(R.id.shoppname);
            money = itemView.findViewById(R.id.shoppmomey);
            addview = itemView.findViewById(R.id.addview);
            delbutton = itemView.findViewById(R.id.btnDelete);
            }
        }

    public void  getcheck(boolean a){
        getGetcheck(a);
        notifyDataSetChanged();
    }

    public void getGetcheck(boolean a) {
        map.clear();
        for(int i=0;i<list.size();i++){
            map.put(list.get(i).getCommodityId()+"",a);
        }
    }

    //接口回调
       public interface Bool{
          void getdate(boolean flag);
          void delete(int i);
       }
       private Bool bool;

       public void setBool(Bool bool) {
           this.bool = bool;
       }
}
