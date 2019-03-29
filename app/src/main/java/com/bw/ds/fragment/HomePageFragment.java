package com.bw.ds.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.activity.DetailsActivity;
import com.bw.ds.activity.FluidlayoutActivity;
import com.bw.ds.adapter.DateAdapter;
import com.bw.ds.base.BaseFragment;
import com.bw.ds.bean.BannaBean;
import com.bw.ds.bean.PageBean;
import com.bw.ds.myview.MyView;
import com.bw.ds.presenter.HomePresenter;
import com.bw.ds.view.PageView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/17 14:59:45
 * @Description:
 */
public class HomePageFragment extends BaseFragment<HomePresenter> implements PageView {

    private MyView kmo;
    private FlyBanner kfly;
    private RecyclerView krecy;

    @Override   //布局
    protected int layoutResID() {
        return R.layout.homepage_item;
    }

    @Override
    protected HomePresenter initPresenter() {
         home=new HomePresenter(this);
         home.attachview(this);
        return home;
    }

    @Override   //控件
    protected void initView(View view) {
        kmo = view.findViewById(R.id.myviewone);
        kfly = view.findViewById(R.id.flyban);
        krecy = view.findViewById(R.id.recy);
    }

    @Override    //数据
    protected void initDate() {
        //设置recycle的布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        krecy.setLayoutManager(linearLayoutManager);
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
          //创建P层对象
           home.Bana();
           home.PageDate();

    }

    @Override
    protected void loadData() {

    }

    @Override    //得到轮播数据
    public void banna(List<BannaBean.ResultBean> date) {
         List<String> list=new ArrayList<>();
                    for (int i=0;i<date.size();i++){
                        list.add(date.get(i).getImageUrl());
                    }
              kfly.setImagesUrl(list);
    }

    @Override    //得到首页数据
    public void pagedate(PageBean.ResultBean result) {

     //走适配器
        DateAdapter dateAdapter = new DateAdapter(getActivity(),result);
           krecy.setAdapter(dateAdapter);
         //接口回调接收到的值
        dateAdapter.setDate(new DateAdapter.Date() {
            @Override
            public void getdate(int i) {
                /*Toast.makeText(getActivity(), "i:" + i, Toast.LENGTH_SHORT).show();*/
                  //得到接口的id值进行详情页面的展示
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("date",i);
                       startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        home.detachview();
    }
}
