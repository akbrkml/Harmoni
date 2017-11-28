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
import com.harmoni.harmonikeluarga.model.DataJournalismItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akbar on 23/11/17.
 */

public class JournalismAdapter extends RecyclerView.Adapter<JournalismAdapter.JournalismHolder> {

    private List<DataJournalismItem> mJournalismItems;
    private Context mContext;
    private OnItemClickListener listener;

    public JournalismAdapter(Context mContext, OnItemClickListener listener) {
        this.mContext = mContext;
        this.listener = listener;
        this.mJournalismItems = new ArrayList<>();
    }

    public interface OnItemClickListener {
        void onItemClick(DataJournalismItem item);
    }

    public void setDataAdapter(List<DataJournalismItem> journalismItems) {
        if (journalismItems == null || journalismItems.size() == 0)
            return;
        if (mJournalismItems != null && mJournalismItems.size() > 0)
            this.mJournalismItems.clear();
        this.mJournalismItems.addAll(journalismItems);
        notifyDataSetChanged();
    }

    @Override
    public JournalismHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_journalism, null);
        return new JournalismHolder(view);
    }

    @Override
    public void onBindViewHolder(JournalismHolder holder, int position) {
        DataJournalismItem journalismItem = mJournalismItems.get(position);

        holder.mTextTitle.setText(journalismItem.getContentTitle());
        holder.mTextDesc.setText(journalismItem.getContentDesc());
        holder.mTextDate.setText(journalismItem.getContentCreateDate());
        Glide.with(mContext).load(journalismItem.getContentImage()).into(holder.mImageBackground);
    }

    @Override
    public int getItemCount() {
        return mJournalismItems != null ? mJournalismItems.size() : 0;
    }

    class JournalismHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img)
        ImageView mImageBackground;
        @BindView(R.id.title)
        TextView mTextTitle;
        @BindView(R.id.desc)
        TextView mTextDesc;
        @BindView(R.id.date)
        TextView mTextDate;

        JournalismHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            DataJournalismItem item = mJournalismItems.get(adapterPosition);
            listener.onItemClick(item);
        }
    }
}
