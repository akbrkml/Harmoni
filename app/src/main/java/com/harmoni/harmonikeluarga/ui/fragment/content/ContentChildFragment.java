package com.harmoni.harmonikeluarga.ui.fragment.content;


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
import android.widget.Button;

import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.Child;
import com.harmoni.harmonikeluarga.model.DataChildItem;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.adapter.ChildAdapter;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;
import com.harmoni.harmonikeluarga.ui.fragment.profile.AddChildFragment;
import com.medialablk.easytoast.EasyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.util.Constant.getCustomerId;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentChildFragment extends BaseFragment {

    @BindView(R.id.rvChild)RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.btn_add_child)Button mButton;

    private ChildAdapter mAdapter;
    private Child child;

    public static ContentChildFragment newInstance(){
        return new ContentChildFragment();
    }

    public ContentChildFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content_child, container, false);

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
                FragmentManager fm = getActivity().getSupportFragmentManager();
                ContentTopicFragment newFragment = new ContentTopicFragment();
                Bundle bundle = new Bundle();
                String dataChild = new Gson().toJson(item, DataChildItem.class);
                bundle.putString("data_child", dataChild);
                newFragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.content_frame, newFragment).addToBackStack("tag").commit();
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
                        mButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AddChildFragment addChildFragment = new AddChildFragment();
                                addChildFragment.show(getActivity().getSupportFragmentManager(), null);
                            }
                        });
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
