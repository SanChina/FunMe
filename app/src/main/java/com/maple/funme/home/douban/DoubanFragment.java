package com.maple.funme.home.douban;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maple.funme.R;
import com.maple.funme.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoubanFragment extends BaseFragment {


    public DoubanFragment() {
    }

    public static DoubanFragment newInstance() {
        return new DoubanFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_douban, container, false);
    }

}
