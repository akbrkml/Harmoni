package com.harmoni.harmonikeluarga.ui.fragment.content;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentDetailFragment extends BaseFragment {

    public static ContentDetailFragment newInstance(){
        return new ContentDetailFragment();
    }

    public ContentDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content_detail, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
