package com.harmoni.harmonikeluarga.ui.fragment.content;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.ContentChild;
import com.harmoni.harmonikeluarga.model.DataChildItem;
import com.harmoni.harmonikeluarga.model.DataTopicItem;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.adapter.TopicDegreeAdapter;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.util.Constant.getCustomerId;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentTopicFragment extends BaseFragment {

    @BindView(R.id.rvContent)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.noData)
    TextView mViewMessage;

    private TopicDegreeAdapter mAdapter;
    private DataChildItem childItem;

    public static ContentTopicFragment newInstance() {
        return new ContentTopicFragment();
    }

    public ContentTopicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content_topic, container, false);

        ButterKnife.bind(this, view);

        initRecyclerViews();

        getArgument();

        getTopicByDegree(childItem.getDegreeId(), getCustomerId(getActivity()));

        return view;
    }

    private void getArgument() {
        String degreeId = getArguments().getString("data_child");
        if (degreeId != null) {
            childItem = new Gson().fromJson(degreeId, DataChildItem.class);
        }
    }

    private void initRecyclerViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new TopicDegreeAdapter(getActivity(), new TopicDegreeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataTopicItem item) {
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getTopicByDegree(String degreeId, String customerId) {
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getListByDegree("list_by_degree_new", degreeId, customerId, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                ContentChild content = (ContentChild) response.body();

                if (content != null) {
                    int size = content.getDataTopic().size();
                    for (int i = 0; i < size; i++) {
                        mAdapter.setDataAdapter(content.getDataTopic().get(i).getDataContent());
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
