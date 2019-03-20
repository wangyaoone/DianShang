package com.bw.ds.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.activity.FluidlayoutActivity;
import com.bw.ds.base.BaseFragment;
import com.bw.ds.myview.MyView;

/**
 * @Auther: 12547
 * @Date: 2019/3/17 14:59:45
 * @Description:
 */
public class HomePageFragment extends BaseFragment {

    private MyView kmo;

    @Override   //布局
    protected int layoutResID() {
        return R.layout.homepage_item;
    }

    @Override   //控件
    protected void initView(View view) {
        kmo = view.findViewById(R.id.myviewone);
    }

    @Override    //数据
    protected void initDate() {
            //跳转到布局页面
        kmo.findViewById(R.id.myed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FluidlayoutActivity.class);
                startActivity(intent);
            }
        });
        //搜索点击
        kmo.findViewById(R.id.mysou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "输入再搜索", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
