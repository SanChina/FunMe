package com.maple.funme.home.guokr.presenter;

import android.content.Context;
import android.content.Intent;

import com.maple.funme.home.guokr.contract.GuokrDetailContract;
import com.maple.funme.util.Api;

/**
* Created by San on 2016/12/22
*/

public class GuokrDetailPresenterImpl implements GuokrDetailContract.Presenter{
    private GuokrDetailContract.View mView;
    private Context mContext;

    private int id;
    private String title;
    private String headline_img;

    public GuokrDetailPresenterImpl(Context context, GuokrDetailContract.View view, int id) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);
        this.id = id;

        /*id = intent.getStringExtra(Constant.KEY_ID);
        title = intent.getStringExtra(Constant.KEY_TITLE);
        headline_img = intent.getStringExtra(Constant.KEY_HEADLINE_IMG);*/
    }

    @Override
    public void loadData() {
        mView.showLoading();

        mView.showResult("Hello World !");

//        mView.setTitle("");

    }

    @Override
    public void refreshData() {
        start();
    }

    @Override
    public void showShare() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String text = Api.GUOKR_ARTICLE_LINK_V1 + id + "\t\t\t" + "分享自 FunMe";

        intent.putExtra(Intent.EXTRA_TEXT, text);
        mContext.startActivity(Intent.createChooser(intent, "分享至"));
    }

    @Override
    public void start() {
        loadData();
    }
}