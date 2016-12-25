package com.maple.funme.home.guokr.presenter;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.maple.funme.constant.Constant;
import com.maple.funme.domain.GuokrBody;
import com.maple.funme.domain.GuokrHead;
import com.maple.funme.home.guokr.GuokrDetailActivity;
import com.maple.funme.home.guokr.contract.GuokrContract;
import com.maple.funme.http.RequestFactory;
import com.maple.funme.interfaces.IRequestCallback;
import com.maple.funme.interfaces.IRequestManager;
import com.maple.funme.util.Api;

import java.util.ArrayList;
import java.util.List;

/**
* Created by San on 2016/12/23
*/

public class GuokrPresenterImpl implements GuokrContract.Presenter{

    private Context mContext;
    private GuokrContract.View view;
    private IRequestManager request;
    private List<GuokrHead.ResultEntity> mHeadList = new ArrayList<>();
    private List<GuokrBody.ResultEntity> mBodyList = new ArrayList<>();
    private Gson gson;

    public GuokrPresenterImpl(Context context, GuokrContract.View view) {
        mContext = context;
        this.view = view;
        this.view.setPresenter(this);
        request = RequestFactory.getRequestManager();
        gson = new Gson();
    }

    @Override
    public void loadDate() {

        this.view.showLoading();


        request.get(Api.GUOKR_HANDPICK_CAROUSEL, new IRequestCallback() {
            @Override
            public void onSuccess(String response) {
                GuokrHead guokrHead = gson.fromJson(response, GuokrHead.class);
//                Log.d("TAG1", response);
                for (GuokrHead.ResultEntity headEntity : guokrHead.result) {
                    mHeadList.add(headEntity);
                }
                view.showViewPager(guokrHead);

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        request.get(Api.GUOKR_ARTICLES, new IRequestCallback() {
            @Override
            public void onSuccess(String response) {
                GuokrBody guokrBody = gson.fromJson(response, GuokrBody.class);
//                Log.d("TAG2", response);
                for (GuokrBody.ResultEntity bodyEntity : guokrBody.result) {
                    mBodyList.add(bodyEntity);
                }
                view.showRecyclerView(guokrBody);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        view.stopLoading();
    }

    @Override
    public void refreshData() {
        start();
    }

    @Override
    public void startReading(int type, int position) {
        Intent intent = new Intent(mContext, GuokrDetailActivity.class);
        if (type == Constant.TYPE_HEAD) {
            GuokrHead.ResultEntity headEntity = mHeadList.get(position);
            intent.putExtra(Constant.KEY_ID, headEntity.articleId);
            intent.putExtra(Constant.KEY_LINK, "http://jingxuan.guokr.com/pick/v2/77403/");
            intent.putExtra(Constant.KEY_HEADLINE_IMG, "");
            intent.putExtra(Constant.KEY_PICTURE, headEntity.picture);
            intent.putExtra(Constant.KEY_TITLE, headEntity.customTitle);
        } else {
            GuokrBody.ResultEntity bodyEntity = mBodyList.get(position);
            intent.putExtra(Constant.KEY_ID, bodyEntity.id);
            intent.putExtra(Constant.KEY_LINK, bodyEntity.linkV2);
            intent.putExtra(Constant.KEY_HEADLINE_IMG, bodyEntity.headlineImg);
            intent.putExtra(Constant.KEY_PICTURE, "");
            intent.putExtra(Constant.KEY_TITLE, bodyEntity.title);
        }
        mContext.startActivity(intent);
    }

    @Override
    public void start() {
        loadDate();
    }
}