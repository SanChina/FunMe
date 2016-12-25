package com.maple.funme.home.zhihu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maple.funme.R;
import com.maple.funme.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ZhihuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ZhihuFragment extends BaseFragment {

    public ZhihuFragment() {
    }

    public static ZhihuFragment newInstance() {
        return new ZhihuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zhihu, container, false);
    }

}
