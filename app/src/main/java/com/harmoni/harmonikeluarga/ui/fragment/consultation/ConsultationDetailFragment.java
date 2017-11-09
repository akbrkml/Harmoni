package com.harmoni.harmonikeluarga.ui.fragment.consultation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataConsultationItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultationDetailFragment extends Fragment {

    @BindView(R.id.title)TextView mTextTitle;
    @BindView(R.id.status)TextView mTextStatus;
    @BindView(R.id.date)TextView mTextDate;
    @BindView(R.id.desc)TextView mTextDesc;
    @BindView(R.id.namaAnak)TextView mTextChildName;
    @BindView(R.id.umurAnak)TextView mTextChildAge;
    @BindView(R.id.pendidikan)TextView mTextEducation;
    @BindView(R.id.answerText)TextView mTextAnswer;
    private DataConsultationItem consultationItem;

    public ConsultationDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultation_detail, container, false);

        ButterKnife.bind(this,view);

        getArgument();

        return view;
    }

    private void getArgument() {
        String dataConsultation = getArguments().getString("data_consultation");
        if (dataConsultation != null) {
            consultationItem = new Gson().fromJson(dataConsultation, DataConsultationItem.class);
        }
        bindData();
    }

    private void bindData(){
        mTextTitle.setText(consultationItem.getConsultTitle());
        mTextStatus.setText(consultationItem.getConsultStatus());
        mTextDate.setText(consultationItem.getConsultCreateDate());
        mTextDesc.setText(consultationItem.getConsultQuestion());
        mTextChildName.setText(consultationItem.getDataChild().getCcName());
        mTextChildAge.setText(consultationItem.getDataChild().getCcAge());
        mTextEducation.setText(consultationItem.getDataChild().getDegreeName());
        mTextAnswer.setText(consultationItem.getConsultAnswerText());
    }

}
