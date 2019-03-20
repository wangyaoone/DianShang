package com.bw.ds.fragment;

import android.content.Intent;
import android.view.View;

import com.bw.ds.R;
import com.bw.ds.activity.LoginActivity;
import com.bw.ds.base.BaseFragment;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @Auther: 12547
 * @Date: 2019/3/17 15:03:07
 * @Description:
 */
public class MyPageFragment extends BaseFragment {

    private SimpleDraweeView kimg;

    @Override
    protected int layoutResID() {
        return R.layout.mypage_item;
    }

    @Override
    protected void initView(View view) {
          //找到头像的控件
        kimg = view.findViewById(R.id.my_image_view);
    }

    @Override
    protected void initDate() {
         //头像的点击事件  点击跳转进入登录页面
        kimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
                   startActivity(intent);
            }
        });
    }
}
