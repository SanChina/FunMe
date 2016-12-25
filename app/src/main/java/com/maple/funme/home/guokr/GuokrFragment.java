package com.maple.funme.home.guokr;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maple.funme.R;
import com.maple.funme.base.BaseFragment;
import com.maple.funme.constant.Constant;
import com.maple.funme.domain.GuokrBody;
import com.maple.funme.domain.GuokrHead;
import com.maple.funme.home.guokr.adapter.GuokrBodyAdapter;
import com.maple.funme.home.guokr.adapter.GuokrHeadAdapter;
import com.maple.funme.home.guokr.contract.GuokrContract;
import com.maple.funme.home.guokr.presenter.GuokrPresenterImpl;
import com.maple.funme.interfaces.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuokrFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuokrFragment extends BaseFragment implements GuokrContract.View {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;

    private View view;
    private GuokrContract.Presenter mPresenter;

    private GuokrHeadAdapter mHeadAdapter;
    private GuokrBodyAdapter mBodyAdapter;

    public GuokrFragment() {
    }

    public static GuokrFragment newInstance() {
        return new GuokrFragment();
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
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_guokr, container, false);
            ButterKnife.bind(this, view);
        }
        //初始化View, RecyclerView必须设置setLayoutManager
        initView(view);

        new GuokrPresenterImpl(getActivity(), this);
        mPresenter.start();

        return view;
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
    public void showViewPager(GuokrHead guokrHead) {
        if (mHeadAdapter == null) {
            mHeadAdapter = new GuokrHeadAdapter(guokrHead.result);
            mViewPager.setAdapter(mHeadAdapter);

            mHeadAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
//                    Toast.makeText(getActivity(), position + "点击了...", Toast.LENGTH_SHORT).show();
                    mPresenter.startReading(Constant.TYPE_HEAD, position);
                }
            });
//            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    Toast.makeText(getActivity(), position+"点击了...", Toast.LENGTH_SHORT).show();
//                    mPresenter.startReading(Constant.TYPE_HEAD, position);
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });
        } else {
            mHeadAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showRecyclerView(GuokrBody guokrBody) {
        if (mBodyAdapter == null) {
            mBodyAdapter = new GuokrBodyAdapter(guokrBody.result);
            mRecyclerView.setAdapter(mBodyAdapter);

            mBodyAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    mPresenter.startReading(Constant.TYPE_BODY, position);
                }
            });
        } else {
            mBodyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void initView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setSmoothScrollbarEnabled(true);
//        linearLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(linearLayoutManager); //没有这行,就不显示,查了老半天...........
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false); //上下滑动卡顿问题

        mSrl.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshData();
            }
        });
    }

    @Override
    public void setPresenter(GuokrContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }
}
