package com.bw.ds.adapter;

import android.content.Context;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.bean.CircleBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @Auther: 12547
 * @Date: 2019/3/26 15:43:27
 * @Description:
 */
public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.myviewholder> {

    private Context context;
    private List<CircleBean.ResultBean> list;

    public CircleAdapter(Context context, List<CircleBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view=LayoutInflater.from(context).inflate(R.layout.circlechild_item,null,false);
        myviewholder myviewholder = new myviewholder(view);
        return myviewholder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, int i) {
        Uri uri = Uri.parse(list.get(i).getHeadPic());
        myviewholder.img1.setImageURI(uri);
        myviewholder.name.setText(list.get(i).getNickName());

        long createTime = list.get(i).getCreateTime();
        Date date = new Date(createTime);
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        myviewholder.date.setText(format);


        myviewholder.title.setText(list.get(i).getContent());
        String image = list.get(i).getImage();
        String[] split = image.split(",");
        Uri uu = Uri.parse(split[0]);
        myviewholder.img2.setImageURI(uu);
        myviewholder.num.setText(list.get(i).getUserId()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView img1;
        private final TextView name;
        private final TextView date;
        private final TextView title;
        private final SimpleDraweeView img2;
        private final TextView num;

        public myviewholder(@NonNull View itemView) {
               super(itemView);
            img1 = itemView.findViewById(R.id.circleimg);
            name = itemView.findViewById(R.id.circlename);
            date = itemView.findViewById(R.id.cricledate);
            title = itemView.findViewById(R.id.cricletitle);
            img2 = itemView.findViewById(R.id.circleimg2);
            num = itemView.findViewById(R.id.circlenum);
           }
       }
}
