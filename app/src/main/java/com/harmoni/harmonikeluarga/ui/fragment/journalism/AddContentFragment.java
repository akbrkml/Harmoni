package com.harmoni.harmonikeluarga.ui.fragment.journalism;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.EventJounalism;
import com.harmoni.harmonikeluarga.model.User;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.util.PermissionHelper;

import java.io.File;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.harmoni.harmonikeluarga.util.Constant.getCustomerId;
import static com.harmoni.harmonikeluarga.util.DialogUtils.customInfoDialog;
import static com.harmoni.harmonikeluarga.util.RealPathUtils.getFilePath;
import static com.harmoni.harmonikeluarga.util.RealPathUtils.getRealPathFromURI_API19;
import static com.harmoni.harmonikeluarga.util.RealPathUtils.getRealPathFromUri;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContentFragment extends Fragment {

    @BindView(R.id.title)EditText mInputTitle;
    @BindView(R.id.desc)EditText mInputDesc;
    @BindView(R.id.imageName)TextView mImageName;
    @BindView(R.id.videoName)TextView mVideoName;

    private String title;
    private String desc;

    private ProgressDialog progressDialog;
    private PermissionHelper permissionHelper;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SELECT_IMAGE = 0;

    private Uri photoURI;
    private String mTempPhotoPath;

    public AddContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_content, container, false);

        ButterKnife.bind(this, view);

        initComponents();

        return view;
    }

    private void getData(){
        title = mInputTitle.getText().toString();
        desc = mInputDesc.getText().toString();
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

    private void initComponents(){
        permissionHelper = new PermissionHelper(getActivity());
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

    private void addNewJournalism(String contentDesc, String contentName){
        if (!isValidateForm()){
            return;
        }

        showProgress();

        APIService apiService = new APIService();
        apiService.addJournalism("add_journalism", getCustomerId(getActivity()), contentDesc, contentName, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                hideProgress();
                EventJounalism pesan = (EventJounalism) response.body();
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

            }
        });
    }

    @OnClick(R.id.btAddImage)
    public void addImage(){
        final CharSequence[] items = {"Kamera", "Galeri",
                "Batal"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Tambah Foto");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Kamera")) {
                    launchCamera();
                } else if (items[item].equals("Galeri")) {
                    checkAndRequestPermissions();
                } else if (items[item].equals("Batal")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @OnClick(R.id.btAddVideo)
    public void addVideo(){

    }

    @OnClick(R.id.bt_kirim)
    public void send(){

    }

    private void launchCamera() {

        // Create the capture image intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the temporary File where the photo should go
            File photoFile = null;
            // Continue only if the File was successfully created
            if (photoFile != null) {

                // Get the path of the temporary file
                mTempPhotoPath = photoFile.getAbsolutePath();

                // Get the content URI for the image file
//                photoURI = FileProvider.getUriForFile(getActivity(),
//                        FILE_PROVIDER_AUTHORITY,
//                        photoFile);
                photoURI = Uri.fromFile(photoFile);

                // Add the URI so the camera can store the image
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                // Launch the camera activity
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void launchGalleryImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_SELECT_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
//            if (requestCode == REQUEST_IMAGE_CAPTURE) {
            // Process the image and set it to the TextView

//            } else
            if (requestCode == REQUEST_SELECT_IMAGE && data != null && data.getData() != null){
                Uri imageUri = data.getData();


                try {
                    mTempPhotoPath = getFilePath(getActivity(), imageUri);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                File file = new File(mTempPhotoPath);
                String fileName = file.getName();
                mImageName.setText(fileName);

            }
        }
    }

    private boolean checkAndRequestPermissions() {
        permissionHelper.permissionListener(new PermissionHelper.PermissionListener() {
            @Override
            public void onPermissionCheckDone() {
                launchGalleryImage();
            }
        });

        permissionHelper.checkAndRequestPermissionStorage();

        return true;
    }

}
