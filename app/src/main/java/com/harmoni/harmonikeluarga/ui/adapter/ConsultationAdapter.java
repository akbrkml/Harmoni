package com.harmoni.harmonikeluarga.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataConsultationItem;
import com.harmoni.harmonikeluarga.model.DataTopicItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akbar on 07/11/17.
 */

public class ConsultationAdapter extends RecyclerView.Adapter<ConsultationAdapter.ConsultationHolder> {

    private ArrayList<DataConsultationItem> mDataConsultationItems;
    private Context context;
    private OnItemClickListener listener;

    public ConsultationAdapter(Context context, OnItemClickListener listener) {
        this.mDataConsultationItems = new ArrayList<>();
        this.context = context;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(DataConsultationItem item);
    }

    public void setDataAdapter(List<DataConsultationItem> consultationItems){
        if (consultationItems == null || consultationItems.size() == 0)
            return;
        if (mDataConsultationItems != null && mDataConsultationItems.size() > 0)
            this.mDataConsultationItems.clear();
        this.mDataConsultationItems.addAll(consultationItems);
        notifyDataSetChanged();
    }

    public List<DataConsultationItem> getDataAdapter(){
        return mDataConsultationItems;
    }


    @Override
    public ConsultationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_consultation, parent, false);
        ConsultationHolder consultationHolder = new ConsultationHolder(view);
        return consultationHolder;
    }

    @Override
    public void onBindViewHolder(ConsultationHolder holder, int position) {
        DataConsultationItem item = mDataConsultationItems.get(position);

        holder.mTextTitle.setText(item.getConsultTitle());
        holder.mTextDesc.setText(item.getConsultAnswerText());
        holder.mTextStatus.setText(item.getConsultStatus());
        holder.mTextDate.setText(item.getConsultAnswerDate());
    }

    @Override
    public int getItemCount() {
        return mDataConsultationItems != null ? mDataConsultationItems.size():0;
    }

    public class ConsultationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title)TextView mTextTitle;
        @BindView(R.id.desc)TextView mTextDesc;
        @BindView(R.id.status)TextView mTextStatus;
        @BindView(R.id.date)TextView mTextDate;

        public ConsultationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
