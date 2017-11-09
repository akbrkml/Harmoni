package com.harmoni.harmonikeluarga.ui.fragment.consultation;


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
import android.widget.TextView;

import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.Consultation;
import com.harmoni.harmonikeluarga.model.DataConsultationItem;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.adapter.ConsultationAdapter;
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
public class ConsultationListFragment extends BaseFragment {

    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.rvConsultation)RecyclerView mRecyclerView;
    @BindView(R.id.noData)TextView mDisplayMessage;
    private ConsultationAdapter mAdapter;

    public static ConsultationListFragment newInstance(){
        return new ConsultationListFragment();
    }

    public ConsultationListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultation_list, container, false);

        ButterKnife.bind(this, view);

        initRecyclerViews();

        getListConsultation();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListConsultation();
            }
        });

        return view;
    }

    private void initRecyclerViews(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ConsultationAdapter(getActivity(), new ConsultationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataConsultationItem item) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                ConsultationDetailFragment newFragment = new ConsultationDetailFragment();
                Bundle bundle = new Bundle();
                String dataConsultation = new Gson().toJson(item, DataConsultationItem.class);
                bundle.putString("data_consultation", dataConsultation);
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

    private void getListConsultation(){
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getListConsultation("list_consultation", getCustomerId(getActivity()), "0", new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                Consultation consultation = (Consultation) response.body();
                if (consultation != null){
                    if (consultation.isStatus()){
                        mAdapter.setDataAdapter(consultation.getDataConsultation());
                    } else {
                        mDisplayMessage.setVisibility(View.VISIBLE);
                        mDisplayMessage.setText(consultation.getText());
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                mRefreshLayout.setRefreshing(false);
                mDisplayMessage.setVisibility(View.VISIBLE);
                mDisplayMessage.setText(R.string.message_failure);
            }
        });
    }
}
