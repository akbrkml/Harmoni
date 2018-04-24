package com.harmoni.harmonikeluarga.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.User;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.fragment.AlertSetPassword;
import com.harmoni.harmonikeluarga.util.SessionManager;
import com.jaredrummler.android.device.DeviceName;
import com.medialablk.easytoast.EasyToast;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.ui.fragment.AlertSetPassword.pass;
import static com.harmoni.harmonikeluarga.util.Constant.USER_SESSION;
import static com.harmoni.harmonikeluarga.util.DialogUtils.customInfoDialog;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.etNameRegist)EditText mInputName;
    @BindView(R.id.etEmailRegist)EditText mInputEmail;
    public static TextView mInputPass;
    @BindView(R.id.etPhoneRegist)EditText mInputPhone;
    @BindView(R.id.etProvRegist)TextView mInputProv;

    private String name, email, password, phone, province, deviceName, manufacturer;

    private ProgressDialog progressDialog;

    private APIService apiService;

    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        initComponents();

        mInputPass = findViewById(R.id.etPassRegist);

        mInputPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertSetPassword setPassword = new AlertSetPassword();
                setPassword.show(getSupportFragmentManager(), null);
            }
        });

        mInputProv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertProvince(v);
            }
        });

    }

    private void initComponents(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sign In");
    }

    private void hideProgress() {
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    private void showProgress() {
        progressDialog.show();
    }

    @OnClick(R.id.btLogin)
    public void doLogin(){
        LoginActivity.start(RegisterActivity.this);
        RegisterActivity.this.finish();
    }

    @OnClick(R.id.btRegister)
    public void doRegister(){
        getData();

        DeviceName.with(this).request(new DeviceName.Callback() {
            @Override public void onFinished(DeviceName.DeviceInfo info, Exception error) {
                manufacturer = info.manufacturer;  // "Samsung"
                String nama = info.marketName;            // "Galaxy S7 Edge"
                String model = info.model;                // "SAMSUNG-SM-G935A"
                String codename = info.codename;          // "hero2lte"
                deviceName = info.getName();       // "Galaxy S7 Edge"

//                EasyToast.info(getApplicationContext(), manufacturer + " " + deviceName);

                register("register", "", password, "", manufacturer + " " + deviceName, "", name, email, province, phone);
                // FYI: We are on the UI thread.
            }
        });
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
        } else if (phone.length() < 10 && phone.length() >= 10){
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

    private void register(String act,
                          String min,
                          String pass,
                          String city,
                          String handset,
                          String regId,
                          String name,
                          String email,
                          String province,
                          String phone){

        if (!isValidateForm()){
            return;
        }

        showProgress();

        apiService = new APIService();
        apiService.doRegister(act, min, pass,
                city, handset, regId,
                name, email, province, phone, new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        hideProgress();
                        User user = (User) response.body();
                        if (user != null){
                            if (user.isStatus()){
                                LoginActivity.start(RegisterActivity.this);
                                RegisterActivity.this.finish();
                                EasyToast.success(getApplicationContext(), "Registrasi berhasil. Login terlebih dahulu.");
                            } else {
                                customInfoDialog(RegisterActivity.this, user.getText());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        hideProgress();
                    }
                });
    }

    private void customInfoDialog(final Context context, String message){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_info);

        // set the custom dialog components - text and button
        TextView text = dialog.findViewById(R.id.tvKet);
        text.setText(message);

        Button mButtonOk = dialog.findViewById(R.id.btOk);

        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.start(RegisterActivity.this);
                RegisterActivity.this.finish();
            }
        });

        dialog.show();
    }

    public void alertProvince(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Pilih Provinsi");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice);
        arrayAdapter.add("Aceh");
        arrayAdapter.add("Bali");
        arrayAdapter.add("Banten");
        arrayAdapter.add("Bengkulu");
        arrayAdapter.add("Gorontalo");
        arrayAdapter.add("Jakarta");
        arrayAdapter.add("Jambi");
        arrayAdapter.add("Jawa Barat");
        arrayAdapter.add("Jawa Tengah");
        arrayAdapter.add("Jawa Timur");
        arrayAdapter.add("Kalimantan Barat");
        arrayAdapter.add("Kalimantan Selatan");
        arrayAdapter.add("Kalimantan Tengah");
        arrayAdapter.add("Kalimantan Timur");
        arrayAdapter.add("Kalimantan Utara");
        arrayAdapter.add("Kepulauan Bangka Belitung");
        arrayAdapter.add("Kepulauan Riau");
        arrayAdapter.add("Lampung");
        arrayAdapter.add("Maluku");
        arrayAdapter.add("Maluku Utara");
        arrayAdapter.add("Nusa Tenggara Timur");
        arrayAdapter.add("Nusa Tenggara Barat");
        arrayAdapter.add("Papua");
        arrayAdapter.add("Papua Barat");
        arrayAdapter.add("Riau");
        arrayAdapter.add("Sulawesi Barat");
        arrayAdapter.add("Sulawesi Selatan");
        arrayAdapter.add("Sulawesi Tengah");
        arrayAdapter.add("Sulawesi Tenggara");
        arrayAdapter.add("Sulawesi Utara");
        arrayAdapter.add("Sumatera Barat");
        arrayAdapter.add("Sumatera Selatan");
        arrayAdapter.add("Sumatera Utara");
        arrayAdapter.add("Yogyakarta");

        alert.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String strName = arrayAdapter.getItem(which);
                mInputProv.setText(strName);
            }
        });
        alert.show();
    }
}
