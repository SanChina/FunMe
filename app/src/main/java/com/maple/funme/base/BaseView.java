package com.maple.funme.base;

import android.view.View;

/**
 * Created by San on 2016/12/23.
 */

public interface BaseView<T> {

    void initView(View view);

    void setPresenter(T presenter);
}
