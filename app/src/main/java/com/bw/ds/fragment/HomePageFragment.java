package com.bw.ds.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.activity.DetailsActivity;
import com.bw.ds.activity.FluidlayoutActivity;
import com.bw.ds.activity.ThreeShoppActivity;
import com.bw.ds.adapter.DateAdapter;
import com.bw.ds.adapter.PopOneAdapter;
import com.bw.ds.adapter.PopTwoAdapter;
import com.bw.ds.api.Api;
import com.bw.ds.api.ApiService;
import com.bw.ds.base.BaseFragment;
import com.bw.ds.bean.BannaBean;
import com.bw.ds.bean.PageBean;
import com.bw.ds.bean.PopOneBean;
import com.bw.ds.bean.PopTwoBean;
import com.bw.ds.myview.MyView;
import com.bw.ds.presenter.HomePresenter;
import com.bw.ds.util.UtilDate;
import com.bw.ds.view.PageView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: 12547
 * @Date: 2019/3/17 14:59:45
 * @Description:
 */
public class HomePageFragment extends BaseFragment<HomePresenter> implements PageView {
    private RecyclerView recyone;
    private MyView kmo;
    private FlyBanner kfly;
    private RecyclerView krecy;
    private ImageView fenlei;
    private RecyclerView recytwo;


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
    protected void initView(final View view) {
        kmo = view.findViewById(R.id.myviewone);
        kfly = view.findViewById(R.id.flyban);
        krecy = view.findViewById(R.id.recy);
        //二级


        //做三级分类
        fenlei = kmo.findViewById(R.id.myfen);
        fenlei.setOnClickListener(new View.OnClickListener() {



            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                //实例化P层
                home.popone();
                //poprecy控件
                View view = View.inflate(getActivity(), R.layout.pop_item1, null);
                recyone = view.findViewById(R.id.poprecy1);
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
                recyone.setLayoutManager(staggeredGridLayoutManager);

                PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                      popupWindow.setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                      popupWindow.setOutsideTouchable(true);
                      popupWindow.showAsDropDown(kmo);
            }
        });
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

    //得到pop第一季数据
    @Override
    public void getpopone(List<PopOneBean.ResultBean> result) {
           //适配器
        PopOneAdapter popOneAdapter = new PopOneAdapter(getActivity(),result);
           recyone.setAdapter(popOneAdapter);

        popOneAdapter.setDate(new PopOneAdapter.Date() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void getdate(String id) {
                View view = View.inflate(getActivity(), R.layout.poptwo2, null);
                recytwo = view.findViewById(R.id.poprecy2);
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
                recytwo.setLayoutManager(staggeredGridLayoutManager);

                PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(view);


                ApiService getserviser = UtilDate.getUtilDate().getserviser(Api.Url, ApiService.class);
                Call<PopTwoBean> call = getserviser.getpop2(id);
                call.enqueue(new Callback<PopTwoBean>() {
                    @Override
                    public void onResponse(Call<PopTwoBean > call, Response<PopTwoBean> response) {
                        List<PopTwoBean.ResultBean> result = response.body().getResult();
                        //适配器
                        PopTwoAdapter popTwoAdapter = new PopTwoAdapter(getActivity(),result);
                        recytwo.setAdapter(popTwoAdapter);

                        popTwoAdapter.setClickDate(new PopTwoAdapter.ClickDate() {
                            @Override
                            public void getdate(String id) {
                                //得到第三级的id
                                        //跳转到第三极页面
                               Intent intent = new Intent(getActivity(), ThreeShoppActivity.class);
                                intent.putExtra("idid",id+"");
                                     startActivity(intent);

                            }
                        });
                    }
                    @Override
                    public void onFailure(Call<PopTwoBean> call, Throwable t) {

                    }
                });
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        home.detachview();
    }



}
