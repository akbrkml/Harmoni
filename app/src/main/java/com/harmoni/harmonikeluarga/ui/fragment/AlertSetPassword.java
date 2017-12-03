package com.harmoni.harmonikeluarga.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.harmoni.harmonikeluarga.R;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.harmoni.harmonikeluarga.ui.activity.RegisterActivity.mInputPass;

/**
 * Created by akbar on 23/10/17.
 */

public class AlertSetPassword extends DialogFragment implements Validator.ValidationListener {

    @Required(order = 1)
    @Password(order = 2, message = "Masukkan password dengan benar!")
    @TextRule(order = 3, minLength = 6, message = "Password minimal 6 karakter!")
    @BindView(R.id.pSet)EditText mInputPassword;

    @Required(order = 4)
    @ConfirmPassword(order = 5, message = "Salah mengulangi password!")
    @BindView(R.id.pRe)EditText mInputRePassword;

    @BindView(R.id.cbsp)CheckBox mShowPassword;

    private Validator mChangePasswordValidator;

    public static String pass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.alert_set_password, container, false);

        ButterKnife.bind(this, view);

        initCheckbox();

        mChangePasswordValidator = new Validator(this);
        mChangePasswordValidator.setValidationListener(this);

        return view;
    }

    private void initCheckbox(){
        mShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mInputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mInputRePassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    mInputPassword.setInputType(129);
                    mInputRePassword.setInputType(129);
                }
            }
        });
    }

    @OnClick(R.id.btBatal)
    public void doCancel(){
        getDialog().dismiss();
    }

    @OnClick(R.id.btOk)
    public void doSetPassword(){
        mChangePasswordValidator.validate();
    }

    @Override
    public void onValidationSucceeded() {
        getDialog().dismiss();
        Toast.makeText(getActivity(), "Password tersimpan",
                Toast.LENGTH_SHORT).show();
        mInputPass.setText(mInputRePassword.getText().toString());
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        String message = failedRule.getFailureMessage();
        if (failedView instanceof EditText) {
            failedView.requestFocus();
            ((EditText) failedView).setError(message);
        } else {
            Toast.makeText(getActivity(), "Salah mengulangi password!", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}