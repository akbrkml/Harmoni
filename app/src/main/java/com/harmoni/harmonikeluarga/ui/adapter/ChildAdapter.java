package com.harmoni.harmonikeluarga.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataChildItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akbar on 25/10/17.
 */

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildHolder> {

    private ArrayList<DataChildItem> mDataChildItems;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(DataChildItem item);
    }

    public ChildAdapter(Context context, OnItemClickListener listener) {
        this.listener = listener;
        this.mDataChildItems = new ArrayList<>();
        this.context = context;
    }

    public void setDataAdapter(List<DataChildItem> childItems){
        if (childItems == null || childItems.size() == 0)
            return;
        if (mDataChildItems!= null && mDataChildItems.size() > 0)
            this.mDataChildItems.clear();
        this.mDataChildItems.addAll(childItems);
        notifyDataSetChanged();
    }

    public List<DataChildItem> getDataAdapter(){
        return mDataChildItems;
    }

    @Override
    public ChildAdapter.ChildHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_child, parent, false);
        ChildHolder childHolder = new ChildHolder(view);
        return childHolder;
    }

    @Override
    public void onBindViewHolder(ChildAdapter.ChildHolder holder, int position) {
        DataChildItem item = mDataChildItems.get(position);

        holder.mTvName.setText(item.getCcName());
        holder.mTvAge.setText(item.getCcAge());
        holder.mTvNumber.setText("Anak ke " + item.getCcNumber());
        holder.mTvDegree.setText(item.getDegreeName());

        if (item.getCcGender().equals("Laki-laki")){
            holder.mIvGender.setImageResource(R.drawable.ic_male);
        } else if (item.getCcGender().equals("Perempuan")){
            holder.mIvGender.setImageResource(R.drawable.ic_female);
        }
    }

    @Override
    public int getItemCount() {
        return mDataChildItems != null ? mDataChildItems.size():0;
    }

    public class ChildHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.name)TextView mTvName;
        @BindView(R.id.age)TextView mTvAge;
        @BindView(R.id.number)TextView mTvNumber;
        @BindView(R.id.degree)TextView mTvDegree;
        @BindView(R.id.icGender)ImageView mIvGender;

        public ChildHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            DataChildItem item = mDataChildItems.get(adapterPosition);
            listener.onItemClick(item);
        }
    }
}
