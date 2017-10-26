package com.harmoni.harmonikeluarga.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataTopicItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akbar on 25/10/17.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicHolder> {

    private ArrayList<DataTopicItem> mDataTopicItems;
    private Context context;

    public TopicAdapter(Context context) {
        this.mDataTopicItems = new ArrayList<>();
        this.context = context;
    }

    public void setDataAdapter(List<DataTopicItem> topicItems){
        if (topicItems == null || topicItems.size() == 0)
            return;
        if (mDataTopicItems != null && mDataTopicItems.size() > 0)
            this.mDataTopicItems.clear();
        this.mDataTopicItems.addAll(topicItems);
        notifyDataSetChanged();
    }

    public List<DataTopicItem> getDataAdapter(){
        return mDataTopicItems;
    }

    @Override
    public TopicAdapter.TopicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_topic, parent, false);
        TopicHolder topicHolder = new TopicHolder(view);
        return topicHolder;
    }

    @Override
    public void onBindViewHolder(TopicAdapter.TopicHolder holder, int position) {
        DataTopicItem item = mDataTopicItems.get(position);

        holder.mTextView.setText(item.getTopicName());
    }

    @Override
    public int getItemCount() {
        return mDataTopicItems != null ? mDataTopicItems.size():0;
    }

    public class TopicHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)TextView mTextView;

        public TopicHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
