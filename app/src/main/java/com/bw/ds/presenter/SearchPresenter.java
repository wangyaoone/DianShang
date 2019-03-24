package com.bw.ds.presenter;

import com.bw.ds.base.BasePresenter;
import com.bw.ds.bean.SearchBean;
import com.bw.ds.model.SearchModel;
import com.bw.ds.view.SearchView;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 12547
 * @Date: 2019/3/24 15:16:33
 * @Description:
 */
public class SearchPresenter extends BasePresenter<SearchView> {

    private final SearchModel searchModel;
    private final SearchView searchView;

    public SearchPresenter(SearchView view) {
        searchModel = new SearchModel();
        searchView = view;
    }

    public void search(String name, int page) {
        searchModel.search(name,page);

        searchModel.setSearCh(new SearchModel.SearCh() {
            @Override
            public void getdate(List<SearchBean.ResultBean> result) {
                searchView.view(result);
            }
        });

    }

    @Override
    public void date(Map<String, String> map) {

    }

    @Override
    public void regdate(Map<String, String> map) {

    }
}
