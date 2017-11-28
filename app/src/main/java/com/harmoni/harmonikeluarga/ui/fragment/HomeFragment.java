package com.harmoni.harmonikeluarga.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.ui.base.BackButtonSupportFragment;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;
import com.harmoni.harmonikeluarga.ui.fragment.consultation.ConsultationFragment;
import com.harmoni.harmonikeluarga.ui.fragment.content.ContentChildFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements BackButtonSupportFragment {

    private String title;

    private boolean consumingBackPress = true;

    private FragmentManager fm;
    private Toast toast;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        fm = getActivity().getSupportFragmentManager();

        return view;
    }

    @OnClick(R.id.bgHome1)
    public void goToBeranda(){
        fm.beginTransaction().replace(R.id.content_frame, new ContentChildFragment()).addToBackStack("tag").commit();
    }

    @OnClick(R.id.bgHome2)
    public void goToAhli(){
        fm.beginTransaction().replace(R.id.content_frame, new ConsultationFragment()).addToBackStack("tag").commit();
    }

    @OnClick(R.id.bgHome3)
    public void goToEvent(){
        fm.beginTransaction().replace(R.id.content_frame, new EventFragment()).addToBackStack("tag").commit();
    }

    @OnClick(R.id.bgHome4)
    public void goToPustaka(){
        fm.beginTransaction().replace(R.id.content_frame, new MainLibraryFragment()).addToBackStack("tag").commit();
    }

    @OnClick(R.id.bgHome5)
    public void goToJournalism(){
        fm.beginTransaction().replace(R.id.content_frame, new JournalismFragment()).addToBackStack("tag").commit();
    }

    @Override
    protected String getTitle() {
        return title;
    }

    @Override
    public boolean onBackPressed() {
        if (consumingBackPress) {
            //This is actually a terrible thing to do and totally against the guidelines
            // Ideally you shouldn't handle the backpress ever, so really think twice about what
            // you are doing and whether you are getting hacky
            toast = Toast.makeText(getActivity(), "Press back once more to quit the application", Toast.LENGTH_LONG);
            toast.show();
            consumingBackPress = false;
            return true; //consumed
        }
        toast.cancel();
        return false;
    }
}
