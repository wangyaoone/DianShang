package com.bw.ds.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.bw.ds.R;
import com.bw.ds.activity.LoginActivity;
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
    }

    @Override
    protected void initDate() {
        //接收页面注册
        EventBus.getDefault().register(this);

         //头像的点击事件  点击跳转进入登录页面
        kimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
                   startActivity(intent);
            }
        });
    }
    //接收传过来的值
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getdate(String date){
        String[] split = date.split(",");
        kname.setText(split[1]);
        Uri uri = Uri.parse(split[0]);
        kimg.setImageURI(uri);
    }
    //销毁的方法
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
