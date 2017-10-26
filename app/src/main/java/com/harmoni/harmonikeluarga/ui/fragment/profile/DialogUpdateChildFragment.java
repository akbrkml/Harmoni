package com.harmoni.harmonikeluarga.ui.fragment.profile;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;
import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataChildItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogUpdateChildFragment extends DialogFragment implements DatePickerDialogFragment.DatePickerDialogHandler {

    @BindView(R.id.sp_gender)Spinner mSpinnerGender;
    @BindView(R.id.sp_degree)Spinner mSpinnerDegree;
    @BindView(R.id.nama)EditText mInputNama;
    @BindView(R.id.birth)TextView mTextBirth;

    private String dataChild;

    private DataChildItem childItem;

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

        getArgument();

        return view;
    }

    private void getArgument(){
        dataChild = getArguments().getString("data_child");
        if (dataChild != null){
            childItem = new Gson().fromJson(dataChild, DataChildItem.class);
        }
        bindData();
    }

    private void bindData(){
        mInputNama.setText(childItem.getCcName());
        mTextBirth.setText(childItem.getCcBirthDate());

        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(getActivity(), R.array.gender, android.R.layout.simple_spinner_item);
        int position = adapterGender.getPosition(childItem.getCcGender());
        mSpinnerGender.setSelection(position);

        ArrayAdapter<CharSequence> adapterDegree = ArrayAdapter.createFromResource(getActivity(), R.array.degree, android.R.layout.simple_spinner_item);
        int pos = adapterDegree.getPosition(childItem.getDegreeName());
        mSpinnerDegree.setSelection(pos);
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

    @Override
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
        mTextBirth.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
    }
}
