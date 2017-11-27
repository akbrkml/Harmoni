package com.harmoni.harmonikeluarga.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataJournalismItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akbar on 23/11/17.
 */

public class JournalismAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    public void setDataAdapter(List<DataJournalismItem> journalismItems){
        if (journalismItems == null || journalismItems.size() == 0)
            return;
        if (mJournalismItems!= null && mJournalismItems.size() > 0)
            this.mJournalismItems.clear();
        this.mJournalismItems.addAll(journalismItems);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 0:
                view = View.inflate(mContext, R.layout.item_journalism_highlight, null);
                viewHolder = new JournalismHolderA(view);
                break;
            case 1:
                view = View.inflate(mContext, R.layout.item_journalism, null);
                viewHolder = new JournalismHolderB(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                JournalismHolderA journalismA = (JournalismHolderA) holder;
                journalismA.mTextTitle.setText(mJournalismItems.get(0).getContentTitle());
                journalismA.mTextDesc.setText(mJournalismItems.get(0).getContentDesc());
                break;
            case 1:
                JournalismHolderB journalismB = (JournalismHolderB) holder;
                journalismB.mTextTitle.setText(mJournalismItems.get(1).getContentTitle());
                journalismB.mTextDesc.setText(mJournalismItems.get(1).getContentDesc());
                journalismB.mTextDate.setText(mJournalismItems.get(1).getContentCreateDate());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mJournalismItems != null ? mJournalismItems.size():0;
    }

    @Override
    public int getItemViewType(int position) {
        return  mJournalismItems.size() == 1 ? 0 : 1;
    }

    class JournalismHolderA extends RecyclerView.ViewHolder {

        @BindView(R.id.img)ImageView mImageBackground;
        @BindView(R.id.title)TextView mTextTitle;
        @BindView(R.id.desc)TextView mTextDesc;

        JournalismHolderA(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class JournalismHolderB extends RecyclerView.ViewHolder {

        @BindView(R.id.img)ImageView mImageBackground;
        @BindView(R.id.title)TextView mTextTitle;
        @BindView(R.id.desc)TextView mTextDesc;
        @BindView(R.id.date)TextView mTextDate;

        JournalismHolderB(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
