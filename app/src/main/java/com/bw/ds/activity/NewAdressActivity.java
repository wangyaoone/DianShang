package com.bw.ds.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.ds.R;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

public class NewAdressActivity extends AppCompatActivity {
    //申明对象
    CityPickerView mPicker = new CityPickerView();
    private TextView ad;
    private EditText adad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_adress);
           ad = findViewById(R.id.adadrere);
           mPicker.init(this);
           adad = findViewById(R.id.adadre);
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
    }
}
