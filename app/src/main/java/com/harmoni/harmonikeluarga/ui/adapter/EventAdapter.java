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
import com.harmoni.harmonikeluarga.model.DataChildItem;
import com.harmoni.harmonikeluarga.model.DataEventItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akbar on 14/11/17.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private List<DataEventItem> mEventItems;
    private Context mContext;
    private OnItemClickListener listener;

    public EventAdapter(Context mContext, OnItemClickListener listener) {
        this.mEventItems = new ArrayList<>();
        this.mContext = mContext;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(DataEventItem item);
    }

    public void setDataAdapter(List<DataEventItem> eventItems) {
        if (eventItems == null || eventItems.size() == 0)
            return;
        if (mEventItems != null && mEventItems.size() > 0)
            this.mEventItems.clear();
        this.mEventItems.addAll(eventItems);
        notifyDataSetChanged();
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_event, parent, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        DataEventItem eventItem = mEventItems.get(position);

        holder.mTextTitle.setText(eventItem.getEventName());
        holder.mTextDesc.setText(Html.fromHtml(eventItem.getEventDesc()));
        holder.mTextDateFrom.setText(eventItem.getEventCreateDate());
        holder.mTextDateTo.setText(eventItem.getEventEndDate());
        Glide.with(mContext).load(eventItem.getEventImage()).into(holder.mImageBackground);
    }

    @Override
    public int getItemCount() {
        return mEventItems != null ? mEventItems.size() : 0;
    }

    class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img)
        ImageView mImageBackground;
        @BindView(R.id.title)
        TextView mTextTitle;
        @BindView(R.id.desc)
        TextView mTextDesc;
        @BindView(R.id.dateFrom)
        TextView mTextDateFrom;
        @BindView(R.id.dateTo)
        TextView mTextDateTo;

        EventHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            DataEventItem item = mEventItems.get(position);
            listener.onItemClick(item);
        }
    }

}
