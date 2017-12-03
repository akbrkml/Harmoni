package com.harmoni.harmonikeluarga.ui.fragment.event;


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
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.EventJounalism;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.adapter.EventParticipantAdapter;
import com.harmoni.harmonikeluarga.ui.adapter.EventParticipantWinnerAdapter;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.ui.fragment.event.DetailEventFragment.eventItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventParticipantWinnerFragment extends BaseFragment {

    @BindView(R.id.rvParticipant)RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.noData)TextView mTextMessage;
    private EventParticipantWinnerAdapter mAdapter;

    public EventParticipantWinnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_participant_winner, container, false);

        ButterKnife.bind(this, view);

        initRecycler();

        getListEventParticipantWinner();

        return view;
    }

    private void initRecycler(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new EventParticipantWinnerAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }


    private void getListEventParticipantWinner(){
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getListEventParticipantWinner("participant_winner", eventItem.getEventId(), new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                EventJounalism winner = (EventJounalism) response.body();
                if (winner != null){
                    if (winner.isStatus()){
                        mAdapter.setDataAdapter(winner.getDataParticipantItems());
                    } else {
                        mTextMessage.setVisibility(View.VISIBLE);
                        mTextMessage.setText(winner.getText());
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
