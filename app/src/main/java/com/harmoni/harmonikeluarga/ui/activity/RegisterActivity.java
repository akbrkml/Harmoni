package com.harmoni.harmonikeluarga.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.crashlytics.android.Crashlytics;
import com.harmoni.harmonikeluarga.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

import static com.harmoni.harmonikeluarga.util.DialogUtils.customInfoDialog;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.etNameRegist)EditText mInputName;
    @BindView(R.id.etEmailRegist)EditText mInputEmail;
    @BindView(R.id.etPassRegist)EditText mInputPass;
    @BindView(R.id.etPhoneRegist)EditText mInputPhone;
    @BindView(R.id.etProvRegist)EditText mInputProv;

    private String name, email, password, phone, province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btRegister)
    public void doRegister(){

    }

    private void getData(){
        name = mInputName.getText().toString().trim();
        email = mInputEmail.getText().toString().trim();
        password = mInputPass.getText().toString().trim();
        phone = mInputPhone.getText().toString().trim();
        province = mInputProv.getText().toString().trim();
    }

    private boolean isValidateForm(){
        boolean result = true;

        getData();

        if (TextUtils.isEmpty(name)) {
            customInfoDialog(this, "Nama tidak boleh kosong!");
            result = false;
        } else if (name.length() < 1) {
            customInfoDialog(this, "Nama terlalu pendek!");
            result = false;
        } else {
            mInputName.setError(null);
        }

        if (TextUtils.isEmpty(email)) {
            customInfoDialog(this, "Email tidak boleh kosong!");
            result = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            customInfoDialog(this, "Email yang anda masukkan tidak sesuai!");
            result = false;
        } else {
            mInputEmail.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            customInfoDialog(this, "Password tidak boleh kosong!");
            result = false;
        } else if (password.length() < 1){
            customInfoDialog(this, "Kata sandi terlalu pendek");
            result = false;
        } else {
            mInputPass.setError(null);
        }

        if (TextUtils.isEmpty(phone)) {
            customInfoDialog(this, "Nomor telp tidak boleh kosong!");
            result = false;
        } else if (password.length() <= 13){
            customInfoDialog(this, "Nomor telp terlalu pendek");
            result = false;
        } else {
            mInputPhone.setError(null);
        }

        if (TextUtils.isEmpty(province)) {
            customInfoDialog(this, "Provinsi tidak boleh kosong!");
            result = false;
        } else {
            mInputProv.setError(null);
        }

        return result;
    }
}
