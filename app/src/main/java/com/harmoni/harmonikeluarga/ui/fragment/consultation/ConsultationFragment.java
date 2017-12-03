package com.harmoni.harmonikeluarga.ui.fragment.consultation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultationFragment extends BaseFragment {

    @BindView(R.id.btn_form)Button mButtonForm;
    @BindView(R.id.btn_arsip)Button mButtonArsip;

    private FragmentManager fm;

    public static ConsultationFragment newInstance(){
        return new ConsultationFragment();
    }

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
        mButtonForm.setTextColor(getResources().getColor(R.color.new_white));
        mButtonForm.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mButtonArsip.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonArsip.setBackgroundColor(getResources().getColor(R.color.new_white));
    }

    @OnClick(R.id.btn_arsip)
    public void goToArchive(){
        fm.beginTransaction().replace(R.id.container, new ConsultationListFragment()).commit();
        mButtonArsip.setTextColor(getResources().getColor(R.color.new_white));
        mButtonArsip.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mButtonForm.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonForm.setBackgroundColor(getResources().getColor(R.color.new_white));
    }


    @Override
    protected String getTitle() {
        return null;
    }
}
