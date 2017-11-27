package com.harmoni.harmonikeluarga.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

public class EventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    public void setDataAdapter(List<DataEventItem> eventItems){
        if (eventItems == null || eventItems.size() == 0)
            return;
        if (mEventItems!= null && mEventItems.size() > 0)
            this.mEventItems.clear();
        this.mEventItems.addAll(eventItems);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 0:
                view = View.inflate(mContext, R.layout.item_event_highlight, null);
                viewHolder = new EventHolderA(view);
                break;
            case 1:
                view = View.inflate(mContext, R.layout.item_event, null);
                viewHolder = new EventHolderB(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                EventHolderA eventHolderA = (EventHolderA) holder;
                eventHolderA.mTextTitle.setText(mEventItems.get(0).getEventName());
                eventHolderA.mTextDateFrom.setText(mEventItems.get(0).getEventCreateDate());
                eventHolderA.mTextDateTo.setText(mEventItems.get(0).getEventEndDate());
                break;
            case 1:
                EventHolderB eventHolderB = (EventHolderB) holder;
                eventHolderB.mTextTitle.setText(mEventItems.get(1).getEventName());
                eventHolderB.mTextDesc.setText(mEventItems.get(1).getEventDesc());
                eventHolderB.mTextDateFrom.setText(mEventItems.get(1).getEventCreateDate());
                eventHolderB.mTextDateTo.setText(mEventItems.get(1).getEventEndDate());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mEventItems != null ? mEventItems.size():0;
    }

    @Override
    public int getItemViewType(int position) {
        return  mEventItems.size() == 1 ? 0 : 1;
    }

    class EventHolderA extends RecyclerView.ViewHolder {

        @BindView(R.id.img)ImageView mImageBackground;
        @BindView(R.id.title)TextView mTextTitle;
        @BindView(R.id.dateFrom)TextView mTextDateFrom;
        @BindView(R.id.dateTo)TextView mTextDateTo;

        EventHolderA(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class EventHolderB extends RecyclerView.ViewHolder {

        @BindView(R.id.img)ImageView mImageBackground;
        @BindView(R.id.title)TextView mTextTitle;
        @BindView(R.id.desc)TextView mTextDesc;
        @BindView(R.id.dateFrom)TextView mTextDateFrom;
        @BindView(R.id.dateTo)TextView mTextDateTo;

        EventHolderB(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
