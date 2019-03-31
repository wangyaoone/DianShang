package com.bw.ds.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.bw.ds.R;

import com.bw.ds.adapter.ThreeShoppAdapter;
import com.bw.ds.api.Api;
import com.bw.ds.api.ApiService;

import com.bw.ds.bean.ThreeShoppBean;
import com.bw.ds.util.UtilDate;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThreeShoppActivity extends AppCompatActivity {

    private RecyclerView recy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_shopp);

        recy = findViewById(R.id.recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ThreeShoppActivity.this, 2);
        recy.setLayoutManager(gridLayoutManager);

        Intent intent = getIntent();
        String idid = intent.getStringExtra("idid");

        ApiService getserviser = UtilDate.getUtilDate().getserviser(Api.Url, ApiService.class);
        Call<ThreeShoppBean> call = getserviser.getthreeshopp(idid, 1, 6);
        call.enqueue(new Callback<ThreeShoppBean>() {
            @Override
            public void onResponse(Call<ThreeShoppBean> call, Response<ThreeShoppBean> response) {
                List<ThreeShoppBean.ResultBean> result = response.body().getResult();
                ThreeShoppAdapter threeShoppAdapter = new ThreeShoppAdapter(ThreeShoppActivity.this,result);
                recy.setAdapter(threeShoppAdapter);

                     //接口回调得到值
                threeShoppAdapter.setClick(new ThreeShoppAdapter.Click() {
                    @Override
                    public void getdate(int commodityId) {
                        Intent intent = new Intent(ThreeShoppActivity.this, DetailsActivity.class);
                        intent.putExtra("date",commodityId);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<ThreeShoppBean> call, Throwable t) {

            }
        });


    }
}
