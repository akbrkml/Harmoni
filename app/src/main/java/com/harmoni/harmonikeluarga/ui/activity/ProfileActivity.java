package com.harmoni.harmonikeluarga.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.harmoni.harmonikeluarga.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        initComponents();
    }

    private void initComponents(){
        setSupportActionBar(mToolbar);
    }
}
