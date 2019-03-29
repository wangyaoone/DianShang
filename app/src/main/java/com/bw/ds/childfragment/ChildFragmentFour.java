package com.bw.ds.childfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.ds.R;

/**
 * @Auther: 12547
 * @Date: 2019/3/28 16:36:59
 * @Description:
 */
public class ChildFragmentFour extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.childfragment4_item, container, false);
              return view;
    }
}
