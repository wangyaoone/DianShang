package com.bw.ds.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.activity.AdressActivity;
import com.bw.ds.activity.LoginActivity;
import com.bw.ds.activity.MyPageMoneyActivity;
import com.bw.ds.base.BaseFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Auther: 12547
 * @Date: 2019/3/17 15:03:07
 * @Description:
 */
public class MyPageFragment extends BaseFragment {

    private SimpleDraweeView kimg;
    private TextView kname;
    private TextView money;
    private TextView adress;
    private SharedPreferences sp;
    private SharedPreferences sp1;
    private String nname;
    private String npic;

    @Override
    protected int layoutResID() {
        return R.layout.mypage_item;
    }

    @Override
    protected Object initPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
          //找到头像的控件
        kimg = view.findViewById(R.id.my_image_view);
        kname = view.findViewById(R.id.loginna);

            //赋值
        sp1 = getActivity().getSharedPreferences("嗯", Context.MODE_PRIVATE);
        //钱包与地址的id
        money = view.findViewById(R.id.mypagemoney);
        adress = view.findViewById(R.id.mypageadress);

    }

    @Override
    protected void initDate() {
        //接收页面注册
        //EventBus.getDefault().register(this);
         //头像的点击事件  点击跳转进入登录页面
        kimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
                   startActivity(intent);
            }
        });

        //点击进入钱包展示余额
        sp = getActivity().getSharedPreferences("嗯", Context.MODE_PRIVATE);
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = sp.getString("uid", "");
                String sid = sp.getString("sid", "");
                     if (sid.equals("")){
                         Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                            return;
                     }
                     //登录成功的操作
                Intent intent = new Intent(getActivity(), MyPageMoneyActivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("sid",sid);
                        startActivity(intent);
            }
        });

        //点击进入收获地址
        adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = sp.getString("uid", "");
                String sid = sp.getString("sid", "");
                if (sid.equals("")){
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }
              //操作
                Intent intent = new Intent(getActivity(), AdressActivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("sid",sid);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {
    }

    @Override
    public void onStart() {
        super.onStart();

        nname = sp1.getString("nname", "");
        npic = sp1.getString("npic", "");
        if (!nname.equals("")){
            kname.setText(nname);
            Uri uri = Uri.parse(npic);
            kimg.setImageURI(uri);
            return;
        }

    }

    /*//接收传过来的值
            @Subscribe(threadMode = ThreadMode.MAIN)
            public void getdate(String date){
                String[] split = date.split(",");
                kname.setText(split[1]);
                Uri uri = Uri.parse(split[0]);
                kimg.setImageURI(uri);
            }*/
    //销毁的方法
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
