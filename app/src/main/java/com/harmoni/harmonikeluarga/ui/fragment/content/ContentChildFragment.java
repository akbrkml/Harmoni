package com.harmoni.harmonikeluarga.ui.fragment.content;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harmoni.harmonikeluarga.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentChildFragment extends Fragment {


    public ContentChildFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content_child, container, false);

        initComponents();

        return view;
    }

    private void initComponents(){

    }

}
