package com.maple.funme.home.guokr.contract;


import com.maple.funme.base.BasePresenter;

/**
 * Created by San on 2016/12/22.
 */

public class GuokrDetailContract {

    public interface View {

        void showResult(String result);

        void showLoading();

        void stopLoading();

        void setTitle(String title);

        void initView();

        void setPresenter(Presenter presenter);
    }

    public interface Presenter extends BasePresenter {

        void loadData();

        void refreshData();

        void showShare();
    }

    public interface Model {

    }

}