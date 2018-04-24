package com.harmoni.harmonikeluarga.ui.fragment.journalism;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataContentItem;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.activity.MainActivity;
import com.harmoni.harmonikeluarga.util.FileUtil;
import com.medialablk.easytoast.EasyToast;
import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.harmoni.harmonikeluarga.util.Constant.getCustomerId;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContentFragment extends Fragment {

    private FragmentManager fm;
    private APIService apiService;
    private ProgressDialog progressDialog;
    private static final int FILE_SELECT_CODE1 = 0;
    private static final int FILE_SELECT_CODE2 = 1;
    String cidx;

    Uri uriImage;
    Uri uriVideo;

    @BindView(R.id.title)EditText mInputTitle;
    @BindView(R.id.desc)EditText mInputDesc;
    @BindView(R.id.imageName)TextView mImageName;
    @BindView(R.id.videoName)TextView mVideoName;

    public AddContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_content, container, false);
        ButterKnife.bind(this, view);
        Nammu.init(getActivity());

        initPermission();
        initComponents();
        return view;
    }

    @OnClick(R.id.bt_kirim)
    public void sendContentJournalism() {
        showProgress();
        String title = mInputTitle.getText().toString();
        String desc = mInputDesc.getText().toString();
        apiService = new APIService();
        apiService.addContent("add_journalism", getCustomerId(getActivity()),title, desc, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                DataContentItem pesan = (DataContentItem) response.body();
                Log.d("Respon Add Journalism",pesan.getContentID());
                cidx = pesan.getContentID();
                uploadFile(uriImage,"image",cidx);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                hideProgress();
                EasyToast.error(getActivity().getApplicationContext(), "Post Tidak Terkirim");
            }
        });
    }

    @OnClick(R.id.btAddImage)
    public void showFileImageChooser(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE1);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this.getContext(), "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btAddVideo)
    public void showFileVideoChooser(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE2);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this.getContext(), "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE1:
                if (resultCode == RESULT_OK) {
                    uriImage = data.getData();
                    String path = FileUtil.getPath(getActivity(), uriImage);
                    File file = new File(path);
                    mImageName.setText(file.getName());
                }
                break;
            case FILE_SELECT_CODE2:
                if (resultCode == RESULT_OK) {
                    uriVideo= data.getData();
                    String path = FileUtil.getPath(getActivity(), uriImage);
                    File file = new File(path);
                    mVideoName.setText(file.getName());
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void uploadFile(Uri sourceFileUri, String type, String id) {
        String imageName = mImageName.getText().toString();
        if(!imageName.equals("")) {
            String path = FileUtil.getPath(getActivity(), sourceFileUri);
            File file = new File(path);
            RequestBody requestFile =
                    RequestBody.create(
                            MediaType.parse(getActivity().getContentResolver().getType(sourceFileUri)),
                            file
                    );
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

            RequestBody cid = RequestBody.create(MediaType.parse("text/plain"), id);
            RequestBody mtype = RequestBody.create(MediaType.parse("text/plain"), type);
            RequestBody act = RequestBody.create(MediaType.parse("text/plain"), "upload_file");

            apiService = new APIService();
            apiService.uploadFileContent(act, cid, mtype, body, new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    uploadFileVideo(uriVideo,"video",cidx);
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    hideProgress();
                    EasyToast.error(getActivity().getApplicationContext(), "File tidak terupload");
                }
            });
        } else {
            uploadFileVideo(sourceFileUri,type,id);
        }
    }

    public void uploadFileVideo(Uri sourceFileUri, String type,String id) {
        String videoName = mVideoName.getText().toString();
        if(!videoName.equals("")) {
            String path = FileUtil.getPath(getActivity(), sourceFileUri);
            File file = new File(path);
            RequestBody requestFile =
                    RequestBody.create(
                            MediaType.parse(getActivity().getContentResolver().getType(sourceFileUri)),
                            file
                    );
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

            RequestBody cid = RequestBody.create(MediaType.parse("text/plain"), id);
            RequestBody mtype = RequestBody.create(MediaType.parse("text/plain"), type);
            RequestBody act = RequestBody.create(MediaType.parse("text/plain"), "upload_file");

            apiService = new APIService();
            apiService.uploadFileContent(act, cid, mtype, body, new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    hideProgress();
                    closeFragment("Post Terkirim");
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    hideProgress();
                    EasyToast.error(getActivity().getApplicationContext(), "File tidak terupload");
                }
            });
        } else {
            closeFragment("Post Terkirim");
        }
    }

    private void initComponents(){
        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setMessage("Loading...");
    }

    private void hideProgress() {
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    private void showProgress() {
        progressDialog.show();
    }

    private String getFileName(Uri uri){
        File file = uriToFile(uri);
        String name = file.getName();
        return name;
    }

    private File uriToFile(Uri uri){
        File file = new File(uri.getPath());
        return file;
    }

    private void closeFragment(String msg){
        hideProgress();
        EasyToast.info(getActivity().getApplicationContext(), msg);
        Intent i = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        startActivity(i);
        getActivity().finish();

//        FragmentManager fm = getActivity().getSupportFragmentManager();
//        HomeFragment home = new HomeFragment();
//        fm.beginTransaction().replace(R.id.content_frame, home).addToBackStack("tag").commit();
    }

    private void initPermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Nammu.askForPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionCallback() {
                @Override
                public void permissionGranted() {
                    //Nothing, this sample saves to Public gallery so it needs permission
                }

                @Override
                public void permissionRefused() {
                    getActivity().finish();
                }
            });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
