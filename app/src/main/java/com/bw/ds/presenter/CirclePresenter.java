package com.bw.ds.presenter;

import com.bw.ds.base.BaseFragmentPragrent;
import com.bw.ds.bean.CircleBean;
import com.bw.ds.model.CircleModel;
import com.bw.ds.view.CircleView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Auther: 12547
 * @Date: 2019/3/26 11:45:26
 * @Description:
 */
public class CirclePresenter extends BaseFragmentPragrent<CircleView> {

    private final CircleModel circleModel;
    private final CircleView circleView;

    public CirclePresenter(CircleView view) {
        circleModel = new CircleModel();
        circleView = view;
    }

    public void circleDate(int page) {
        circleModel.circleDate(page);
        circleModel.sethDate(new CircleModel.HDate() {
            @Override
            public void getdate(List<CircleBean.ResultBean> result) {
                circleView.view(result);
            }

            @Override
            public void a(CompositeDisposable compositeDisposable) {
                circleView.a(compositeDisposable);
            }
        });
    }



    @Override
    public void Bana() {
    }

    @Override
    public void PageDate() {
    }
}
