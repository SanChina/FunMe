package com.maple.funme.meizhi.presenter;
import android.util.Log;

import com.google.gson.Gson;
import com.maple.funme.domain.MeizhiEntity;
import com.maple.funme.http.RequestFactory;
import com.maple.funme.interfaces.IRequestCallback;
import com.maple.funme.interfaces.IRequestManager;
import com.maple.funme.meizhi.contract.MeizhiContract;
import com.maple.funme.util.Api;

import java.util.ArrayList;
import java.util.List;

/**
* Created by San on 2016/12/24
*/

public class MeizhiPresenterImpl implements MeizhiContract.Presenter{

    private List<MeizhiEntity> mList;
    private MeizhiContract.View mView;
    private IRequestManager mRequest;
    private Gson gson;

    public MeizhiPresenterImpl(MeizhiContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mRequest = RequestFactory.getRequestManager();
        gson = new Gson();
        mList = new ArrayList<>();
    }

    @Override
    public void loadData() {
        mView.showLoading();

        mRequest.get(Api.MEI_ZHI_FULI, new IRequestCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("TAG", response);
                MeizhiEntity meizhiEntity = gson.fromJson(response, MeizhiEntity.class);

                mView.showResult(meizhiEntity);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        mView.stopLoading();
    }

    @Override
    public void start() {
        loadData();
    }
}