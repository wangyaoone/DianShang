package com.bw.ds.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bw.ds.R;
import com.bw.ds.adapter.CircleAdapter;
import com.bw.ds.base.BaseFragment;
import com.bw.ds.bean.CircleBean;
import com.bw.ds.presenter.CirclePresenter;
import com.bw.ds.view.CircleView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Auther: 12547
 * @Date: 2019/3/17 15:00:32
 * @Description:
 */
//圈子
public class CircleFragment extends BaseFragment<CirclePresenter> implements CircleView {
    private int page=1;
    private RecyclerView recy;
    private CompositeDisposable a;
    private Handler handler=new Handler();
    private SwipeRefreshLayout swip;

    @Override
    protected int layoutResID() {
        return R.layout.circle_item;
    }

    @Override
    protected CirclePresenter initPresenter() {
        home=new CirclePresenter(this);
        home.attachview(this);
        return home;
    }

    @Override
    protected void initView(View view) {
        recy = view.findViewById(R.id.circlerecy);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recy.setLayoutManager(linearLayoutManager);
        swip = view.findViewById(R.id.circleswi);
    }

    @Override
    protected void initDate() {
        home.circleDate(page);
           //圈子
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        home.circleDate(page++);
                        swip.setRefreshing(false);
                    }
                },2000);
            }
        });

    }

    @Override
    protected void loadData() {
        //下拉刷新
          Log.i("aaa","ok");
    }

    @Override
    public void view(List<CircleBean.ResultBean> result) {
        //Log.i("aaa",result+"");
            //适配器
        CircleAdapter circleAdapter = new CircleAdapter(getActivity(), result);
        recy.setAdapter(circleAdapter);
    }

    @Override
    public void a(CompositeDisposable compositeDisposable) {
          this.a=compositeDisposable;
    }

    //内存泄漏
    @Override
    public void onDestroy() {
        super.onDestroy();
        home.detachview();
        //判断是否解除订阅
        boolean disposed = a.isDisposed();
        if (!disposed){
            //消除订阅
            a.clear();
            //解除订阅
            a.dispose();
            //Log.i("xxx","解绑了");
        }
    }
}
