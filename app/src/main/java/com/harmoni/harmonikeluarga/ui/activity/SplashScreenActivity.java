package com.harmoni.harmonikeluarga.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.util.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.BuildConfig;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

import static com.harmoni.harmonikeluarga.util.Constant.USER_SESSION;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);

        initComponents();
    }

    private void initComponents(){
        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
        for (int progress = 0; progress < 50; progress += 10) {
            try {
                Thread.sleep(500);
                mProgressBar.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
                if (BuildConfig.DEBUG)
                    Timber.e(e.getMessage());
            }
        }
    }

    private void startApp() {
        final Activity a = this;
        if (isSessionLogin()) {
            MainActivity.start(a);
            SplashScreenActivity.this.finish();
        } else {
            LoginActivity.start(a);
            SplashScreenActivity.this.finish();
        }
    }

    boolean isSessionLogin() {
        return SessionManager.getUser(this, USER_SESSION) != null;
    }
}
