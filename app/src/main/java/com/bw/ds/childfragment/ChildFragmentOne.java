package com.bw.ds.childfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.adapter.ChaAdapter;
import com.bw.ds.api.Api;
import com.bw.ds.api.ApiService;
import com.bw.ds.base.ChildBaseFragment;
import com.bw.ds.bean.ChaBean;
import com.bw.ds.util.UtilDate;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: 12547
 * @Date: 2019/3/28 16:36:59
 * @Description:
 */
public class ChildFragmentOne extends ChildBaseFragment {


    private RecyclerView recy;
    private SharedPreferences sp;
    private String uid;
    private String sid;

    @Override
    protected int layoutResID() {
        return R.layout.childfragment1_item;
    }

    @Override     //控件
    protected void initView(View view) {
        recy = view.findViewById(R.id.charecy);

    }

    @Override
    protected void initDate() {
            //设置布局管理者
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recy.setLayoutManager(linearLayoutManager);
        //得到sp的值
        sp = getActivity().getSharedPreferences("嗯", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        sid = sp.getString("sid", "");
    }

    @Override     //耗时操作写在懒加载方法中
    protected void loadData() {

    }


    @Override
    public void onStart() {
        super.onStart();
        uid = sp.getString("uid", "");
        sid = sp.getString("sid", "");

        int status=0;
        int page=1;
        int count=5;
        if (sid.equals("")){
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }else
        {
            //网络请求
            ApiService apiService = UtilDate.getUtilDate().getserviserHand(Api.Url, uid, sid, ApiService.class);
            Call<ChaBean> call = apiService.getstate(status, page, count);
            call.enqueue(new Callback<ChaBean>() {
                @Override
                public void onResponse(Call<ChaBean> call, Response<ChaBean> response) {
                    List<ChaBean.OrderListBean> orderList = response.body().getOrderList();
                    //适配器
                    ChaAdapter chaAdapter = new ChaAdapter(getActivity(),orderList);
                    recy.setAdapter(chaAdapter);
                }
                @Override
                public void onFailure(Call<ChaBean> call, Throwable t) {

                }
            });
        }
    }
}
