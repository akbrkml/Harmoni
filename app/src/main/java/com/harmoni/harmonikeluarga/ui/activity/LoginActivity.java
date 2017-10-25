package com.harmoni.harmonikeluarga.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.crashlytics.android.Crashlytics;
import com.harmoni.harmonikeluarga.BuildConfig;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.User;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.util.SessionManager;
import com.medialablk.easytoast.EasyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.harmoni.harmonikeluarga.util.AlertDialogManager.showAlertDialog;
import static com.harmoni.harmonikeluarga.util.Constant.USER_SESSION;
import static com.harmoni.harmonikeluarga.util.DialogUtils.customInfoDialog;
import static com.harmoni.harmonikeluarga.util.Hashing.md5;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.etEmailLogin)EditText mInputEmail;
    @BindView(R.id.etPassLogin)EditText mInputPass;

    private String email, password;

    private ProgressDialog progressDialog;

    private APIService apiService;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_login);

        if(isSessionLogin()) {
            MainActivity.start(this);
            LoginActivity.this.finish();
        }

        ButterKnife.bind(this);

        initComponents();
    }

    private void initComponents(){
        progressDialog = new ProgressDialog(this);
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

    private void getData(){
        email = mInputEmail.getText().toString().trim();
        password = mInputPass.getText().toString().trim();
    }

    private boolean isValidateForm(){
        boolean result = true;

        getData();

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

        return result;
    }

    @OnClick(R.id.btLogin)
    public void doLogin(){
        getData();

        authenticateUser(email, md5(md5(password)));
    }

    @OnClick(R.id.btRegister)
    public void doRegister(){
        RegisterActivity.start(this);
        finish();
    }

    private void authenticateUser(final String email, String password){

        if (!isValidateForm()){
            return;
        }

        showProgress();

        apiService = new APIService();
        apiService.doLogin("login", email, password, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                User user = (User) response.body();
                hideProgress();
                if (user != null){
                    if (user.isStatus()){
                        onSuccessLogin(LoginActivity.this, USER_SESSION, user);
                        SessionManager.save("email", email);
                        MainActivity.start(LoginActivity.this);
                        LoginActivity.this.finish();
                        EasyToast.info(getApplicationContext(), "Welcome " + user.getDataUser().getCustomerName());
                    } else {
                        showAlertDialog(LoginActivity.this, getString(R.string.app_name), getString(R.string.auth_failed), false);
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (BuildConfig.DEBUG)
                    Timber.d(TAG + " -> onFailure: ", t.getMessage());
                EasyToast.error(getApplicationContext(), "Koneksi eror");
                hideProgress();
            }
        });
    }

    private void onSuccessLogin(Context context, String key, User user){
        SessionManager.putUser(context, key, user);
    }

    boolean isSessionLogin() {
        return SessionManager.getUser(this, USER_SESSION) != null;
    }

}
