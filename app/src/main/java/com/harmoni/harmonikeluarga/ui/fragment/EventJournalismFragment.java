package com.harmoni.harmonikeluarga.ui.fragment;


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
import com.harmoni.harmonikeluarga.model.DataEventItem;
import com.harmoni.harmonikeluarga.model.DataJournalismItem;
import com.harmoni.harmonikeluarga.model.EventJounalism;
import com.harmoni.harmonikeluarga.model.User;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.adapter.EventAdapter;
import com.harmoni.harmonikeluarga.ui.adapter.JournalismAdapter;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventJournalismFragment extends BaseFragment {

    @BindView(R.id.rvEvent)RecyclerView mRecyclerViewEvent;
    @BindView(R.id.rvJournalism)RecyclerView mRecyclerViewJournalism;
    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;
    private EventAdapter mAdapterEvent;
    private JournalismAdapter mAdapterJournalism;

    public static EventJournalismFragment newInstance(){
        return new EventJournalismFragment();
    }

    public EventJournalismFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_journalism, container, false);

        ButterKnife.bind(this, view);

        initEventRecycler();

        initJournalismRecycler();

        getEvent();

        getJournalism();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getEvent();
                getJournalism();
            }
        });

        return view;
    }

    private void initEventRecycler(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewEvent.setLayoutManager(linearLayoutManager);
        mRecyclerViewEvent.setItemAnimator(new DefaultItemAnimator());
        mAdapterEvent = new EventAdapter(getActivity(), new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataEventItem item) {

            }
        });
        mRecyclerViewEvent.setAdapter(mAdapterEvent);
    }

    private void initJournalismRecycler(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewJournalism.setLayoutManager(linearLayoutManager);
        mRecyclerViewJournalism.setItemAnimator(new DefaultItemAnimator());
        mAdapterJournalism = new JournalismAdapter(getActivity(), new JournalismAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataJournalismItem item) {

            }
        });
        mRecyclerViewJournalism.setAdapter(mAdapterEvent);
    }

    private void getEvent(){
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getListEventJournalism("list_event_journalism", new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                EventJounalism event = (EventJounalism) response.body();
                if (event.isStatus()){
                    mAdapterEvent.setDataAdapter(event.getDataEvent());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getJournalism(){
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getListEventJournalism("list_event_journalism", new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                EventJounalism event = (EventJounalism) response.body();
                if (event.isStatus()){
                    mAdapterJournalism.setDataAdapter(event.getDataJournalism());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                mRefreshLayout.setRefreshing(false);
            }
        });
    }


    @OnClick(R.id.btMoreEvent)
    public void moreEvent(){

    }

    @OnClick(R.id.btMoreJournalizm)
    public void moreJournalism(){

    }

    @OnClick(R.id.add_journalism)
    public void addJournalism(){

    }

    @Override
    protected String getTitle() {
        return null;
    }
}
