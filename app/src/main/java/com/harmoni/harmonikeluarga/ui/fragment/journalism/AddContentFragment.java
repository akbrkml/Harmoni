package com.harmoni.harmonikeluarga.ui.fragment.journalism;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContentFragment extends Fragment {

    @BindView(R.id.title)EditText mInputTitle;
    @BindView(R.id.desc)EditText mInputDesc;
    @BindView(R.id.imageName)TextView mImageName;
    @BindView(R.id.videoName)TextView mVideoName;

    public AddContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_content, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

}
