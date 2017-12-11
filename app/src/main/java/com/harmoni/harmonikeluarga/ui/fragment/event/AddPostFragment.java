package com.harmoni.harmonikeluarga.ui.fragment.event;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.util.PermissionHelper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.harmoni.harmonikeluarga.ui.fragment.event.DetailEventFragment.eventItem;
import static com.harmoni.harmonikeluarga.util.RealPathUtils.getFilePath;
import static com.harmoni.harmonikeluarga.util.RealPathUtils.getRealPathFromURI_API19;
import static com.harmoni.harmonikeluarga.util.RealPathUtils.getRealPathFromUri;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPostFragment extends Fragment {

    @BindView(R.id.title)EditText mInputTitle;
    @BindView(R.id.desc)EditText mInputDesc;
    @BindView(R.id.bg_addimage)LinearLayout mLayoutImage;
    @BindView(R.id.bg_addaudio)LinearLayout mLayoutAudio;
    @BindView(R.id.bg_addvideo)LinearLayout mLayoutVideo;
    @BindView(R.id.imageName)TextView mImageName;
    @BindView(R.id.audioName)TextView mAudioName;
    @BindView(R.id.videoName)TextView mVideoName;
    private PermissionHelper permissionHelper;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SELECT_IMAGE = 0;

    private Uri photoURI;
    private String mTempPhotoPath;

    public AddPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);

        ButterKnife.bind(this,view);

        initComponents();

        return view;
    }

    private void initComponents(){
        permissionHelper = new PermissionHelper(getActivity());
        int size = eventItem.getEventType().size();
        String[] type = new String[size];
        for (int i = 0; i < size; i++){
            type[i] = eventItem.getEventType().get(i);
        }

        if (Arrays.asList(type).contains("image")){
            mLayoutImage.setVisibility(View.VISIBLE);
        }
        if (Arrays.asList(type).contains("audio")){
            mLayoutAudio.setVisibility(View.VISIBLE);
        }
        if (Arrays.asList(type).contains("video")){
            mLayoutVideo.setVisibility(View.VISIBLE);
        }
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

    @OnClick(R.id.btAddAudio)
    public void addAudio(){

    }

    @OnClick(R.id.btAddVideo)
    public void addVideo(){

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

    private void launchGallery(){
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
                launchGallery();
            }
        });

        permissionHelper.checkAndRequestPermissionStorage();

        return true;
    }
}
