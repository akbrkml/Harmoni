package com.harmoni.harmonikeluarga.ui.fragment.profile;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.Child;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;
import com.medialablk.easytoast.EasyToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.ui.fragment.profile.ChildFragment.child;
import static com.harmoni.harmonikeluarga.ui.fragment.profile.ChildFragment.getDataChild;
import static com.harmoni.harmonikeluarga.util.Constant.getCustomerId;
import static com.harmoni.harmonikeluarga.util.DialogUtils.customInfoDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddChildFragment extends DialogFragment
        implements DatePickerDialogFragment.DatePickerDialogHandler {

    @BindView(R.id.sp_gender)Spinner mSpinnerGender;
    @BindView(R.id.sp_degree)Spinner mSpinnerDegree;
    @BindView(R.id.nama)EditText mInputNama;
    @BindView(R.id.birth)EditText mTextBirth;
    private String gender;
    private String name;
    private String birth;
    private String degreeSelectedId;

    private ProgressDialog progressDialog;

    public AddChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_child, container, false);

        ButterKnife.bind(this, view);

        initDatePicker();

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

    private void initDatePicker(){
//        final Calendar cal = Calendar.getInstance();
//        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                String dateFormat = "dd-MM-yyyy";
//                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
//                mTextBirth.setText(sdf.format(cal.getTime()));
//            }
//        };
//
//        mTextBirth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTextBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    @Override
//                    public void onFocusChange(View v, boolean hasFocus) {
//                        DatePickerDialog dpd = new DatePickerDialog(getActivity(), dateSetListener,
//                                cal.get(Calendar.YEAR),
//                                cal.get(Calendar.MONTH),
//                                cal.get(Calendar.DAY_OF_MONTH));
//
//                        if (hasFocus) {
//                            dpd.show();
//                        } else {
//                            dpd.dismiss();
//                        }
//                    }
//                });
//            }
//        });

//        mTextBirth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerBuilder dpb = new DatePickerBuilder()
//                        .setFragmentManager(getChildFragmentManager())
//                        .setStyleResId(R.style.BetterPickersDialogFragment)
//                        .setTargetFragment(getTargetFragment());
//                dpb.show();
//            }
//        });
    }

    private void getData(){
        birth = mTextBirth.getText().toString();
        name = mInputNama.getText().toString();
    }

    @OnClick(R.id.btOk)
    public void doSave(){
        int jumlahAnak = child.getDataChild() != null ? child.getDataChild().size():0;
        if (jumlahAnak == 0 || jumlahAnak > 0){
            jumlahAnak += 1;
        }
//        EasyToast.info(getActivity(), String.valueOf(jumlahAnak));
        addChild(String.valueOf(jumlahAnak));
    }

    private void addChild(String childNumber){
        getData();

        if (mSpinnerDegree.getSelectedItem().toString().equals("Paud")){
            degreeSelectedId = "2";
        } else if (mSpinnerDegree.getSelectedItem().toString().equals("TK")){
            degreeSelectedId = "3";
        } else if (mSpinnerDegree.getSelectedItem().toString().equals("SD")){
            degreeSelectedId = "4";
        } else if (mSpinnerDegree.getSelectedItem().toString().equals("SMP")){
            degreeSelectedId = "5";
        } else if (mSpinnerDegree.getSelectedItem().toString().equals("SMA/SMK")){
            degreeSelectedId = "6";
        } else if (mSpinnerDegree.getSelectedItem().toString().equals("Pendidikan Non Formal")){
            degreeSelectedId = "7";
        } else if (mSpinnerDegree.getSelectedItem().toString().equals("Pendidikan Anak Kebutuhan Khusus")){
            degreeSelectedId = "8";
        }

        getDialog().dismiss();
        showProgress();

        APIService apiService = new APIService();
        apiService.addChild("add_child", degreeSelectedId, "", mSpinnerGender.getSelectedItem().toString(), birth, name, getCustomerId(getActivity()), childNumber, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                hideProgress();
                Child child = (Child) response.body();
                if (child != null){
                    if (child.isStatus()){
                        getDataChild();
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
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
        mTextBirth.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
        EasyToast.info(getActivity(), dayOfMonth + "-" + monthOfYear + "-" + year);
    }

}
