package com.harmoni.harmonikeluarga.ui.fragment.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.User;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;
import com.harmoni.harmonikeluarga.util.SessionManager;
import com.medialablk.easytoast.EasyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.util.Constant.USER_SESSION;
import static com.harmoni.harmonikeluarga.util.DialogUtils.customInfoDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment {

    @BindView(R.id.nama)EditText mInputName;
    @BindView(R.id.email)EditText mInputEmail;
    @BindView(R.id.phone)EditText mInputPhone;
    @BindView(R.id.alamat)EditText mInputAddress;
    @BindView(R.id.province)EditText mInputProvince;
    @BindView(R.id.kota)EditText mInputCity;
    @BindView(R.id.swipeRefresh)SwipeRefreshLayout mRefreshLayout;

    private String name, email, phone, address, province, city, customerId;

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    private void getData(){
        name = mInputName.getText().toString();
        email = mInputEmail.getText().toString();
        phone = mInputPhone.getText().toString();
        address = mInputAddress.getText().toString();
        province = mInputProvince.getText().toString();
        city = mInputCity.getText().toString();
    }

    @OnClick(R.id.btn_simpan)
    public void doSave(){
        getData();

        updateProfile();
    }

    private void updateProfile(){
        APIService apiService = new APIService();
        apiService.updateProfile("update_profile", address, email, name,
                province, phone, customerId, city, new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        User user = (User) response.body();
                        if (user != null){
                            if (user.isStatus()){
                                customInfoDialog(getActivity(), "Data profil anda berhasil diperbarui.");
                                getDataProfile();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        EasyToast.error(getActivity(), "Koneksi error");
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        getSession();

        getDataProfile();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataProfile();
            }
        });

        return view;
    }

    private void getSession(){
        User user = SessionManager.getUser(getActivity(), USER_SESSION);
        if (user != null) {
            email = user.getDataUser().getCustomerEmail();
            customerId = user.getDataUser().getCustomerId();
        }
    }

    @Override
    protected String getTitle() {
        return null;
    }

    private void getDataProfile(){
        mRefreshLayout.setRefreshing(true);
        APIService apiService = new APIService();
        apiService.getProfile("profile", email, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mRefreshLayout.setRefreshing(false);
                User user = (User) response.body();
                if (user != null){
                    if (user.isStatus()){
                        mInputName.setText(user.getDataUser().getCustomerName());
                        mInputAddress.setText(user.getDataUser().getCustomerAddress());
                        mInputCity.setText(user.getDataUser().getCustomerCity());
                        mInputEmail.setText(user.getDataUser().getCustomerEmail());
                        mInputPhone.setText(user.getDataUser().getCustomerMsisdn());
                        mInputProvince.setText(user.getDataUser().getCustomerProvince());
                        customerId = user.getDataUser().getCustomerId();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                mRefreshLayout.setRefreshing(false);
                EasyToast.error(getActivity(), "Koneksi error");
            }
        });
    }
}
