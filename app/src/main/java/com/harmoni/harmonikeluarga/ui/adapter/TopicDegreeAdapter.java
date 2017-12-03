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

    private ArrayList<DataContentItem> mDataContentItems;
    private Context context;
    private OnItemClickListener listener;

    public TopicDegreeAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        mDataContentItems = new ArrayList<>();
    }

    public interface OnItemClickListener {
        void onItemClick(DataContentItem item);
    }

    public void setDataAdapter(List<DataContentItem> contentItems) {
//        if (contentItems == null || contentItems.size() == 0)
//            return;
//        if (mDataContentItems != null && mDataContentItems.size() > 0)
//            this.mDataContentItems.clear();
        this.mDataContentItems.addAll(contentItems);
        notifyDataSetChanged();
    }

    public List<DataContentItem> getDataAdapter() {
        return mDataContentItems;
    }

    @Override
    public TopicDegreeAdapter.TopicDegreeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_content, parent, false);
        return new TopicDegreeHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicDegreeAdapter.TopicDegreeHolder holder, int position) {
        DataContentItem item = mDataContentItems.get(position);

        Glide.with(context)
                .load(item.getContentImage())
                .into(holder.mImageContent);
        holder.mTitleContent.setText(item.getContentTitle());
        holder.mTextDesc.setText(Html.fromHtml(item.getContentDesc()));
        holder.mTextDate.setText(item.getContentCreateDate());
    }

    @Override
    public int getItemCount() {
        return mDataContentItems != null ? mDataContentItems.size() : 0;
    }

    public class TopicDegreeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            DataContentItem item = mDataContentItems.get(position);
            listener.onItemClick(item);
        }
    }
}
