package com.harmoni.harmonikeluarga.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harmoni.harmonikeluarga.R;
import com.medialablk.easytoast.EasyToast;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.bgHome1)
    public void goToBeranda(){
        EasyToast.info(getActivity(), "test");
    }

    @OnClick(R.id.bgHome2)
    public void goToAhli(){
        EasyToast.info(getActivity(), "test");
    }

    @OnClick(R.id.bgHome3)
    public void goToInspirasi(){
        EasyToast.info(getActivity(), "test");
    }

    @OnClick(R.id.bgHome4)
    public void goToPustaka(){
        EasyToast.info(getActivity(), "test");
    }

}
