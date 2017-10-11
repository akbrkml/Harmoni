package com.harmoni.harmonikeluarga.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;
import com.harmoni.harmonikeluarga.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogUpdateChildFragment extends DialogFragment implements DatePickerDialogFragment.DatePickerDialogHandler {

    @BindView(R.id.birth)TextView mTextBirth;

    public DialogUpdateChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_update_child, container, false);

        ButterKnife.bind(this, view);

        initDatePicker();

        return view;
    }

    private void initDatePicker(){
        mTextBirth.setText("--");
        mTextBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder dpb = new DatePickerBuilder()
                        .setFragmentManager(getChildFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .setTargetFragment(DialogUpdateChildFragment.this);
                dpb.show();
            }
        });
    }

    @Override
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
        mTextBirth.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
    }
}
