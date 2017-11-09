package com.harmoni.harmonikeluarga.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.User;
import com.harmoni.harmonikeluarga.ui.fragment.profile.ChildFragment;
import com.harmoni.harmonikeluarga.ui.fragment.profile.ProfileFragment;
import com.harmoni.harmonikeluarga.ui.fragment.profile.TopicFragment;
import com.harmoni.harmonikeluarga.util.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.harmoni.harmonikeluarga.util.Constant.USER_SESSION;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)Toolbar mToolbar;
    @BindView(R.id.img_user)CircleImageView mImageUser;
    @BindView(R.id.nameUser)TextView mTextUser;

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
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
    }

    @OnClick(R.id.btn_anak)
    public void goToChild(){
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, new ChildFragment()).commit();
    }

    @OnClick(R.id.btn_topik)
    public void goToTopic(){
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, new TopicFragment()).commit();
    }

}
