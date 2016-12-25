package com.maple.funme.home.guokr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maple.funme.R;
import com.maple.funme.domain.GuokrBody;
import com.maple.funme.interfaces.OnItemClickListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by San on 2016/12/21.
 */

public class GuokrBodyAdapter extends RecyclerView.Adapter<GuokrBodyAdapter.GuokrViewHolder> {
    private List<GuokrBody.ResultEntity> mList;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public GuokrBodyAdapter(List<GuokrBody.ResultEntity> list) {
        mList = list;
    }

    @Override
    public GuokrViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_guokr_body, parent, false);
        return new GuokrViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(GuokrViewHolder holder, int position) {
        GuokrBody.ResultEntity resultEntity = mList.get(position);
        holder.bindData(resultEntity);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GuokrViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        private ImageView iv_guokr;
        private TextView tv_title;
        private TextView tv_summary;
        private CircleImageView iv_header_img;
        private TextView tv_source_name;
        private TextView tv_category;

        private OnItemClickListener mOnItemClickListener;

        public GuokrViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            iv_guokr = (ImageView) itemView.findViewById(R.id.iv_guokr);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_summary = (TextView) itemView.findViewById(R.id.tv_summary);
            iv_header_img = (CircleImageView) itemView.findViewById(R.id.iv_header_img);
            tv_source_name = (TextView) itemView.findViewById(R.id.tv_source_name);
            tv_category = (TextView) itemView.findViewById(R.id.tv_category);

            mOnItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        public void bindData(GuokrBody.ResultEntity resultEntity) {
            tv_title.setText(resultEntity.title);
            tv_summary.setText(resultEntity.summary);
            tv_source_name.setText(resultEntity.sourceName);
            String category = resultEntity.category;
            switch (category) {
                case "science": category = "科技"; break;
                case "life": category = "生活"; break;
                case "health": category = "健康" ; break;
                case "learning": category = "学习"; break;
                case "humanities": category = "人文"; break;
                case "nature": category = "自然"; break;
                case "entertainment": category = "娱乐"; break;
                default: break;
            }
            tv_category.setText(category);
            Glide.with(mContext)
                    .load(resultEntity.headlineImg)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(R.drawable.placeholder)
                    .centerCrop()
                    .into(iv_guokr);
            Glide.with(mContext)
                    .load(resultEntity.sourceData.image)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(R.drawable.author_img)
                    .centerCrop()
                    .into(iv_header_img);

        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
}

