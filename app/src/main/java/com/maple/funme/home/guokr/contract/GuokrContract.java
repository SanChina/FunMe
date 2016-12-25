package com.maple.funme.home.guokr.contract;

import com.maple.funme.base.BasePresenter;
import com.maple.funme.base.BaseView;
import com.maple.funme.domain.GuokrBody;
import com.maple.funme.domain.GuokrHead;

/**
 * Created by San on 2016/12/23.
 */

public class GuokrContract {

    public interface View extends BaseView<Presenter> {

        void showLoading();

        void stopLoading();

        void showViewPager(GuokrHead guokrHead);

        void showRecyclerView(GuokrBody guokrBody);

    }

    public interface Presenter extends BasePresenter {

        void loadDate();

        void refreshData();

        void startReading(int type, int position);
    }

    public interface Model {

    }

}