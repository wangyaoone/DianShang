package com.bw.ds.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.ds.R;
import com.bw.ds.adapter.PageAdapter;
import com.bw.ds.base.BaseActivity;
import com.bw.ds.bean.AddShoppBean;
import com.bw.ds.bean.DetailsBean;
import com.bw.ds.bean.ShoppBean;
import com.bw.ds.bean.addMoreBean;
import com.bw.ds.presenter.DetailsPresenter;
import com.bw.ds.view.DetailsView;
import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsView {


    private List<ImageView> list = new ArrayList<>();
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.numbre)
    TextView numbre;
    @BindView(R.id.shoopprice)
    TextView shoopprice;
    @BindView(R.id.shoopnum)
    TextView shoopnum;
    @BindView(R.id.shoopname)
    TextView shoopname;
    @BindView(R.id.web)
    WebView web;
    private ImageView finn;
    private ImageView add;
    private SharedPreferences sp;
    private DetailsBean.ResultBean resultok;
    private String sid;
    private String uid;
    private ImageView buy;

    @Override
    protected int layoutResID() {
        return R.layout.activity_details;
    }

    @Override
    protected DetailsPresenter initPresenter() {
        date = new DetailsPresenter(this);
        date.attachview(this);
        return date;
    }


    @Override
    protected void initView() {
        finn = findViewById(R.id.fin);
        add = findViewById(R.id.addshopp);
        buy = findViewById(R.id.buyshopp);
    }


    @Override
    protected void initDate() {
                //得到SP中的值
            sp = getSharedPreferences("嗯", MODE_PRIVATE);
        //点击加入购物车   进行判断
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                uid = sp.getString("uid", "");
                sid = sp.getString("sid", "");
                 if (uid.equals("")){
        Intent intent = new Intent(DetailsActivity.this, LoginActivity.class);
                       startActivity(intent);
         Toast.makeText(DetailsActivity.this, "登录在添加购物车", Toast.LENGTH_SHORT).show();
                             return;
                   }
                   //做加入购物车的操作
   /* Toast.makeText(DetailsActivity.this, "登录成功加购物车", Toast.LENGTH_SHORT).show();*/
             //   同步购物车数据 http://172.17.8.100/small/order/verify/v1/syncShoppingCart
              /* 由于接口问题 先进行查询将值那到*/
                     date.chaShopp(uid, sid);
                   //  Log.i("eee",uid+"-"+sid);

            }
        });
        //焦点不好使待修改
        finn.setEnabled(true);
        finn.setFocusable(true);
        finn.setFocusableInTouchMode(true);
        finn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  finish();
            }
        });

        //得到首页展示的id
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("date");
        //网络请求详情的数据
        date.detailsDate(id);


             //点击购买进入确认订单页面
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid = sp.getString("uid", "");
                sid = sp.getString("sid", "");
                if (uid.equals("")){
         Intent intent = new Intent(DetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
          Toast.makeText(DetailsActivity.this, "登录在购买", Toast.LENGTH_SHORT).show();
                    return;
                }
                //操作
        Intent intent1 = new Intent(DetailsActivity.this, FirmOrderActivity.class);
                int commodityId = resultok.getCommodityId();
                String commodityName = resultok.getCommodityName();
                int price = resultok.getPrice();
                String picture = resultok.getPicture();

                intent1.putExtra("id",commodityId);
                intent1.putExtra("name",commodityName);
                intent1.putExtra("price",price);
                intent1.putExtra("pic",picture);
                       startActivity(intent1);
            }
        });

    }


    //得到查询购物车的值
    @Override
    public void vieww(List<ShoppBean.ResultBean> resultt) {

        sp = getSharedPreferences("嗯", MODE_PRIVATE);
        List<addMoreBean>list1 = new ArrayList<>();
        for (int i = 0; i <resultt.size(); i++) {
            //查询购物车获得的id和count
            list1.add(new addMoreBean(resultt.get(i).getCommodityId()+"",1+""));
        }
        //详情的id  和count
        list1.add(new addMoreBean(resultok.getCommodityId()+"",1+""));
        Gson gson = new Gson();
        String  sss = gson.toJson(list1);
        date.addShopp(uid,sid,sss);

    }
     //得到同步之后回调过来的值
    @Override
    public void viewww(String message) {
        Gson gson = new Gson();
        AddShoppBean addShoppBean = gson.fromJson(message, AddShoppBean.class);
        String message1 = addShoppBean.getMessage();
        Toast.makeText(this, message1, Toast.LENGTH_SHORT).show();
    }

    //详情数据
    @Override
    public void view(DetailsBean.ResultBean result) {
         this.resultok=result;
        //得到图片的值
        String picture = result.getPicture();
        //截取字符串
        final String[] split = picture.split(",");
        for (int i = 0; i < split.length; i++) {
            ImageView imageView = new ImageView(DetailsActivity.this);
            Glide.with(DetailsActivity.this).load(split[i]).into(imageView);
            //把ImageView添加给集合
            list.add(imageView);
            //轮播适配器
            PageAdapter pageAdapter = new PageAdapter(list);
            pager.setAdapter(pageAdapter);
        }
        //第一次默认展示第一页的数值
        int currentItem = pager.getCurrentItem() + 1;
        numbre.setText(currentItem + "/" + split.length);
        //设置监听的方法  使当前页面跟随动
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                int currentItem = pager.getCurrentItem() + 1;
                numbre.setText(currentItem + "/" + split.length);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //名字  价格  购买数量赋值
        shoopname.setText(result.getCommodityName());
        shoopprice.setText("¥" + result.getPrice());
        shoopnum.setText("已售:" + result.getStock());

        String details = result.getDetails();

        web.loadData(details, "text/html; charset=UTF-8", null);
       web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        WebSettings settings = web.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }



    //解决内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        date.detachview();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



}
