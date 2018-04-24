package com.harmoni.harmonikeluarga.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.harmoni.harmonikeluarga.model.DataContentItem;
import com.harmoni.harmonikeluarga.model.DataTopicItem;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.adapter.TopicDegreeAdapter;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;
import com.harmoni.harmonikeluarga.ui.fragment.content.ContentDetailFragment;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.util.Constant.getCustomerId;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends BaseFragment {

    @BindView(R.id.rvContent)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.noData)
    TextView mViewMessage;

    private TopicDegreeAdapter mAdapter;

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initRecyclerViews();
        getFavorites(getCustomerId(getActivity()));
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFavorites(getCustomerId(getActivity()));
            }
        });
    }

    private void getFavorites(String customerId) {
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getListFavorite("list_favorite", customerId, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                ContentChild content = (ContentChild) response.body();

                if (content != null) {
                    if (content.isStatus()) {
                        mAdapter.setDataAdapter(content.getDataContent());
                    } else {
                        mViewMessage.setVisibility(View.VISIBLE);
                        mViewMessage.setText(content.getText());
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initRecyclerViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new TopicDegreeAdapter(getActivity(), new TopicDegreeAdapter.OnItemClickListener() {
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

    @Override
    protected String getTitle() {
        return null;
    }
}
