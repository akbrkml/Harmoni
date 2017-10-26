package com.harmoni.harmonikeluarga.ui.fragment.profile;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;
import com.harmoni.harmonikeluarga.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddChildFragment extends DialogFragment implements DatePickerDialogFragment.DatePickerDialogHandler {

    @BindView(R.id.sp_gender)Spinner mSpinnerGender;
    @BindView(R.id.sp_degree)Spinner mSpinnerDegree;
    @BindView(R.id.nama)EditText mInputNama;
    @BindView(R.id.birth)TextView mTextBirth;

    public AddChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_child, container, false);

        ButterKnife.bind(this, view);

        initDatePicker();

        return view;
    }

    private void initDatePicker(){
        mTextBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder dpb = new DatePickerBuilder()
                        .setFragmentManager(getChildFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .setTargetFragment(getTargetFragment());
                dpb.show();
            }
        });
    }

    @OnClick(R.id.btOk)
    public void doSave(){

    }

    @Override
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
        mTextBirth.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
    }
}
