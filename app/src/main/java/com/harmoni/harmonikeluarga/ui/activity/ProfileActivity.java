package com.harmoni.harmonikeluarga.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.User;
import com.harmoni.harmonikeluarga.ui.fragment.profile.ChildFragment;
import com.harmoni.harmonikeluarga.ui.fragment.profile.ProfileFragment;
import com.harmoni.harmonikeluarga.ui.fragment.profile.TopicFragment;
import com.harmoni.harmonikeluarga.util.SessionManager;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.harmoni.harmonikeluarga.util.Constant.USER_SESSION;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)Toolbar mToolbar;
    @BindView(R.id.img_user)CircularImageView mImageUser;
    @BindView(R.id.nameUser)TextView mTextUser;
    @BindView(R.id.btn_profile)Button mButtonProfile;
    @BindView(R.id.btn_anak)Button mButtonAnak;
    @BindView(R.id.btn_topik)Button mButtonTopik;

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        initToolbar();

        addFragment();

        getSession();
    }

    private void getSession(){
        User user = SessionManager.getUser(this, USER_SESSION);
        if (user != null){
            mTextUser.setText(user.getDataUser().getCustomerName());
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

}
