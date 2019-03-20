package com.bw.ds.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Auther: 12547
 * @Date: 2019/3/17 14:18:49
 * @Description:
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view=View.inflate(getActivity(),layoutResID(),null);
                        initView(view);
                        initDate();
                       return view;
    }


    protected abstract int layoutResID();
    protected abstract void initView(View view);
    protected abstract void initDate();


}
