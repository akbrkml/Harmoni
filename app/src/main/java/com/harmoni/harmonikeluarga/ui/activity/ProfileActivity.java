package com.harmoni.harmonikeluarga.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.User;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.fragment.profile.ChildFragment;
import com.harmoni.harmonikeluarga.ui.fragment.profile.ProfileFragment;
import com.harmoni.harmonikeluarga.ui.fragment.profile.TopicFragment;
import com.harmoni.harmonikeluarga.util.FileUtil;
import com.harmoni.harmonikeluarga.util.SessionManager;
import com.medialablk.easytoast.EasyToast;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

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

import static com.harmoni.harmonikeluarga.util.Constant.IMAGE_USER_URL;
import static com.harmoni.harmonikeluarga.util.Constant.USER_SESSION;
import static com.harmoni.harmonikeluarga.util.Constant.getCustomerId;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)Toolbar mToolbar;
    @BindView(R.id.img_user)CircularImageView mImageUser;
    @BindView(R.id.nameUser)TextView mTextUser;
    @BindView(R.id.btn_profile)Button mButtonProfile;
    @BindView(R.id.btn_anak)Button mButtonAnak;
    @BindView(R.id.btn_topik)Button mButtonTopik;

    private static final int FILE_SELECT_CODE1 = 0;

    Uri uriImage;

    private FragmentManager fm;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        Nammu.init(this);

        initPermission();

        initToolbar();

        addFragment();

        getSession();
    }

    private void getSession(){
        User user = SessionManager.getUser(this, USER_SESSION);
        String imageUser = SessionManager.getString(this, "image_user");
        if (user != null){
            mTextUser.setText(user.getDataUser().getCustomerName());
        }
        if (imageUser != null){
            Glide.with(getApplicationContext())
                    .load(imageUser)
                    .into(mImageUser);
        }
    }

    private void addFragment(){
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, new ProfileFragment()).commit();
    }

    private void initToolbar(){
        setSupportActionBar(mToolbar);
        setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick(R.id.btn_upload_photo)
    public void doUpload(){
        showFileImageChooser();
    }

    @OnClick(R.id.btn_profile)
    public void goToProfile(){
        fm.beginTransaction().replace(R.id.container, new ProfileFragment()).commit();

        mButtonProfile.setTextColor(getResources().getColor(R.color.new_white));
        mButtonProfile.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mButtonAnak.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonAnak.setBackgroundColor(getResources().getColor(R.color.new_white));

        mButtonTopik.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonTopik.setBackgroundColor(getResources().getColor(R.color.new_white));
    }

    @OnClick(R.id.btn_anak)
    public void goToChild(){
        fm.beginTransaction().replace(R.id.container, new ChildFragment()).commit();

        mButtonAnak.setTextColor(getResources().getColor(R.color.new_white));
        mButtonAnak.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mButtonProfile.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonProfile.setBackgroundColor(getResources().getColor(R.color.new_white));

        mButtonTopik.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonTopik.setBackgroundColor(getResources().getColor(R.color.new_white));
    }

    @OnClick(R.id.btn_topik)
    public void goToTopic(){
        fm.beginTransaction().replace(R.id.container, new TopicFragment()).commit();

        mButtonTopik.setTextColor(getResources().getColor(R.color.new_white));
        mButtonTopik.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mButtonAnak.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonAnak.setBackgroundColor(getResources().getColor(R.color.new_white));

        mButtonProfile.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonProfile.setBackgroundColor(getResources().getColor(R.color.new_white));
    }

    private void initPermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Nammu.askForPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionCallback() {
                @Override
                public void permissionGranted() {
                    //Nothing, this sample saves to Public gallery so it needs permission
                }

                @Override
                public void permissionRefused() {
                    finish();
                }
            });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void showFileImageChooser(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE1);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE1:
                if (resultCode == RESULT_OK) {
                    uriImage = data.getData();
                    uploadFile(uriImage, getCustomerId(getApplicationContext()));
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void uploadFile(Uri sourceFileUri, String id) {
            String path = FileUtil.getPath(this, sourceFileUri);
            File file = new File(path);
            final RequestBody requestFile =
                    RequestBody.create(
                            MediaType.parse(this.getContentResolver().getType(sourceFileUri)),
                            file
                    );
            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

            RequestBody cid = RequestBody.create(MediaType.parse("text/plain"), id);
            RequestBody act = RequestBody.create(MediaType.parse("text/plain"), "updatePhoto");

            apiService = new APIService();
            apiService.updatePhoto(act, cid, body, new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    User imageUser = (User) response.body();
                    EasyToast.info(getApplicationContext(), imageUser.getDataUser().getCustomerImage());
                    SessionManager.save("image_user", imageUser.getDataUser().getCustomerImage());
                    Glide.with(getApplicationContext())
                            .load(imageUser.getDataUser().getCustomerImage())
                            .into(mImageUser);
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    EasyToast.error(getApplicationContext(), "File tidak terupload");
                }
            });
    }


}
