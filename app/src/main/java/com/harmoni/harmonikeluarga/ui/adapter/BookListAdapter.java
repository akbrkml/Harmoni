package com.harmoni.harmonikeluarga.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataBookItem;
import com.harmoni.harmonikeluarga.model.DataContentItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by akbar on 02/12/17.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListHolder> {

    private Context mContext;
    private List<DataBookItem> mDataBookItems;
    private OnItemClickListener listener;

    public BookListAdapter(Context mContext, OnItemClickListener listener) {
        this.mContext = mContext;
        this.listener = listener;
        this.mDataBookItems = new ArrayList<>();
    }

    public interface OnItemClickListener {
        void onItemClick(DataBookItem item);
    }

    public void setDataAdapter(List<DataBookItem> bookItems) {
        if (bookItems == null || bookItems.size() == 0)
            return;
        if (mDataBookItems != null && mDataBookItems.size() > 0)
            this.mDataBookItems.clear();
        this.mDataBookItems.addAll(bookItems);
        notifyDataSetChanged();
    }

    @Override
    public BookListAdapter.BookListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_book, parent, false);
        return new BookListHolder(view);
    }

    @Override
    public void onBindViewHolder(BookListAdapter.BookListHolder holder, int position) {
        DataBookItem item = mDataBookItems.get(position);

        holder.mTextTitle.setText(item.getBookTitle());
        holder.mTextDesc.setText(Html.fromHtml(item.getBookDesc()));
        Glide.with(mContext).load(item.getBookImage()).into(holder.mImageBook);
    }

    @Override
    public int getItemCount() {
        return mDataBookItems != null ? mDataBookItems.size() : 0;
    }

    public class BookListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)TextView mTextTitle;
        @BindView(R.id.desc)TextView mTextDesc;
        @BindView(R.id.img)ImageView mImageBook;

        public BookListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.download)
        public void goDownload(){
            int position = getAdapterPosition();
            DataBookItem item = mDataBookItems.get(position);
            listener.onItemClick(item);
        }
    }
}
