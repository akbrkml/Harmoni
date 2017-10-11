package com.harmoni.harmonikeluarga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.ui.base.BaseActivity;
import com.harmoni.harmonikeluarga.ui.fragment.SettingFragment;
import com.harmoni.harmonikeluarga.ui.fragment.content.ContentChildFragment;
import com.harmoni.harmonikeluarga.ui.fragment.EventJournalismFragment;
import com.harmoni.harmonikeluarga.ui.fragment.MainLibraryFragment;
import com.harmoni.harmonikeluarga.ui.fragment.SaranFragment;
import com.harmoni.harmonikeluarga.ui.fragment.consultation.ConsultationFragment;
import com.harmoni.harmonikeluarga.ui.fragment.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.harmoni.harmonikeluarga.util.DialogUtils.customExitAppDialog;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)Toolbar mToolbar;
    @BindView(R.id.nav_view)NavigationView mNavigationView;

    private ActionBarDrawerToggle mToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initComponents();
        showHomeFragment();
    }

    private void initComponents(){
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, 0, 0){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setDrawerIndicatorEnabled(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
//        mToggle.setHomeAsUpIndicator(R.drawable.ic_toolbar_nav);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void showHomeFragment(){
        add(HomeFragment.newInstance());
    }

    @Override
    protected ActionBarDrawerToggle getDrawerToggle() {
        return mToggle;
    }

    @Override
    protected DrawerLayout getDrawer() {
        return mDrawerLayout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_beranda) {
            add(ContentChildFragment.newInstance());
        } else if (id == R.id.nav_ahli) {
            add(ConsultationFragment.newInstance());
        } else if (id == R.id.nav_event) {
            add(EventJournalismFragment.newInstance());
        } else if (id == R.id.nav_pustaka) {
            add(MainLibraryFragment.newInstance());
        } else if (id == R.id.nav_kirimsaran) {
            add(SaranFragment.newInstance());
        } else if (id == R.id.nav_favorit) {

        } else if (id == R.id.nav_pengaturan) {
            add(SettingFragment.newInstance());
        } else if (id == R.id.nav_tentang) {

        } else if (id == R.id.nav_keluar) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
