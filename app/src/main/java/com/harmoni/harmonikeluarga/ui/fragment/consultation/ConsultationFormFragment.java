package com.harmoni.harmonikeluarga.ui.fragment.consultation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.Consultation;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.util.Constant.getCustomerId;
import static com.harmoni.harmonikeluarga.util.DialogUtils.customInfoDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultationFormFragment extends BaseFragment {

    @BindView(R.id.umurAnak)TextView mTextUmur;
    @BindView(R.id.jenis)EditText mInputJenisMasalah;
    @BindView(R.id.isi)EditText mInputIsi;

    String umur, jenisMasalah, isi;

    public static ConsultationFormFragment newInstance(){
        return new ConsultationFormFragment();
    }

    public ConsultationFormFragment() {
        // Required empty public constructor
    }

    private boolean isValidateForm(){
        boolean result = true;

        getData();

        if (TextUtils.isEmpty(umur) || TextUtils.isEmpty(jenisMasalah) || TextUtils.isEmpty(isi)) {
            customInfoDialog(getActivity(), "Silahkan lengkapi form terlebih dahulu");
            result = false;
        }

        return result;
    }

    private void addConsultation(String customerId, String childId, String consultTitle, final String consultQuestion){
        if (!isValidateForm()){
            return;
        }

        APIService apiService = new APIService();
        apiService.addConsultation("add_consultation", customerId, childId, consultTitle, consultQuestion, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Consultation consultation = (Consultation) response.body();
                if (consultation != null){
                    if (consultation.isStatus()){
                        customInfoDialog(getActivity(), consultation.getText());
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    private void getData() {
        umur = mTextUmur.getText().toString();
        jenisMasalah = mInputJenisMasalah.getText().toString();
        isi = mInputIsi.getText().toString();
    }

    @OnClick(R.id.bt_simpan)
    public void doSave(){
        getData();

        addConsultation(getCustomerId(getActivity()), umur, jenisMasalah, isi);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultation_form, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
