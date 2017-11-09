package com.harmoni.harmonikeluarga.ui.fragment.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataTopicItem;
import com.harmoni.harmonikeluarga.model.Topic;
import com.harmoni.harmonikeluarga.model.User;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.adapter.TopicAdapter;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;
import com.harmoni.harmonikeluarga.util.SessionManager;
import com.medialablk.easytoast.EasyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.util.Constant.USER_SESSION;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends BaseFragment {

    @BindView(R.id.rvTopic)RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;

    private TopicAdapter adapter;
    private String customerId;

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

        getSession();

        getDataTopic();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataTopic();
            }
        });

        return view;
    }

    private void getSession(){
        User user = SessionManager.getUser(getActivity(), USER_SESSION);
        if (user != null) {
            customerId = user.getDataUser().getCustomerId();
        }
    }

    private void initRecyclerViews(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new TopicAdapter(getActivity(), new TopicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataTopicItem item) {
                addTopic(item.getTopicId());
            }
        });
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

    private void addTopic(String topicId){
        APIService apiService = new APIService();
        apiService.addTopic("add_topic", customerId, topicId, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Topic topic = (Topic) response.body();
                if (topic != null){
                    if (topic.isStatus()){
                        getDataTopic();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                EasyToast.error(getActivity(), "Koneksi error");
            }
        });
    }

}
