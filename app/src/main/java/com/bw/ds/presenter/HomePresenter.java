package com.bw.ds.presenter;

import android.util.Log;

import com.bw.ds.base.BaseFragmentPragrent;
import com.bw.ds.bean.BannaBean;
import com.bw.ds.bean.PageBean;
import com.bw.ds.model.PageModel;
import com.bw.ds.view.PageView;

import java.util.List;

/**
 * @Auther: 12547
 * @Date: 2019/3/20 20:48:31
 * @Description:
 */
public class HomePresenter extends BaseFragmentPragrent<PageView> {

    private final PageModel pageModel;
    private final PageView pageView;

    public HomePresenter(PageView view) {
        pageModel = new PageModel();
        pageView = view;
    }


    @Override
    public void Bana() {
        pageModel.bannar();
        pageModel.setDate(new PageModel.Date() {
            @Override
            public void getdate(List<BannaBean.ResultBean> date) {
                pageView.banna(date);
            }

            @Override
            public void getpagedate(PageBean.ResultBean result) {
               /* Log.i("aa",result+"");*/
                pageView.pagedate(result);
            }
        });
    }

    @Override
    public void PageDate() {
        pageModel.PageDate();
    }
}
