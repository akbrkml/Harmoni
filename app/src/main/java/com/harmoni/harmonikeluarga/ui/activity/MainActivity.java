package com.harmoni.harmonikeluarga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.ui.fragment.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)Toolbar mToolbar;
    @BindView(R.id.nav_view)NavigationView mNavigationView;

    private ActionBarDrawerToggle mToggle;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initComponents();
    }

    private void initComponents(){
        setSupportActionBar(mToolbar);

        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mToggle.setDrawerIndicatorEnabled(false);
        mToggle.setHomeAsUpIndicator(R.drawable.ic_toolbar_nav);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
        mToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        mNavigationView.setNavigationItemSelectedListener(this);

        addFragment();
    }

    private void addFragment(){
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.content_frame, new HomeFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
            // Handle the camera action
        } else if (id == R.id.nav_ahli) {

        } else if (id == R.id.nav_event) {

        } else if (id == R.id.nav_pustaka) {

        } else if (id == R.id.nav_kirimsaran) {

        } else if (id == R.id.nav_favorit) {

        } else if (id == R.id.nav_pengaturan) {

        } else if (id == R.id.nav_tentang) {

        } else if (id == R.id.nav_keluar) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
