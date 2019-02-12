package com.dspot.declex.example.expenses.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.ui.auth.AuthNavigation;
import com.dspot.declex.example.expenses.ui.auth.splash.SplashViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.ViewModel;
@EBinder
@OptionsMenu(R.menu.menu_main)
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById
    Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @Bean
    MainNavigation mainNavigation;

    @ViewModel
    MainViewModel mainViewModel;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        drawer.closeDrawer(GravityCompat.START);

        if (id == R.id.nav_expenses_list) {

        } else if (id == R.id.nav_expenses_per_week) {

        } else if (id == R.id.nav_profile) {
            goToProfile();
        } else if (id == R.id.nav_logout) {

            logout();

            return false;
        } else
            return false;

        return true;
    }

    private void logout() {
        mainViewModel.logout();
    }

    private void goToProfile() {
        mainNavigation.goToProfile();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
