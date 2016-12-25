package com.maple.funme.meizhi;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.maple.funme.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class MeizhiDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MEIZHI_URL = "com.maple.funme.meizhi.meizhi_url";

    @BindView(R.id.iv_meizhi_detail)
    ImageView mIvMeizhiDetail;
    @BindView(R.id.pv_meizhi_detail)
    PhotoView mPvMeizhiDetail;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private String mUrl_meizhi;

    private boolean isHidden = false;

    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, MeizhiDetailActivity.class);
        intent.putExtra(EXTRA_MEIZHI_URL, url);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizhi_detail);
        ButterKnife.bind(this);

        mToolbar.setTitle("妹纸");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initData();

        initView();

    }

    private void initView() {
        Picasso.with(this)
                .load(mUrl_meizhi)
                .into(mIvMeizhiDetail);

        mPvMeizhiDetail.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                Toast.makeText(getApplication(), "什么", Toast.LENGTH_SHORT).show();
                hideOrShowToolbar();
                hideOrShowStatusBar();
            }
        });

        mPvMeizhiDetail.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplication(), "长按", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(MeizhiDetailActivity.this)
                        .setMessage("保存到手机")
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                savePicture();
                                dialog.dismiss();
                            }
                        }).show();
                return true;
            }
        });

        initLoadView();
//        loadPhotoView();
    }

    private void hideOrShowStatusBar() {
        /*if (mIsStatusBarHidden) {
            SystemUiVisibilityUtil.enter(PhotoDetailActivity.this);
        } else {
            SystemUiVisibilityUtil.exit(PhotoDetailActivity.this);
        }
        mIsStatusBarHidden = !mIsStatusBarHidden;*/
    }

    private void hideOrShowToolbar() {
        isHidden = !isHidden;
        if(isHidden){
            startAnimation(true,1.0f,0.0f);
        }else{
            startAnimation(false,0.1f,1.0f);
        }
    }

    private void startAnimation(final boolean endState, float startValue, float endValue) {
        ValueAnimator animator = ValueAnimator.ofFloat(startValue, endValue).setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float y1;
                if(endState){
                    y1 = (0 - animation.getAnimatedFraction())*mToolbar.getHeight();
                }else{
                    y1 = (animation.getAnimatedFraction() - 1)*mToolbar.getHeight();
                }
                mToolbar.setTranslationY(y1);
            }
        });
        animator.start();
    }

    private void savePicture() {
        Toast.makeText(this, "保存图片", Toast.LENGTH_SHORT).show();
    }

    private void initLoadView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getEnterTransition()
                    .addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {

                        }

                        @Override
                        public void onTransitionEnd(Transition transition) {
                            loadPhotoView();
                        }

                        @Override
                        public void onTransitionCancel(Transition transition) {

                        }

                        @Override
                        public void onTransitionPause(Transition transition) {

                        }

                        @Override
                        public void onTransitionResume(Transition transition) {

                        }
                    });
        } else {
            loadPhotoView();
        }
    }

    private void loadPhotoView() {
        Picasso.with(this)
                .load(mUrl_meizhi)
                .into(mPvMeizhiDetail);
    }

    private void initData() {
        mUrl_meizhi = getIntent().getStringExtra(EXTRA_MEIZHI_URL);
    }
}
