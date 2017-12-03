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
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.Book;
import com.harmoni.harmonikeluarga.model.DataBookItem;
import com.harmoni.harmonikeluarga.model.DataCategoryItem;
import com.harmoni.harmonikeluarga.model.DataJournalismItem;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.adapter.BookCategoryAdapter;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;
import com.harmoni.harmonikeluarga.ui.fragment.journalism.DetailJournalismFragment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainLibraryFragment extends BaseFragment {

    @BindView(R.id.slider_layout)SliderLayout mSliderLayout;
    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.rvBook)RecyclerView mRecyclerView;
    @BindView(R.id.noData)TextView mTextMessage;
    private BookCategoryAdapter mAdapter;

    public static MainLibraryFragment newInstance(){
        return new MainLibraryFragment();
    }

    public MainLibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_library, container, false);

        ButterKnife.bind(this, view);

        getBooks();

        initRecycler();

        getBookCategory();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBookCategory();
            }
        });

        return view;
    }

    private void initRecycler(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new BookCategoryAdapter(getActivity(), new BookCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataCategoryItem item) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                BooksFragment newFragment = new BooksFragment();
                Bundle bundle = new Bundle();
                String catId = item.getCatId();
                bundle.putString("cat_id", catId);
                newFragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.container, newFragment).addToBackStack("tag").commit();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getBookCategory(){
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getBookCategory("get_book_cat", "book", new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                Book book = (Book) response.body();
                if (book != null){
                    mAdapter.setDataAdapter(book.getDataCategory());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getBooks(){
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getBookCategory("get_book_cat", "book", new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                Book book = (Book) response.body();
                if (book != null){
                    HashMap<String,String> books = new HashMap<String, String>();
                    int size = book.getDataBook().size();
                    for (int i = 0; i < size; i++){
                        books.put(book.getDataBook().get(i).getBookTitle(), book.getDataBook().get(i).getBookImage());
                    }

                    for(String name : books.keySet()){
                        TextSliderView textSliderView = new TextSliderView(getActivity());
                        // initialize a SliderLayout
                        textSliderView
                                .description(name)
                                .image(books.get(name))
                                .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                    @Override
                                    public void onSliderClick(BaseSliderView slider) {
                                    }
                                });

                        //add your extra information
                        textSliderView.bundle(new Bundle());
                        textSliderView.getBundle()
                                .putString("extra",name);

                        mSliderLayout.addSlider(textSliderView);
                    }

                    mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
                    mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                    mSliderLayout.setCustomAnimation(new DescriptionAnimation());
                    mSliderLayout.setDuration(4000);
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
