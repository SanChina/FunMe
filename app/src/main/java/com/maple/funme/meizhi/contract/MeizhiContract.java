package com.maple.funme.meizhi.contract;

import com.maple.funme.base.BasePresenter;
import com.maple.funme.base.BaseView;
import com.maple.funme.domain.MeizhiEntity;

/**
 * Created by San on 2016/12/24.
 */

public class MeizhiContract {

    public interface View extends BaseView<Presenter> {

        void showLoading();

        void stopLoading();

        void showResult(MeizhiEntity meizhiEntity);
    }

    public interface Presenter extends BasePresenter {

        void loadData();

    }

    public interface Model {

    }

}