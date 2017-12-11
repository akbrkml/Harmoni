package com.harmoni.harmonikeluarga.ui.fragment.content;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataContentItem;
import com.harmoni.harmonikeluarga.model.DataJournalismItem;
import com.harmoni.harmonikeluarga.model.DataTopicItem;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentDetailFragment extends BaseFragment {

    @BindView(R.id.img)ImageView mImageView;
    @BindView(R.id.title)TextView mTextTitle;
    @BindView(R.id.desc)TextView mTextDesc;
    private DataContentItem topicItem;

    public static ContentDetailFragment newInstance(){
        return new ContentDetailFragment();
    }

    public ContentDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content_detail, container, false);

        ButterKnife.bind(this, view);

        getArgument();

        return view;
    }

    private void getArgument() {
        String dataContent = getArguments().getString("data_content");
        if (dataContent != null) {
            topicItem = new Gson().fromJson(dataContent, DataContentItem.class);
        }
        bindData();
    }

    private void bindData() {
        Glide.with(getActivity()).load(topicItem.getContentImage()).into(mImageView);
        mTextTitle.setText(topicItem.getContentTitle());
        mTextDesc.setText(Html.fromHtml(topicItem.getContentDesc()));
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
