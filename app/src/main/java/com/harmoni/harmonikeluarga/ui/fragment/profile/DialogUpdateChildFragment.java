package com.harmoni.harmonikeluarga.ui.fragment.profile;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;
import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataChildItem;
import com.harmoni.harmonikeluarga.network.APIService;
import com.medialablk.easytoast.EasyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.ui.fragment.profile.ChildFragment.getDataChild;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogUpdateChildFragment extends DialogFragment
        implements DatePickerDialogFragment.DatePickerDialogHandler {

    @BindView(R.id.sp_gender)Spinner mSpinnerGender;
    @BindView(R.id.sp_degree)Spinner mSpinnerDegree;
    @BindView(R.id.nama)EditText mInputName;
    @BindView(R.id.birth)TextView mTextBirth;

    private DataChildItem childItem;

    public DialogUpdateChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_update_child, container, false);

        ButterKnife.bind(this, view);

        initDatePicker();

        getArgument();

        return view;
    }

    @OnClick(R.id.btHapus)
    public void doDelete(){
        getDialog().dismiss();
        deleteChildDialog(getActivity(), "HAPUS DATA ANAK (" + childItem.getCcName() + ") ?" );
    }

    @OnClick(R.id.btOk)
    public void doUpdate(){

    }

    private void deleteChildDialog(final Context context, String message){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_confirmation);

        // set the custom dialog components - text and button
        TextView text = dialog.findViewById(R.id.tvKet);
        text.setText(message);

        Button mButtonCancel = dialog.findViewById(R.id.btBatal);
        Button mButtonOk = dialog.findViewById(R.id.btOk);

        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteChild();
                dialog.dismiss();
            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void getArgument(){
        String dataChild = getArguments().getString("data_child");
        if (dataChild != null){
            childItem = new Gson().fromJson(dataChild, DataChildItem.class);
        }
        bindData();
    }

    private void bindData(){
        mInputName.setText(childItem.getCcName());
        mTextBirth.setText(childItem.getCcBirthDate());

        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(getActivity(), R.array.gender, android.R.layout.simple_spinner_item);
        int position = adapterGender.getPosition(childItem.getCcGender());
        mSpinnerGender.setSelection(position);

        ArrayAdapter<CharSequence> adapterDegree = ArrayAdapter.createFromResource(getActivity(), R.array.degree, android.R.layout.simple_spinner_item);
        int pos = adapterDegree.getPosition(childItem.getDegreeName());
        mSpinnerDegree.setSelection(pos);
    }

    private void initDatePicker(){
        mTextBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder dpb = new DatePickerBuilder()
                        .setFragmentManager(getChildFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .setTargetFragment(getTargetFragment());
                dpb.show();
            }
        });
    }

    @Override
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
        mTextBirth.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
    }

    private void deleteChild(){
        APIService apiService = new APIService();
        apiService.deleteChild("delete_child", childItem.getCcId(), new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()){
//                    EasyToast.success(getActivity(), "Data berhasil dihapus");
                    getDataChild();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
