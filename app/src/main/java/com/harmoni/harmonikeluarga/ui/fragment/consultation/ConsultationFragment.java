package com.harmoni.harmonikeluarga.ui.fragment.consultation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harmoni.harmonikeluarga.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultationFragment extends Fragment {

    private FragmentManager fm;

    public ConsultationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultation, container, false);

        ButterKnife.bind(this, view);

        addFragment();

        return view;
    }

    private void addFragment(){
        fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, new ConsultationFormFragment()).commit();
    }

    @OnClick(R.id.btn_form)
    public void goToForm(){
        fm.beginTransaction().replace(R.id.container, new ConsultationFormFragment()).commit();
    }

    @OnClick(R.id.btn_arsip)
    public void goToArchive(){
        fm.beginTransaction().replace(R.id.container, new ConsultationListFragment()).commit();
    }


}
