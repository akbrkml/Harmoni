package com.harmoni.harmonikeluarga.ui.fragment.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.Topic;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.adapter.TopicAdapter;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;
import com.medialablk.easytoast.EasyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.ui.fragment.profile.ProfileFragment.customerId;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends BaseFragment {

    @BindView(R.id.rvTopic)RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;

    private TopicAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    public static TopicFragment newInstance(){
        return new TopicFragment();
    }

    public TopicFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic, container, false);

        ButterKnife.bind(this, view);

        initRecyclerViews();

        getDataTopic();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataTopic();
            }
        });

        return view;
    }

    private void initRecyclerViews(){
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new TopicAdapter(getActivity());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected String getTitle() {
        return null;
    }

    private void getDataTopic(){
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getTopic("list_topic", customerId, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                Topic topic = (Topic) response.body();
                if (topic != null){
                    if (topic.isStatus()){
                        adapter.setDataAdapter(topic.getDataTopic());
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                mRefreshLayout.setRefreshing(false);
                EasyToast.error(getActivity(), "Koneksi error");
            }
        });
    }
}
