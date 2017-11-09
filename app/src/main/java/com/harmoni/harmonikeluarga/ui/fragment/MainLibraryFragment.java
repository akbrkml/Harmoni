package com.harmoni.harmonikeluarga.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainLibraryFragment extends BaseFragment {

    @BindView(R.id.slider_layout)SliderLayout mSliderLayout;

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

        return view;
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
