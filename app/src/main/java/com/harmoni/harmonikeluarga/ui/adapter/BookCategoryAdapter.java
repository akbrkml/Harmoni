package com.harmoni.harmonikeluarga.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataBookItem;
import com.harmoni.harmonikeluarga.model.DataCategoryItem;
import com.harmoni.harmonikeluarga.model.DataEventItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akbar on 02/12/17.
 */

public class BookCategoryAdapter extends RecyclerView.Adapter<BookCategoryAdapter.BookCategoryHolder> {

    private List<DataCategoryItem> mDataCategories;
    private Context mContext;
    private OnItemClickListener listener;

    public BookCategoryAdapter(Context mContext, OnItemClickListener listener) {
        this.listener = listener;
        this.mDataCategories = new ArrayList<>();
        this.mContext = mContext;
    }

    public interface OnItemClickListener {
        void onItemClick(DataCategoryItem item);
    }

    public void setDataAdapter(List<DataCategoryItem> categoryItems) {
        if (categoryItems == null || categoryItems.size() == 0)
            return;
        if (mDataCategories != null && mDataCategories.size() > 0)
            this.mDataCategories.clear();
        this.mDataCategories.addAll(categoryItems);
        notifyDataSetChanged();
    }

    @Override
    public BookCategoryAdapter.BookCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_category_book, parent, false);
        return new BookCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(BookCategoryAdapter.BookCategoryHolder holder, int position) {
        DataCategoryItem item = mDataCategories.get(position);

        holder.mTextTitle.setText(item.getCatName());
    }

    @Override
    public int getItemCount() {
        return mDataCategories != null ? mDataCategories.size() : 0;
    }

    public class BookCategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title)TextView mTextTitle;

        public BookCategoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            DataCategoryItem item = mDataCategories.get(position);
            listener.onItemClick(item);
        }
    }
}
