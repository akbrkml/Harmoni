package com.harmoni.harmonikeluarga.ui.fragment.journalism;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataJournalismItem;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;


import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailJournalismFragment extends BaseFragment {

    @BindView(R.id.img)ImageView mImageView;
    @BindView(R.id.title)TextView mTextTitle;
    @BindView(R.id.author)TextView mTextAuthor;
    @BindView(R.id.desc)TextView mTextDesc;
    private DataJournalismItem journalismItem;

    public DetailJournalismFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_journalism, container, false);

        ButterKnife.bind(this, view);

        getArgument();

        return view;
    }

//    @OnClick(R.id.btPlayVideo)
//    public void playVideo(){
//
//    }

    private void getArgument() {
        String dataJournalism = getArguments().getString("data_journalism");
        if (dataJournalism != null) {
            journalismItem = new Gson().fromJson(dataJournalism, DataJournalismItem.class);
        }
        bindData();
    }

    private void bindData() {
        Glide.with(getActivity()).load(journalismItem.getContentImage()).into(mImageView);
        mTextTitle.setText(journalismItem.getContentTitle());
        mTextAuthor.setText(journalismItem.getCustomerName());
        mTextDesc.setText(journalismItem.getContentDesc());
    }


    @Override
    protected String getTitle() {
        return null;
    }
}
