package com.harmoni.harmonikeluarga.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataParticipantItem;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akbar on 02/12/17.
 */

public class EventParticipantWinnerAdapter extends RecyclerView.Adapter<EventParticipantWinnerAdapter.EventParticipantWinnerHolder> {

    private Context mContext;
    private List<DataParticipantItem> mParticipantItems;

    public EventParticipantWinnerAdapter(Context mContext) {
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
    public EventParticipantWinnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_participant_winner, parent, false);
        return new EventParticipantWinnerHolder(view);
    }

    @Override
    public void onBindViewHolder(EventParticipantWinnerHolder holder, int position) {
        DataParticipantItem item = mParticipantItems.get(position);

        holder.mTextUser.setText(item.getCustomerName());
        holder.mTextWinnerTitle.setText(item.getEmWinnerTitle());
        holder.mTextWinnerDesc.setText(item.getEmWinnerDesc());
        Glide.with(mContext).load(item.getCustomerImage()).into(holder.mImageUser);
    }

    @Override
    public int getItemCount() {
        return mParticipantItems != null ? mParticipantItems.size() : 0;
    }

    public class EventParticipantWinnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img)CircularImageView mImageUser;
        @BindView(R.id.nameUser)TextView mTextUser;
        @BindView(R.id.winnerTitle)TextView mTextWinnerTitle;
        @BindView(R.id.winner_desc)TextView mTextWinnerDesc;

        public EventParticipantWinnerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
