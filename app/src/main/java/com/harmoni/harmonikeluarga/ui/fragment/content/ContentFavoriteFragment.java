package com.harmoni.harmonikeluarga.ui.fragment.content;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFavoriteFragment extends BaseFragment {

    public static ContentFavoriteFragment newInstance(){
        return new ContentFavoriteFragment();
    }

    public ContentFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content_favorite, container, false);
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
