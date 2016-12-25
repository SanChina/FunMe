package com.maple.funme.home.guokr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maple.funme.R;
import com.maple.funme.constant.Constant;
import com.maple.funme.home.guokr.contract.GuokrDetailContract;
import com.maple.funme.home.guokr.presenter.GuokrDetailPresenterImpl;
import com.maple.funme.util.Api;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuokrDetailActivity extends AppCompatActivity implements GuokrDetailContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.iv_guokr_detail_head)
    ImageView mIvGuokrDetailHead;
    @BindView(R.id.tv_guokr_detail_title)
    TextView mTvGuokrDetailTitle;

    private GuokrDetailContract.Presenter mPresenter;

    private int id;
    private String link;
    private String title;
    private String headline_img;
    private String headline_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guokr_detail);
        ButterKnife.bind(this);

        mToolbar.setTitle("文章精度");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();

        Intent intent = getIntent();
        initData(intent);

        new GuokrDetailPresenterImpl(this, this, id);

        mPresenter.start();


    }

    private void initData(Intent intent) {

        id = intent.getIntExtra(Constant.KEY_ID, 0);
        link = intent.getStringExtra(Constant.KEY_LINK);
        title = intent.getStringExtra(Constant.KEY_TITLE);
        headline_img = intent.getStringExtra(Constant.KEY_HEADLINE_IMG);
        headline_pic = intent.getStringExtra(Constant.KEY_PICTURE);

        Log.d("*******", "*****************" + id);
    }

    @Override
    public void showResult(String result) {
        String url_img;
//        mWebView.loadUrl(link);
        if (!TextUtils.isEmpty(headline_img)) {
            url_img = headline_img;
            mWebView.loadUrl(link);
        } else {
            mWebView.loadUrl(Api.GUOKR_ARTICLE_LINK_V2 + id);
            url_img = headline_pic;
        }
//        mWebView.loadUrl("http://jingxuan.guokr.com/pick/v2/77388/");
        Log.d("*******", "*****************" + link);
        Glide.with(this)
                .load(url_img)
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(mIvGuokrDetailHead);

        setTitle(this.title);
    }

    @Override
    public void showLoading() {
        mSrl.post(new Runnable() {
            @Override
            public void run() {
                mSrl.setRefreshing(true);
            }
        });
    }

    @Override
    public void stopLoading() {
        mSrl.post(new Runnable() {
            @Override
            public void run() {
                mSrl.setRefreshing(false);
            }
        });
    }

    @Override
    public void setTitle(String title) {
        mToolbarLayout.setTitle(this.title);
        mToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        mToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        mToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
    }

    @Override
    public void initView() {

        mSrl.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_light);
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshData();
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                mPresenter.showShare();
            }
        });

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);
//        settings.setAppCacheEnabled(false);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (url != null) {
                    view.loadUrl(url);
                }
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    stopLoading();
                } else {
                    showLoading();
                }
            }
        });
    }

    @Override
    public void setPresenter(GuokrDetailContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
