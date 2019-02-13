package com.dspot.declex.example.expenses.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.auth.ExpensesUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.Observer;
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

    @Observer
    void expensesUser(ExpensesUser expensesUser) {
        if (expensesUser != null) {
            updateUI(expensesUser);
        }
    }

    private void updateUI(ExpensesUser expensesUser) {
        ((TextView) navigationView
                .getHeaderView(0)
                .findViewById(R.id.userDisplayName)).setText(expensesUser.displayName());

        ((TextView) navigationView
                .getHeaderView(0)
                .findViewById(R.id.userEmail)).setText(expensesUser.email());
    }

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mainViewModel.init();
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
            goToExpensesList();
        } else if (id == R.id.nav_expenses_per_week) {
            goToExpensesPerWeek();
        } else if (id == R.id.nav_profile) {
            goToProfile();
            return false;
        } else if (id == R.id.nav_logout) {
            logout();
            return false;
        } else if (id == R.id.nav_new_expenses) {
            goToCreateNewExpense();
            return false;
        }

        return true;
    }

    private void goToCreateNewExpense() {
        mainNavigation.goToCreateNewExpenses();
    }

    private void goToExpensesList() {
        mainNavigation.goToExpensesList();
    }

    private void goToExpensesPerWeek() {
        mainNavigation.goToExpensesPerWeek();
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
