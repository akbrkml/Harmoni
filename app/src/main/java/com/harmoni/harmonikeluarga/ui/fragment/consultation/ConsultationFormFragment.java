package com.harmoni.harmonikeluarga.ui.fragment.consultation;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

    public static TextView mTextUmur;
    @BindView(R.id.jenis)EditText mInputJenisMasalah;
    @BindView(R.id.isi)EditText mInputIsi;

    String umur, jenisMasalah, isi;
    static String childId;
    private ProgressDialog progressDialog;

    public static ConsultationFormFragment newInstance(){
        return new ConsultationFormFragment();
    }

    public ConsultationFormFragment() {
        // Required empty public constructor
    }

    private void hideProgress() {
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    private void showProgress() {
        progressDialog.show();
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

    @OnClick(R.id.umurAnak)
    public void selectChild(){
        SetChildFragment childFragment = new SetChildFragment();
        childFragment.show(getActivity().getSupportFragmentManager(),null);
    }

    private void clearText(){
        mTextUmur.setText("");
        mInputIsi.setText("");
        mInputJenisMasalah.setText("");
    }

    private void addConsultation(String customerId, String childId, String consultTitle, final String consultQuestion){
        if (!isValidateForm()){
            return;
        }

        showProgress();

        APIService apiService = new APIService();
        apiService.addConsultation("add_consultation", customerId, childId, consultTitle, consultQuestion, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                hideProgress();
                Consultation consultation = (Consultation) response.body();
                if (consultation != null){
                    if (consultation.isStatus()){
                        customInfoDialog(getActivity(), consultation.getText());
                        clearText();
                    } else {
                        customInfoDialog(getActivity(), consultation.getText());
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                hideProgress();
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

        addConsultation(getCustomerId(getActivity()), childId, jenisMasalah, isi);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultation_form, container, false);

        ButterKnife.bind(this, view);

        initComponents(view);

        return view;
    }

    private void initComponents(View view){
        mTextUmur = view.findViewById(R.id.umurAnak);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Harap tunggu...");
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
