package com.harmoni.harmonikeluarga.ui.fragment.event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataEventItem;
import com.harmoni.harmonikeluarga.model.EventJounalism;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.adapter.EventAdapter;
import com.harmoni.harmonikeluarga.ui.adapter.EventParticipantAdapter;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;
import com.medialablk.easytoast.EasyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.ui.fragment.event.DetailEventFragment.eventItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventParticipantFragment extends BaseFragment {

    @BindView(R.id.rvParticipant)RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.noData)TextView mTextMessage;
    private EventParticipantAdapter mAdapterParticipant;

    public EventParticipantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_participant, container, false);

        ButterKnife.bind(this,view);

        initRecycler();

        getListEventParticipant();

        return view;
    }

    private void initRecycler(){
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapterParticipant = new EventParticipantAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapterParticipant);
    }

    private void getListEventParticipant(){
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getListEventParticipant("participant", eventItem.getEventId(), new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                EventJounalism participant = (EventJounalism) response.body();
                if (participant != null){
                    if (participant.isStatus()){
                        mAdapterParticipant.setDataAdapter(participant.getDataParticipantItems());
                    } else {
                        mTextMessage.setVisibility(View.VISIBLE);
                        mTextMessage.setText(participant.getText());
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

    @Override
    protected String getTitle() {
        return null;
    }
}
