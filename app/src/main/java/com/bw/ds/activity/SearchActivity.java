package com.bw.ds.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.adapter.SearchAdapter;
import com.bw.ds.base.BaseActivity;
import com.bw.ds.bean.SearchBean;
import com.bw.ds.myview.MyView;
import com.bw.ds.presenter.SearchPresenter;
import com.bw.ds.view.SearchView;

import java.util.List;
import java.util.logging.Handler;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchView {

    private int page=1;
    private MyView thiree;
    private RecyclerView recy;
    private ImageView img;
    private EditText ed;
    private ImageView sousuo;
    private SwipeRefreshLayout sim;
    private android.os.Handler handler=new android.os.Handler();

    @Override
    protected int layoutResID() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchPresenter initPresenter() {
        date=new SearchPresenter(this);
        date.attachview(this);
        return date;
    }

    @Override
    protected void initView() {
        thiree = findViewById(R.id.myviewthree);
        //得到子搜索按钮及输入框内容
        ed = thiree.findViewById(R.id.myed);
        sousuo = thiree.findViewById(R.id.mysou);

        recy = findViewById(R.id.recyfour);
        img = findViewById(R.id.errorimg);

        //下拉刷新
        sim = findViewById(R.id.searchsim);
    }

    @Override
    protected void initDate() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchActivity.this, 2);
        recy.setLayoutManager(gridLayoutManager);
         //获取到输入框的值
        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
              date.search(name,page);

       //重新走一遍网络
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ed.getText().toString();
                if (!TextUtils.isEmpty(s)){
                    date.search(s,page);
                    return;
                }
    Toast.makeText(SearchActivity.this, "输入不为空", Toast.LENGTH_SHORT).show();

            }
        });

            //下拉刷新
        sim.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        date.search(name,page);
                        sim.setRefreshing(false);
                    }
                },2000);
            }
        });

        //上拉加载


    }

    //得到搜索到的值
    @Override
    public void view(final List<SearchBean.ResultBean> result) {
        if (result.size()==0){
            sim.setVisibility(View.GONE);
            img.setVisibility(View.VISIBLE);
        }else
        {
            sim.setVisibility(View.VISIBLE);
            img.setVisibility(View.GONE);
        }
         //走适配器
        SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this,result);
        recy.setAdapter(searchAdapter);

        //接收点击事件   跳转到详情
        searchAdapter.setDateClick(new SearchAdapter.DateClick() {
            @Override
            public void getdate(int i) {
                Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                   intent.putExtra("date",i);
                   startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        date.detachview();
    }
}
