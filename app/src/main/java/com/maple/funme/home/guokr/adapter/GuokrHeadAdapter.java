package com.maple.funme.home.guokr.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maple.funme.R;
import com.maple.funme.domain.GuokrHead;
import com.maple.funme.interfaces.OnItemClickListener;

import java.util.List;

/**
 * Created by San on 2016/12/23.
 */

public class GuokrHeadAdapter extends PagerAdapter {

    private Context mContext;
    private List<GuokrHead.ResultEntity> mList;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public GuokrHeadAdapter(List<GuokrHead.ResultEntity> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        mContext = container.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_guokr_head, container, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
        initView(view, position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    private void initView(View view, int position) {
        ImageView iv_head_background = (ImageView) view.findViewById(R.id.iv_head_background);
        TextView tv_head_title = (TextView) view.findViewById(R.id.tv_head_title);

        tv_head_title.setText(mList.get(position).customTitle);

        String url_picture = mList.get(position).picture;
        Glide.with(mContext)
                .load(url_picture)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.drawable.placeholder)
                .centerCrop()
                .into(iv_head_background);
    }

}
