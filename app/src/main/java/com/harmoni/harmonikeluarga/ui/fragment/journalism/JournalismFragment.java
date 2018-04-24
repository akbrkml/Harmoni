package com.harmoni.harmonikeluarga.ui.fragment.journalism;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataJournalismItem;
import com.harmoni.harmonikeluarga.model.EventJounalism;
import com.harmoni.harmonikeluarga.network.APIService;
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
public class JournalismFragment extends BaseFragment {

    @BindView(R.id.rvJournalism)RecyclerView mRecyclerViewJournalism;
    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;
    private JournalismAdapter mAdapterJournalism;

    public static JournalismFragment newInstance(){
        return new JournalismFragment();
    }

    public JournalismFragment() {
        // Required empty public constructor
        // add journalism
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_journalism, container, false);

        ButterKnife.bind(this, view);

        initRecycler();

        getJournalism();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getJournalism();
            }
        });

        return view;
    }

    private void initRecycler(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewJournalism.setLayoutManager(linearLayoutManager);
        mRecyclerViewJournalism.setItemAnimator(new DefaultItemAnimator());
        mAdapterJournalism = new JournalismAdapter(getActivity(), new JournalismAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataJournalismItem item) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DetailJournalismFragment newFragment = new DetailJournalismFragment();
                Bundle bundle = new Bundle();
                String dataJournalism = new Gson().toJson(item, DataJournalismItem.class);
                bundle.putString("data_journalism", dataJournalism);
                newFragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.content_frame, newFragment).addToBackStack("tag").commit();
            }
        });
        mRecyclerViewJournalism.setAdapter(mAdapterJournalism);
    }

    private void getJournalism(){
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getListJournalism("list_journalism", new Callback() {
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

    @OnClick(R.id.add_journalism)
    public void addJournalism(){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        AddContentFragment addContentFragment = new AddContentFragment();
        fm.beginTransaction().replace(R.id.content_frame, addContentFragment).addToBackStack("tag").commit();
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
