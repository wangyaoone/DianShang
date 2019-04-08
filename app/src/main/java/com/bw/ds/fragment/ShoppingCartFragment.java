package com.bw.ds.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ds.R;
import com.bw.ds.activity.DetailsActivity;
import com.bw.ds.activity.FirmOrderActivity;
import com.bw.ds.adapter.ShoppAdapter;
import com.bw.ds.base.BaseFragment;
import com.bw.ds.bean.ShoppBean;
import com.bw.ds.presenter.ShoppingPresenter;
import com.bw.ds.view.ShoppView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 12547
 * @Date: 2019/3/17 15:01:16
 * @Description:
 */
public class ShoppingCartFragment extends BaseFragment<ShoppingPresenter> implements ShoppView {
    private Handler handler=new Handler();
    private SharedPreferences sp;
    private SwipeRefreshLayout swip;
    private RecyclerView recyy;
    private Map<String,String>map=new HashMap<>();
    private String uid;
    private String sid;
    private CheckBox check;
    private TextView money;
    private Button mybuy;


    @Override
    protected int layoutResID() {
        return R.layout.shoppingcart_item;
    }

    @Override
    protected ShoppingPresenter initPresenter() {
        home=new ShoppingPresenter(this);
        home.attachview(this);
        return home;
    }

    @Override
    protected void initView(View view) {
        swip = view.findViewById(R.id.swip);
        recyy = view.findViewById(R.id.shopprecy);
        check = view.findViewById(R.id.check);
        money = view.findViewById(R.id.nummoney);
        mybuy = view.findViewById(R.id.myguy);
        //设置布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyy.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initDate() {
        //得到sp
        sp = getActivity().getSharedPreferences("嗯", Context.MODE_PRIVATE);

        //设置下拉属性
        swip.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //获取sp中存的值
                        swip.setRefreshing(false);
                      //进行判断   如果登录那么走网络请求展示购物车
                        if (uid.equals("")){
    Toast.makeText(getActivity(), "刷啥啊,登了吗", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            //进入p层
                            home.Shopp(uid, sid);
                        }
                    }
                },2000);
            }
        });
    }

    @Override
    protected void loadData() {

    }

    //V层传过来的值
    @Override
    public void view(final List<ShoppBean.ResultBean> result) {
       /* Log.i("aa",result+"");*/
        //适配器
        final ShoppAdapter shoppAdapter = new ShoppAdapter(getActivity(),result);
        recyy.setAdapter(shoppAdapter);


       //点击结算
        mybuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<result.size();i++){
                      if (result.get(i).isIschecked()){
                          int commodityId = result.get(i).getCommodityId();
                          String commodityName = result.get(i).getCommodityName();
                          int price = result.get(i).getPrice();
                          String picture = result.get(i).getPic();
                          Intent intent1 = new Intent(getActivity(), FirmOrderActivity.class);
                          intent1.putExtra("id",commodityId);
                          intent1.putExtra("name",commodityName);
                          intent1.putExtra("price",price);
                          intent1.putExtra("pic",picture);
                               startActivity(intent1);
                            return;
                      }
                }
            }
        });


       //点击全选及全不选及价格
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               for (int i=0;i<result.size();i++){
                   result.get(i).setIschecked(check.isChecked());
               }
                shoppAdapter.notifyDataSetChanged();
                //调用价钱
                moneyy(result);
            }
        });
     //反选
        shoppAdapter.setBool(new ShoppAdapter.Bool() {
            @Override
            public void getdate() {
                boolean da=true;
                for (int i=0;i<result.size();i++){
                    da=da&result.get(i).isIschecked();
                }
                check.setChecked(da);
                //调用价钱
                moneyy(result);
            }

            @Override
            public void delete(int i) {
                result.remove(i);
                shoppAdapter.notifyDataSetChanged();
            }
            @Override
            public void del(int num, int i) {
                   if (num==0){
                       result.remove(i);
                       shoppAdapter.notifyDataSetChanged();
                   }
            }


        });
    }

     //价格
     private void  moneyy(List<ShoppBean.ResultBean> result){
                 int zong=0;
              for (int i=0;i<result.size();i++){
                     if (result.get(i).isIschecked()){
                         //价格乘以数量
     int i1 = result.get(i).getPrice() * result.get(i).getAaa();
                         zong+= i1;
                     }
              }
         money.setText(zong+"");
     }

    @Override
    public void onStart() {
        super.onStart();
        uid = sp.getString("uid", "");
        sid = sp.getString("sid", "");
        //进行判断   如果登录那么走网络请求展示购物车
        if (uid.equals("")){
           return;
        }
            //进入p层
        home.Shopp(uid, sid);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        home.detachview();
    }
}
