package com.maple.funme.home.guokr;

import android.util.SparseArray;

import com.maple.funme.base.BaseFragment;
import com.maple.funme.home.douban.DoubanFragment;
import com.maple.funme.home.zhihu.ZhihuFragment;

/**
 * Created by San on 2016/12/23.
 */

public class HomeFragmentFactory {
    private static SparseArray<BaseFragment> mArray = new SparseArray<>();

    public static BaseFragment createFragment(int position) {
        BaseFragment fragment = mArray.get(position);
        if (fragment == null) {
            if (position == 0) {
                fragment = GuokrFragment.newInstance();
            } else if (position == 1) {
                fragment = ZhihuFragment.newInstance();
            } else if (position == 2) {
                fragment = DoubanFragment.newInstance();
            }
            mArray.put(position, fragment);
        }
        return fragment;
    }
}
