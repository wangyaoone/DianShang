package com.bw.ds.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.ds.R;
import com.bw.ds.base.BaseFragment;
import com.bw.ds.childfragment.ChildFragmentFive;
import com.bw.ds.childfragment.ChildFragmentFour;
import com.bw.ds.childfragment.ChildFragmentOne;
import com.bw.ds.childfragment.ChildFragmentThree;
import com.bw.ds.childfragment.ChildFragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/17 15:02:11
 * @Description:
 */
public class IndentFragment extends BaseFragment {

    private RadioGroup group;
    private ViewPager page;


    @Override
    protected int layoutResID() {
        return R.layout.indent_item;
    }

    @Override
    protected Object initPresenter() {
        return null;
    }

    @Override   //控件
    protected void initView(View view) {
        group = view.findViewById(R.id.newgroup);
        page = view.findViewById(R.id.nwepage);

    }

    @Override
    protected void initDate() {
            //创建子布局
        final List<Fragment> list=new ArrayList<>();
        list.add(new ChildFragmentOne());
        list.add(new ChildFragmentTwo());
        list.add(new ChildFragmentThree());
        list.add(new ChildFragmentFour());
        list.add(new ChildFragmentFive());

             //适配器
        page.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
             //跟随联动
        group.check(group.getChildAt(0).getId());
        page.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int i) {
                group.check(group.getChildAt(i).getId());

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //点击切换页面
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                     switch (checkedId){
                         case R.id.newb1:
                             page.setCurrentItem(0);
                             break;
                         case R.id.newb2:
                             page.setCurrentItem(1);
                             break;
                         case R.id.newb3:
                             page.setCurrentItem(2);
                             break;
                         case R.id.newb4:
                             page.setCurrentItem(3);
                             break;
                         case R.id.newb5:
                             page.setCurrentItem(4);
                             break;
                     }
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
