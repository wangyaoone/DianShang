package com.bw.ds.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.api.Api;
import com.bw.ds.api.ApiService;
import com.bw.ds.bean.NewAdressBean;
import com.bw.ds.util.UtilDate;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAdressActivity extends AppCompatActivity {
    //申明对象
    CityPickerView mPicker = new CityPickerView();
    private TextView ad;
    private EditText adad;
    private EditText name;
    private EditText phone;
    private EditText xing;
    private EditText emile;
    private SharedPreferences sp;
    private String uid;
    private String sid;
    private Button cun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_adress);
        name = findViewById(R.id.adname);
        phone = findViewById(R.id.adhpone);
        xing = findViewById(R.id.adxiang);
        emile = findViewById(R.id.ademile);
        sp = getSharedPreferences("嗯", MODE_PRIVATE);
        ad = findViewById(R.id.adadrere);
        cun = findViewById(R.id.cun);

            mPicker.init(this);
            adad = findViewById(R.id.adadre);
            //ad  为点击
        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //添加默认的配置，不需要自己定义，当然也可以自定义相关熟悉，详细属性请看demo
                    CityConfig cityConfig = new CityConfig.Builder().build();
                    mPicker.setConfig(cityConfig);
                    //显示
                    mPicker.showCityPicker();
                    //监听选择点击事件及返回结果
                    mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                        @Override
                        public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                             //为输入框输入详情数据
                            adad.setText(province.getName() + city.getName() + district.getName());
                            //省份province
                            //城市city
                            //地区district
                        }
                        @Override
                        public void onCancel() {
                        }
                    });
                }
        });

        cun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String mingzi = name.getText().toString();
                 String dianhua = phone.getText().toString();
                String city = adad.getText().toString();
                String xiang = xing.getText().toString();
                 String em =emile.getText().toString();

                final String a=city+xiang;
                uid = sp.getString("uid", "");
                sid = sp.getString("sid", "");
                ApiService apiService = UtilDate.getUtilDate().getserviserHand(Api.Url, uid, sid, ApiService.class);
                Call<NewAdressBean> call = apiService.getnewadress(mingzi, dianhua, a, em);
                call.enqueue(new Callback<NewAdressBean>() {
                    @Override
                    public void onResponse(Call<NewAdressBean> call, Response<NewAdressBean> response) {
                        String message = response.body().getMessage();
                        Toast.makeText(NewAdressActivity.this, message, Toast.LENGTH_SHORT).show();
                               if (message.equals("添加成功")){
                                     finish();
                                     return;
                               }
                    }

                    @Override
                    public void onFailure(Call<NewAdressBean> call, Throwable t) {

                    }
                });

            }
        });


    }


}
