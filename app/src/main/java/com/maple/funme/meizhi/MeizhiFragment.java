package com.maple.funme.meizhi;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maple.funme.R;
import com.maple.funme.constant.Constant;
import com.maple.funme.domain.MeizhiEntity;
import com.maple.funme.interfaces.OnItemClickListener;
import com.maple.funme.meizhi.adapter.MeizhiAdapter;
import com.maple.funme.meizhi.contract.MeizhiContract;
import com.maple.funme.meizhi.presenter.MeizhiPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeizhiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeizhiFragment extends Fragment implements MeizhiContract.View {
    private OnFragmentInteractionListener mListener;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private MeizhiContract.Presenter mPresenter;
    private MeizhiAdapter mAdapter;

    public MeizhiFragment() {
    }

    public static MeizhiFragment newInstance() {
        return new MeizhiFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizhi, container, false);
        ButterKnife.bind(this, view);

        initView(view);

        new MeizhiPresenterImpl(this);

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
    public void showResult(final MeizhiEntity meizhiEntity) {
        if (meizhiEntity != null) {
            mAdapter = new MeizhiAdapter(meizhiEntity.results);
            mRecyclerView.setAdapter(mAdapter);

            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
//                    Toast.makeText(getActivity(), position + "点击了...", Toast.LENGTH_SHORT).show();
                    String url_meizhi = meizhiEntity.results.get(position).url;
                    Intent intent = MeizhiDetailActivity.newIntent(getActivity(), url_meizhi);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ImageView iv_meizhi = (ImageView) view.findViewById(R.id.iv_meizhi);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                                getActivity(), iv_meizhi, Constant.TRANSITION_ANIMATION_NEWS_PHOTOS);
                        startActivity(intent, options.toBundle());
                    } else {
                        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(
                                view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                        ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
                    }
                }
            });
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void initView(View view) {
        mListener.onSetToolbar(mToolbar);
        mRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mSrl.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_light);

        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    @Override
    public void setPresenter(MeizhiContract.Presenter presenter) {
        if (presenter != null) { //if (mPresenter != null) 判断参数错误
            mPresenter = presenter;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
        void onSetToolbar(Toolbar toolbar);
    }
}
