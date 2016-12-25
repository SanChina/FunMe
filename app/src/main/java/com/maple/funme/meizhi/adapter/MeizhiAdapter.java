package com.maple.funme.meizhi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maple.funme.R;
import com.maple.funme.domain.MeizhiEntity;
import com.maple.funme.interfaces.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by San on 2016/12/24.
 */

public class MeizhiAdapter extends RecyclerView.Adapter<MeizhiAdapter.MeizhiViewHolder> {
    private Context mContext;
    private List<MeizhiEntity.ResultsEntity> mList;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public MeizhiAdapter(List<MeizhiEntity.ResultsEntity> list) {
        mList = list;
    }

    @Override
    public MeizhiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_meizhi, parent, false);
        return new MeizhiViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(MeizhiViewHolder holder, int position) {
        MeizhiEntity.ResultsEntity resultsEntity = mList.get(position);
        holder.bindData(resultsEntity);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MeizhiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_meizhi;

        private OnItemClickListener mOnItemClickListener;

        public MeizhiViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            iv_meizhi = (ImageView) itemView.findViewById(R.id.iv_meizhi);

            mOnItemClickListener = listener;
            itemView.setOnClickListener(this);
        }

        public void bindData(MeizhiEntity.ResultsEntity resultsEntity) {
            /*Glide.with(mContext)
                    .load(resultsEntity.url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(R.drawable.author_img)
                    .centerCrop()
                    .into(iv_meizhi);*/
            Picasso.with(mContext)
                    .load(resultsEntity.url)
                    .into(iv_meizhi);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
