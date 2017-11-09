package com.harmoni.harmonikeluarga.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventJournalismFragment extends BaseFragment {

    @BindView(R.id.rvEvent)RecyclerView mRecyclerViewEvent;
    @BindView(R.id.rvJournalism)RecyclerView mRecyclerViewJournalism;
    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;

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

        return view;
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
