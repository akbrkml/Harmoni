package com.harmoni.harmonikeluarga.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataEventItem;
import com.harmoni.harmonikeluarga.model.DataParticipantItem;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akbar on 02/12/17.
 */

public class EventParticipantAdapter extends RecyclerView.Adapter<EventParticipantAdapter.EventParticipantHolder> {

    private List<DataParticipantItem> mParticipantItems;
    private Context mContext;

    public EventParticipantAdapter(Context mContext) {
        this.mParticipantItems = new ArrayList<>();
        this.mContext = mContext;
    }

    public void setDataAdapter(List<DataParticipantItem> participantItems) {
        if (participantItems == null || participantItems.size() == 0)
            return;
        if (mParticipantItems != null && mParticipantItems.size() > 0)
            this.mParticipantItems.clear();
        this.mParticipantItems.addAll(participantItems);
        notifyDataSetChanged();
    }

    @Override
    public EventParticipantAdapter.EventParticipantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_participant, null);
        return new EventParticipantHolder(view);
    }

    @Override
    public void onBindViewHolder(EventParticipantAdapter.EventParticipantHolder holder, int position) {
        DataParticipantItem item = mParticipantItems.get(position);

        holder.mTextUser.setText(item.getCustomerName());
        Glide.with(mContext).load(item.getCustomerImage()).into(holder.mImageUser);
    }

    @Override
    public int getItemCount() {
        return mParticipantItems != null ? mParticipantItems.size() : 0;
    }

    public class EventParticipantHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img)CircularImageView mImageUser;
        @BindView(R.id.nameUser)TextView mTextUser;

        public EventParticipantHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
