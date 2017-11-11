package com.harmoni.harmonikeluarga.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.User;
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
public class SaranFragment extends BaseFragment {

    @BindView(R.id.title)EditText mInputTitle;
    @BindView(R.id.desc)EditText mInputDesc;

    private String title, desc;
    private ProgressDialog progressDialog;

    public static SaranFragment newInstance(){
        return new SaranFragment();
    }

    public SaranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saran, container, false);

        ButterKnife.bind(this, view);

        initComponents();

        return view;
    }

    private void initComponents(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Harap tunggu...");
    }

    private void hideProgress() {
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    private void showProgress() {
        progressDialog.show();
    }

    @OnClick(R.id.bt_kirim)
    public void doSend(){
        getData();

        addSaran(getCustomerId(getActivity()), desc, title);
    }

    private boolean isValidateForm(){
        boolean result = true;

        getData();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(desc)) {
            customInfoDialog(getActivity(), "Silahkan lengkapi form terlebih dahulu");
            result = false;
        }

        return result;
    }

    private void getData() {
        title = mInputTitle.getText().toString();
        desc = mInputDesc.getText().toString();
    }

    private void addSaran(String customerId, String saranText, String saranTitle){
        if (!isValidateForm()){
            return;
        }

        showProgress();

        APIService apiService = new APIService();
        apiService.addSaran("add_saran", customerId, saranText, saranTitle, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                hideProgress();
                User pesan = (User) response.body();
                if (pesan != null){
                    if (pesan.isStatus()){
                        customInfoDialog(getActivity(), pesan.getText());
                        mInputTitle.setText("");
                        mInputDesc.setText("");
                    } else {
                        customInfoDialog(getActivity(), pesan.getText());
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                hideProgress();
            }
        });
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
