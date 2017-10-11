package com.harmoni.harmonikeluarga.ui.fragment;


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
public class MainLibraryFragment extends BaseFragment {

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
        return inflater.inflate(R.layout.fragment_main_library, container, false);
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
