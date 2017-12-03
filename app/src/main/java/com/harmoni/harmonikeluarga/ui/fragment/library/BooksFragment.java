package com.harmoni.harmonikeluarga.ui.fragment.library;


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
import com.harmoni.harmonikeluarga.model.Book;
import com.harmoni.harmonikeluarga.model.DataBookItem;
import com.harmoni.harmonikeluarga.model.DataCategoryItem;
import com.harmoni.harmonikeluarga.model.DataJournalismItem;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.adapter.BookCategoryAdapter;
import com.harmoni.harmonikeluarga.ui.adapter.BookListAdapter;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BooksFragment extends BaseFragment {

    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.rvBook)RecyclerView mRecyclerView;
    @BindView(R.id.noData)TextView mTextMessage;
    private String catId;
    private BookListAdapter mAdapter;

    public BooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        ButterKnife.bind(this, view);

        getArgument();

        initRecycler();

        getBookByCat();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBookByCat();
            }
        });

        return view;
    }

    private void initRecycler(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new BookListAdapter(getActivity(), new BookListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataBookItem item) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DetailBookFragment newFragment = new DetailBookFragment();
                Bundle bundle = new Bundle();
                String dataBook = new Gson().toJson(item, DataBookItem.class);
                bundle.putString("data_book", dataBook);
                newFragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.content_frame, newFragment).addToBackStack("tag").commit();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getArgument() {
        String cat_id = getArguments().getString("cat_id");
        if (cat_id != null) {
            catId = cat_id;
        }
    }

    private void getBookByCat(){
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getBookByCat("list_by_cat", catId, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                Book book = (Book) response.body();
                if (book != null){
                    if (book.isStatus()){
                        mAdapter.setDataAdapter(book.getDataBook());
                    } else {
                        mTextMessage.setVisibility(View.VISIBLE);
                        mTextMessage.setText(book.getText());
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
