package com.harmoni.harmonikeluarga.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.harmoni.harmonikeluarga.R;
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

    public void setDataAdapter(List<DataTopicItem> topicItems) {
//        if (contentItems == null || contentItems.size() == 0)
//            return;
//        if (mDataContentItems != null && mDataContentItems.size() > 0)
//            this.mDataContentItems.clear();
        this.mDataTopicItems.addAll(topicItems);
        notifyDataSetChanged();
    }

    public List<DataTopicItem> getDataAdapter() {
        return mDataTopicItems;
    }

    @Override
    public TopicDegreeAdapter.TopicDegreeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_topic, parent, false);
        return new TopicDegreeHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicDegreeAdapter.TopicDegreeHolder holder, int position) {
        DataTopicItem item = mDataTopicItems.get(position);

//        Glide.with(context)
//                .load(item.getContentImage())
//                .into(holder.mImageContent);
//        holder.mTitleContent.setText(item.getContentTitle());
//        holder.mTextDesc.setText(Html.fromHtml(item.getContentDesc()));
//        holder.mTextDate.setText(item.getContentCreateDate());
        holder.mTitleText.setText(item.getTopicName());
    }

    @Override
    public int getItemCount() {
        return mDataTopicItems != null ? mDataTopicItems.size() : 0;
    }

    public class TopicDegreeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

//        @BindView(R.id.img)
//        ImageView mImageContent;
        @BindView(R.id.text)
        TextView mTitleText;
//        @BindView(R.id.desc)
//        TextView mTextDesc;
//        @BindView(R.id.date)
//        TextView mTextDate;
//        @BindView(R.id.options)
//        TextView mOptions;

        public TopicDegreeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            DataTopicItem item = mDataTopicItems.get(position);
            listener.onItemClick(item);
        }
    }
}
