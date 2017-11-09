package com.harmoni.harmonikeluarga.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataChildItem;
import com.harmoni.harmonikeluarga.model.DataContentItem;
import com.harmoni.harmonikeluarga.model.DataTopicItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akbar on 02/11/17.
 */

public class TopicDegreeAdapter extends RecyclerView.Adapter<TopicDegreeAdapter.TopicDegreeHolder> {

    private ArrayList<DataTopicItem> mDataTopicItems;
    private Context context;
    private OnItemClickListener listener;

    public TopicDegreeAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        mDataTopicItems = new ArrayList<>();
    }

    public interface OnItemClickListener {
        void onItemClick(DataTopicItem item);
    }

    public void setDataAdapter(List<DataTopicItem> contentItems) {
        if (contentItems == null || contentItems.size() == 0)
            return;
        if (mDataTopicItems != null && mDataTopicItems.size() > 0)
            this.mDataTopicItems.clear();
        this.mDataTopicItems.addAll(contentItems);
        notifyDataSetChanged();
    }

//    public List<DataContentItem> getDataAdapter() {
//        return mDataTopicItems;
//    }

    @Override
    public TopicDegreeAdapter.TopicDegreeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_content, parent, false);
        TopicDegreeHolder holder = new TopicDegreeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TopicDegreeAdapter.TopicDegreeHolder holder, int position) {
        DataContentItem item = mDataTopicItems.get(position).getDataContent().get(position);

        Glide.with(context)
                .load(item.getContentImage())
                .into(holder.mImageContent);
        holder.mTitleContent.setText(item.getContentTitle());
        holder.mTextDesc.setText(item.getContentDesc());
        holder.mTextDate.setText(item.getContentCreateDate());
    }

    @Override
    public int getItemCount() {
        return mDataTopicItems != null ? mDataTopicItems.size() : 0;
    }

    public class TopicDegreeHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img)
        ImageView mImageContent;
        @BindView(R.id.title)
        TextView mTitleContent;
        @BindView(R.id.desc)
        TextView mTextDesc;
        @BindView(R.id.date)
        TextView mTextDate;
        @BindView(R.id.options)
        TextView mOptions;

        public TopicDegreeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
