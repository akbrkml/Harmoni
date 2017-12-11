package com.harmoni.harmonikeluarga.ui.fragment.content;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataContentItem;
import com.harmoni.harmonikeluarga.model.DataTopicItem;
import com.harmoni.harmonikeluarga.ui.adapter.TopicDegreeAdapter;
import com.harmoni.harmonikeluarga.ui.adapter.TopicListAdapter;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicListFragment extends BaseFragment {

    @BindView(R.id.rvContent)
    RecyclerView mRecyclerView;

    TopicListAdapter mAdapter;
    private DataTopicItem topicItem;

    public TopicListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic_list, container, false);

        ButterKnife.bind(this, view);

        initRecyclerViews();

        getArgument();

        return view;
    }

    private void initRecyclerViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new TopicListAdapter(getActivity(), new TopicListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataContentItem item) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                ContentDetailFragment newFragment = new ContentDetailFragment();
                Bundle bundle = new Bundle();
                String dataContent = new Gson().toJson(item, DataContentItem.class);
                bundle.putString("data_content", dataContent);
                newFragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.content_frame, newFragment).addToBackStack("tag").commit();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getArgument() {
        String dataContent = getArguments().getString("data_content");
        if (dataContent != null) {
            topicItem = new Gson().fromJson(dataContent, DataTopicItem.class);
        }
        bindData();
    }

    private void bindData(){
        mAdapter.setDataAdapter(topicItem.getDataContent());
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
