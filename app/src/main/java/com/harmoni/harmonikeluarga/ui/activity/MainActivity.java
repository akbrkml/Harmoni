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
import com.harmoni.harmonikeluarga.ui.fragment.content.ContentChildFragment;
import com.harmoni.harmonikeluarga.ui.fragment.EventJournalismFragment;
import com.harmoni.harmonikeluarga.ui.fragment.MainLibraryFragment;
import com.harmoni.harmonikeluarga.ui.fragment.SaranFragment;
import com.harmoni.harmonikeluarga.ui.fragment.consultation.ConsultationFragment;
import com.harmoni.harmonikeluarga.ui.fragment.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.harmoni.harmonikeluarga.util.DialogUtils.customExitAppDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)Toolbar mToolbar;
    @BindView(R.id.nav_view)NavigationView mNavigationView;

    private ActionBarDrawerToggle mToggle;
    private FragmentManager fm;

    private boolean mToolBarNavigationListenerIsRegistered = false;

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

    /**
     * To be semantically or contextually correct, maybe change the name
     * and signature of this function to something like:
     *
     * private void showBackButton(boolean show)
     * Just a suggestion.
     */
    private void enableViews(boolean enable) {

        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
        if(enable) {
            // Remove hamburger
            mToggle.setDrawerIndicatorEnabled(false);
            // Show back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if(!mToolBarNavigationListenerIsRegistered) {
                mToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Doesn't have to be onBackPressed
                        onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            // Remove back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            mToggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            mToggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }

        // So, one may think "Hmm why not simplify to:
        // .....
        // getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        // mDrawer.setDrawerIndicatorEnabled(!enable);
        // ......
        // To re-iterate, the order in which you enable and disable views IS important #dontSimplify.
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
            customExitAppDialog(this, "Keluar Aplikasi ?");
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
        fm = getSupportFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_beranda) {
            fm.beginTransaction().replace(R.id.content_frame, new ContentChildFragment()).addToBackStack("tag").commit();
        } else if (id == R.id.nav_ahli) {
            fm.beginTransaction().replace(R.id.content_frame, new ConsultationFragment()).addToBackStack("tag").commit();
        } else if (id == R.id.nav_event) {
            fm.beginTransaction().replace(R.id.content_frame, new EventJournalismFragment()).addToBackStack("tag").commit();
        } else if (id == R.id.nav_pustaka) {
            fm.beginTransaction().replace(R.id.content_frame, new MainLibraryFragment()).addToBackStack("tag").commit();
        } else if (id == R.id.nav_kirimsaran) {
            fm.beginTransaction().replace(R.id.content_frame, new SaranFragment()).addToBackStack("tag").commit();
        } else if (id == R.id.nav_favorit) {

        } else if (id == R.id.nav_pengaturan) {

        } else if (id == R.id.nav_tentang) {

        } else if (id == R.id.nav_keluar) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
