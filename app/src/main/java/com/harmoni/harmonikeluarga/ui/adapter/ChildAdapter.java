package com.harmoni.harmonikeluarga.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by akbar on 25/10/17.
 */

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildHolder> {
    @Override
    public ChildAdapter.ChildHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ChildAdapter.ChildHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ChildHolder extends RecyclerView.ViewHolder {

        public ChildHolder(View itemView) {
            super(itemView);
        }
    }
}
