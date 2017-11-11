package com.harmoni.harmonikeluarga.ui.fragment.consultation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.Child;
import com.harmoni.harmonikeluarga.model.DataChildItem;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.adapter.ChildAdapter;
import com.harmoni.harmonikeluarga.ui.fragment.content.ContentTopicFragment;
import com.medialablk.easytoast.EasyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.ui.fragment.consultation.ConsultationFormFragment.childId;
import static com.harmoni.harmonikeluarga.ui.fragment.consultation.ConsultationFormFragment.mTextUmur;
import static com.harmoni.harmonikeluarga.util.Constant.getCustomerId;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class SetChildFragment extends DialogFragment {

    @BindView(R.id.rvChild)RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.btn_add_child)Button mButton;

    private ChildAdapter mAdapter;
    private Child child;

    public SetChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_set_child, container, false);

        ButterKnife.bind(this, view);

        initRecyclerViews();

        getDataChild();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataChild();
            }
        });


        return view;
    }

    private void initRecyclerViews(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ChildAdapter(getActivity(), new ChildAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataChildItem item) {
                mTextUmur.setText(item.getCcAge());
                childId = item.getCcId();
                getDialog().dismiss();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getDataChild(){
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getChild("customer_child", getCustomerId(getActivity()), new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                child = (Child) response.body();
                if (child != null){
                    if (child.isStatus()){
                        mAdapter.setDataAdapter(child.getDataChild());
                    } else {
                        mButton.setVisibility(View.VISIBLE);
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
