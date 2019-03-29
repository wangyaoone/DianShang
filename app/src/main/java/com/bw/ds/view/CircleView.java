package com.bw.ds.view;

import com.bw.ds.bean.CircleBean;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Auther: 12547
 * @Date: 2019/3/26 13:43:02
 * @Description:
 */
public interface CircleView {
        void view(List<CircleBean.ResultBean> result);
        void a(CompositeDisposable compositeDisposable);
}
